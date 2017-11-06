package dao;

import appconfig.AppConfig;
import context.Teacher;
import dao.mappers.TeacherMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Driver;

public class JdbcTest {


    public static void main(String[] args) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName("com.mysql.jdbc.Driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl("jdbc:mysql://138.68.75.40:3306/epam-elective");
        dataSource.setUsername("epam-admin");
        dataSource.setPassword("secretservice");

        //dataSource.setConnectionProperties();

        Teacher teacher = new Teacher(100, "Alexandr", "Barmin");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        /*
        String SQL = "insert into Teacher (teacher_id, first_name, last_name) values (?, ?, ?)";
        jdbcTemplate.update( SQL, teacher.getId(), teacher.getFirstName(), teacher.getLastName());
        */

        String SQL2 = "select * from Teacher where teacher_id = ?";
        Teacher newTeacher = jdbcTemplate.queryForObject(SQL2,
                new Object[]{1}, new TeacherMapper());

        System.out.println(newTeacher);


    }
}
