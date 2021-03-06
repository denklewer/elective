package dao;


import epam_team1.service.dao.CourseJdbcDaoImpl;
import epam_team1.service.logger.ServiceLogger;
import epam_team1.service.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootConfiguration
@TestConfiguration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {CourseJdbcDaoImpl.class, UserManager.class})
@EnableAspectJAutoProxy
public class TestConfig {

    @Autowired
    private Environment environment;

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    @Bean
    public ServiceLogger serviceLogger () {
        return new ServiceLogger();
    }

    @Bean
    public DataSource h2DataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2) // set data base type
                .addScript("scripts/create/user") // set create table script
                .addScript("scripts/create/course")
                .addScript("scripts/create/userinserts") // insert values into user table
                .addScript("scripts/create/courseinserts")
                .addScript("scripts/create/course_participation")
                .addScript("scripts/create/studentScoreInserts")
                .build();
        return db;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(h2DataSource());
    }
}
