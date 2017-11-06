package dao;

import appconfig.AppConfig;
import context.Teacher;
import dao.mappers.TeacherMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Example class of database connection. Only fro testing.
 */
public class JdbcTest {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // load properties from file
        Properties properties = new Properties();
        properties.load(JdbcTemplate.class.getResourceAsStream("/database.properties"));

        // create data source
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String className = properties.getProperty("driverClassName");
        dataSource.setDriverClassName(className);
        //  dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setConnectionProperties(properties);

        // create jdbc template
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // Usage of jdbcTemplate
        // ...
        // or Dao usage
        TeacherJdbcDao teacherJdbcDao = new TeacherJdbcDao(dataSource);
        System.out.println(teacherJdbcDao.list());


    }
}
