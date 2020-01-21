package test;

import com.mvcoder.springmvc.bean.Student;
import com.mvcoder.springmvc.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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


    @Test
    public void testDB() throws Exception {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/springmvc_test";
            String user = "root";
            String password = "08130125cjj";
            connection = (Connection) DriverManager.getConnection(url, user, password);
            // 获取statement，preparedStatement
            String sql = "select * from tb_user where user_id=?";
            prepareStatement = (PreparedStatement) connection.prepareStatement(sql);
            // 设置参数
            prepareStatement.setLong(1, 2);
            // 执行查询
            rs = prepareStatement.executeQuery();
            // 处理结果集
            while (rs.next()) {
                System.out.println(rs.getString("user_id"));
                System.out.println(rs.getString("user_name"));
            }
        } finally {
            // 关闭连接，释放资源
            if (rs != null) {
                rs.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Test
    public void testMybatis() throws IOException {
        // 指定全局配置文件
        String resource = "mybatis/mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 操作CRUD，第一个参数：指定statement，规则：命名空间+“.”+statementId
            // 第二个参数：指定传入sql的参数：这里是用户id
            User user = sqlSession.selectOne("MyMapper.selectUser", 2);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

}
