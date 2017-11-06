package dao;

import context.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

public class TestClass {



    public static void main(String[] args) throws NamingException, IOException, ClassNotFoundException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        Properties properties = new Properties();
        properties.load(new FileReader("C:\\Users\\denkl\\Documents\\Idea Projects\\Epam\\elective\\service\\src\\main\\resources\\database.properties"));


        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setConnectionProperties(properties);
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);


        TeacherJdbcDao teacherJdbcDao = new TeacherJdbcDao(dataSource);

        ArrayList<Teacher> list = teacherJdbcDao.list();
        for (Teacher item: list) {
            System.out.println(item);

        }


    }
}
