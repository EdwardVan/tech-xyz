package tech.edwardvan.msspringcloudgenerator.seata;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Seata Nacos配置初始化
 *
 * @author EdwardVan
 */
@Slf4j
public class SeataInit {

    public static void main(String[] args) throws IOException {
        String nacosUrl = "http://127.0.0.1:8848/nacos/v1/cs/configs?";
        String nacosConfigPath = ResourceUtils.getURL("classpath:").getPath() + "/application.properties";
        Properties properties = new Properties();
        // 使用properties对象加载输入流
        properties.load((new FileInputStream(nacosConfigPath)));
        //注册配置
        registerNacosConfig(properties, nacosUrl);
        log.info("添加配置完成");
    }

    /**
     * 注册nacos配置
     */
    public static void registerNacosConfig(Properties properties, String nacosUrl) {
        properties.forEach((k, v) -> {
            final String key = k.toString();
            final String value = v.toString();
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("dataId", key);
                params.add("namespace", "public");
                params.add("group", "SEATA_GROUP");
                params.add("content", value);
                //发送Post数据并返回数据
                sendPostRequest(nacosUrl, params);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 向目的URL发送post请求
     */
    public static String sendPostRequest(String url, MultiValueMap<String, String> params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }
}
