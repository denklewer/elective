package epam_team1.service.appconfig;



import epam_team1.service.services.UserManagerImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.managed.BasicManagedDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import epam_team1.service.dao.CourseJdbcDaoImpl;
import org.springframework.context.annotation.*;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.jta.JtaTransactionManager;


import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {CourseJdbcDaoImpl.class, UserManagerImpl.class})
@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired
    private Environment environment;


    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(mySqlDataSource());
    }


    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(mySqlDataSource());
    }

    @Bean
    public DataSource mySqlDataSource() {
        System.out.println("mySql");
        String className = environment.getProperty("spring.datasource.driver-class-name");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        System.out.println("End");
        return dataSource;
    }

}