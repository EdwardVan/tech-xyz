package tech.edwardvan.webssmannotation.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.edwardvan.webssmannotation.model.Student;
import tech.edwardvan.webssmannotation.model.User;
import tech.edwardvan.webssmannotation.service.IUserService;

/**
 * 用户模块
 *
 * @author EdwardVan
 *
 * 实现自动装配
 *      Autowired注解
 *          1. 默认优先按照类型去容器中找对应的组件,applicationContext.getBean(Student.class),找到就赋值
 *          2. 如果找到多个相同类型的组件,再将属性的名称作为组件的id去容器中查找,applicationContext.getBean("student")
 *          3. @Qualifier("student2"),使用@Qualifier注解指定需要装配的组件的id,而不是使用属性名
 * 		    4. 自动装配默认必须要有对应的对象,没有就会报错,可以使用@Autowired(required=false)取消必须
 * 		    5. @Primary注解让Spring进行自动装配的时候默认使用首选的bean,也可以继续使用@Qualifier指定需要装配的bean的名字
 *      java规范的注解
 *          1. @Resource(JSR250规范)
 *              可以和@Autowired一样实现自动装配功能,默认是按照组件名称进行装配的
 *              没有支持@Primary功能
 *              没有支持@Autowired(reqiured=false)
 *          2. @Inject(JSR330规范)
 *              需要导入javax.inject的包,和@Autowired一样实现自动装配功能
 *              支持@Primary功能
 *              没有支持@Autowired(reqiured=false)
 *
 *  Autowired注解的位置
 *      标注在方法位置,执行方法并且自动装配参数,注意:@Bean+方法参数,参数从容器中获取,默认不写@Autowired效果是一样的,都能自动装配
 *      标在构造器上,创建对象时执行该构造方法并且自动装配参数,注意:如果组件只有一个有参构造器,这个有参构造器的@Autowired可以省略,参数位置的组件还是可以自动从容器中获取
 *      放在参数位置
 */
@Api(value = "用户模块", tags = "用户模块接口")
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private Student student;

    @Autowired
    @Qualifier("conditionalUser")
    private User user;

    @ApiOperation(value = "获取用户信息", notes = "测试Restful接口")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "int")
    @RequestMapping(value = "/info/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getInformation(@PathVariable(value = "userId") Integer id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "测试事务", notes = "测试事务")
    @GetMapping("/testTransactional")
    public void testTransactional() {
        userService.testTransactional();
    }

    @ApiOperation(value = "测试AOP", notes = "测试AOP")
    @GetMapping("/testAop")
    public void testAop() {
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        System.out.println(student.toString());
        System.out.println(user.toString());
    }
}
