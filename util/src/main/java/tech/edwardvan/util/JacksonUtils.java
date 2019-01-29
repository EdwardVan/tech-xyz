package tech.edwardvan.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Jackson工具类
 *
 * @author EdwardVan
 */
public class JacksonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 初始化objectMapper
     */
    static {
        // 序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        // 取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    private JacksonUtils() {
    }

    /**
     * 获取objectMapper实例
     */
    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * javaBean对象转换为json字符串
     */
    public static String beanToJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * javaBean对象转换为json字符串,忽略空值
     */
    public static String beanToJsonIgnoreNull(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    /**
     * json字符串转换为JavaBean对象
     */
    public static <T> T jsonToBean(String jsonString, Class<T> clazz) throws Exception {
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * json字符串转换为List对象
     */
    public static List jsonToList(String jsonString) throws Exception {
        return objectMapper.readValue(jsonString, List.class);
    }

    /**
     * json字符串转换为List对象,需要指定元素类型
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) throws Exception {
        JavaType javaType = getJavaType(ArrayList.class, clazz);
        return objectMapper.readValue(jsonString, javaType);
    }

    /**
     * json字符串转换为map
     */
    public static Map<String, Object> jsonToMap(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.readValue(jsonString, Map.class);
    }

    /**
     * json字符串转换为map,需要指定value类型,key类型为String
     */
    public static <T> Map<String, T> jsonToMap(String jsonString, Class<T> clazz) throws Exception {
        JavaType javaType = getJavaType(Map.class, String.class, clazz);
        Map<String, T> map = objectMapper.readValue(jsonString, javaType);
        return map;
    }

    /**
     * 获取泛型类javaType
     *
     * @param clazz            泛型类
     * @param parameterClasses 元素类
     * @return JavaType Java类型
     */
    public static JavaType getJavaType(Class<?> clazz, Class<?>... parameterClasses) {
        return objectMapper.getTypeFactory().constructParametricType(clazz, parameterClasses);
    }

    /**
     * map转换为JavaBean
     */
    public static <T> T mapToBean(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

}
