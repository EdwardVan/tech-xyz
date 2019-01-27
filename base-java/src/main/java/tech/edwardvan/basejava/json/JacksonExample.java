package tech.edwardvan.basejava.json;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Jackson示例
 *
 * @author EdwardVan
 */
@Slf4j
public class JacksonExample {

    public static void main(String[] args) throws IOException {
        log.debug("---------简单的映射---------");
        quickStart();
        log.debug("---------集合的映射---------");
        collectionMapping();
        log.debug("---------注解---------");
        annotationMapping();
    }

    static void quickStart() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Friend friend = new Friend("EdwardVan", 20, new Date());

        // 写为字符串
        String text = mapper.writeValueAsString(friend);
        log.debug("写为字符串:{}", text);
        // 写为文件
        mapper.writeValue(new File("d:/friend.json"), friend);
        // 写为字节流
        byte[] bytes = mapper.writeValueAsBytes(friend);
        log.debug("写为字节流:{}", bytes.length);

        // 从字符串中读取
        Friend textFriend = mapper.readValue(text, Friend.class);
        log.debug("从字符串中读取:{}", textFriend);
        // 从字节流中读取
        Friend bytesFriend = mapper.readValue(bytes, Friend.class);
        log.debug("从字节流中读取:{}", bytesFriend);
        // 从文件中读取
        Friend fileFriend = mapper.readValue(new File("d:/friend.json"), Friend.class);
        log.debug("从文件中读取:{}", fileFriend);

    }

    static void collectionMapping() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // list的读和写
        List<Friend> friends = new ArrayList<Friend>();
        friends.add(new Friend("EdwardVan", 20, new Date()));
        friends.add(new Friend("EdwardVan2", 21, new Date()));
        // 写为字符串
        String listJson = mapper.writeValueAsString(friends);
        log.debug("将list转换为json字符串:{}", listJson);
        // 从字符串读
        List<Friend> newFriends = mapper.readValue(listJson, new TypeReference<List<Friend>>() {
        });
        log.debug("从json字符串中读取list:{}", newFriends);

        // map的读和写
        Map<String, Object> map = new HashMap<>();
        map.put("name", "EdwardVan");
        map.put("age", 20);
        map.put("interests", new String[]{"pc games", "music"});
        // 写为字符串
        String mapJson = mapper.writeValueAsString(map);
        log.debug("将map转换为json字符串:{}", mapJson);
        // 从字符串读
        Map<String, Object> map2 = mapper.readValue(mapJson, new TypeReference<Map<String, Object>>() {
        });
        log.debug("从json字符串中读取map:{}", map2);
        // 从字符串中读取到JsonNode
        JsonNode root = mapper.readTree(mapJson);
        String name = root.get("name").asText();
        int age = root.get("age").asInt();
        log.debug("从JsonNode中获取信息:{},{}", name, age);

    }


    static void annotationMapping() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //是否环绕根元素,默认false,如果为true,则默认以类名作为根元素,你也可以通过@JsonRootName来自定义根元素名称
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        //是否缩放排列输出,默认false,有些场合为了便于排版阅读则需要对输出做缩放排列
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        //序列化日期时以timestamps输出,默认true
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        //序列化枚举是以toString()来输出,默认false,即默认以name()来输出
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        //序列化Map时对key进行排序操作,默认false
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        //序列化char[]时以json数组输出,默认false
        mapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);

        Friend2 f2 = new Friend2("Ed", 25, new Date(), "a", "b", "c");
        String text = mapper.writeValueAsString(f2);
        log.debug("将对象转换为json字符串:{}", text);

        Friend2 fd2 = mapper.readValue(text, Friend2.class);
        log.debug("从json字符串中读取对象:{}", fd2);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Friend {
        private String nickname;
        private int age;
        private Date birthday;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonRootName("Friend2")
    /**
     * @JsonIgnoreProperties 用来说明有些属性在序列化/反序列化时需要忽略掉
     */
    @JsonIgnoreProperties({"uselessProp1", "uselessProp3"})
    public static class Friend2 {
        /**
         * @JsonProperty 对属性名称重命名
         */
        @JsonProperty("NickName")
        private String name;
        private int age;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date birthday;
        private String uselessProp1;
        /**
         * @JsonIgnore 忽略对应的属性, 不管注解在getters上还是setters上都会忽略对应的属性
         */
        @JsonIgnore
        private String uselessProp2;
        private String uselessProp3;
    }

}
