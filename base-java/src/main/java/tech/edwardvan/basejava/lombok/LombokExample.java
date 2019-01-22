package tech.edwardvan.basejava.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * lombok示例
 *
 * @author EdwardVan
 * @Data 等价于 @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor,不包括@NoArgsConstructor和@AllArgsConstructor
 * @NoArgsConstructor 为该类产生无参的构造方法, 如果类中含有final修饰的成员变量, 是无法使用该注解
 * @AllArgsConstructor 为该类产生包含所有参数的构造方法
 * @RequiredArgsConstructor 使用类中所有带有@NonNull注解的或者带有final修饰的成员变量生成对应的构造方法,如果所有字段都没有@nonNull注解,那效果同NoArgsConstructor
 * @Builder 提供了一种比较推崇的构建值对象的方式, 缺点就是父类的属性不能产于builder
 * @Cleanup 能够自动释放资源, 默认是调用资源的close()方法, 如果该资源有其它关闭方法, 可使用@Cleanup("methodName")来指定要调用的方法
 * @ToString 生成toString,@ToString(includeFieldNames = true, //是否使用字段名 exclude = {"name"}, //排除某些字段 of = {"age"}, //只使用某些字段 callSuper = true //是否让父类字段也参与 默认false)
 * @EqualsAndHashCode 生成equals和hashcode方法, 同时后者还会生成一个canEqual方法, 用于判断某个对象是否是当前类的实例
 * @Getter/@Setter 解在类上, 可以为所有属性自动生成Getter/Setter方法；解在属性上,可以为相应的属性自动生成Getter/Setter方法,如果是final变量,那就只会有get方法
 * @NonNull 该注解用在属性或构造器上, Lombok会生成一个非空的声明, 可用于校验参数, 能帮助避免空指针.
 * @Slf4j 等价于private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger();
 */
@Slf4j
public class LombokExample {

    public static void main(String[] args) {
        log.debug("测试@AllArgsConstructor:" + new Friend("Ed", 19));
//        log.debug("测试@NonNull:" + new Friend(null, 20));
        log.debug("测试@Builder:" + Friend.builder().nickname("Ed").age(21).build());
        log.debug("测试@EqualsAndHashCode:" + new Friend().hashCode());
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @Getter
    @Setter
    @Slf4j
    @Builder
    public static class Friend {
        @NonNull
        private String nickname;
        private int age;
    }
}
