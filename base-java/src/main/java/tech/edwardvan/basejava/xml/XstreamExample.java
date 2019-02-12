package tech.edwardvan.basejava.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * Xstream示例
 *
 * @author EdwardVan
 */
@Slf4j
public class XstreamExample {
    public static void main(String[] args) {
        quickStart();
        annotationMapping();
    }

    private static void quickStart() {
        XStream xstream = new XStream();

        // 忽略报错
        xstream.ignoreUnknownElements();
        // 设置默认安全防护
        XStream.setupDefaultSecurity(xstream);
        // 设置允许的类
        xstream.allowTypes(new Class[]{Person.class});

        //设置类的别名
        xstream.alias("person", Person.class);

        //设置字段的别名
        xstream.aliasField("username", Person.class, "name");

        //去掉集合类型生成xml的父节点
        xstream.omitField(Person.class, "age");

        //去掉集合类型生成xml的父节点
        xstream.addImplicitCollection(Person.class, "friends");

        //将name字段作为Person的属性
        //作用等同注解@XStreamAsAttribute
        xstream.useAttributeFor(Person.class, "name");

        // XML序列化
        String xmlString = xstream.toXML(new Person("Van", 20, "男", null, Arrays.asList("pc games", "music")));
        log.debug("obj->xml:{}", xmlString);

        // XML反序列化
        Person p = (Person) xstream.fromXML(xmlString);
        log.debug("xml->obj:{}", p);

    }

    private static void annotationMapping() {
        XStream xstream = new XStream();

        // 忽略报错
        xstream.ignoreUnknownElements();

        // 设置默认安全防护
        XStream.setupDefaultSecurity(xstream);

        // 设置允许的类
        xstream.allowTypes(new Class[]{Person.class});

        // 自动侦查注解(将缓存所有类的类型,性能较差)
        // xstream.autodetectAnnotations(true);

        // 显式的注册使用注解的类
        xstream.processAnnotations(new Class[]{Person.class});

        // XML序列化
        String xmlString = xstream.toXML(new Person("Van", 20, "男", null, Arrays.asList("pc games", "music")));
        log.debug("obj->xml:{}", xmlString);

        // XML反序列化
        Person p = (Person) xstream.fromXML(xmlString);
        log.debug("xml->obj:{}", p);
    }

    @Data
    @AllArgsConstructor
    /**
     * 重命名注解
     */
    @XStreamAlias("person")
    static class Person {
        /**
         * 把字段节点设置成属性
         */
        @XStreamAsAttribute
        private String name;
        private int age;
        /**
         * 隐藏字段
         */
        @XStreamOmitField
        private String sex;
        /**
         * 省略集合根节点
         */
        @XStreamImplicit
        private List friends;
        /**
         * 将collection或map类型的成员变量中数据转换成xml相同层次,可能会导致无法反序列化
         */
        @XStreamImplicit(itemFieldName = "interest")
        private List interests;
    }
}
