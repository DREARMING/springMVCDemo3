package test;

import com.mvcoder.springmvc.bean.Student;
import com.mvcoder.springmvc.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Created by mvcoder on 2020/1/17.
 */
public class BeanTest {

    @Test
    public void testUserBean(){
        //初始化Spring容器ApplicationContext，加载配置文件
        ApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过容器获取 User 实例
        User user = (User) application.getBean("user");
        user.setId(123);
        user.setUsername("mvcoder");
        user.printInfo();

        //因为 scope 是单例，user2 跟 user 获取到的是同一个对象
        User user2 = (User) application.getBean("user");
        user2.printInfo();
    }

    @Test
    public void testStudentBean(){
        ConfigurableApplicationContext application = new ClassPathXmlApplicationContext("applicationContext.xml");

        application.start();

        Student student = (Student) application.getBean("student");
        student.printInfo();

        student.method2();
        student.method3();

        application.stop();
    }


}
