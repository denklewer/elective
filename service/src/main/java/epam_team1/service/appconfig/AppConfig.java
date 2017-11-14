package epam_team1.service.appconfig;

import epam_team1.service.services.UserManagerImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.managed.BasicManagedDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import epam_team1.service.dao.CourseJdbcDaoImpl;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {CourseJdbcDaoImpl.class, UserManagerImpl.class})
@PropertySource("classpath:database.properties")
@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired
    private Environment environment;


    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource mySqlDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(environment.getProperty("datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("datasource.url"));
        dataSource.setUsername(environment.getProperty("datasource.username"));
        dataSource.setPassword(environment.getProperty("datasource.password"));
        return dataSource;
//        String className = environment.getProperty("datasource.driver-class-name");
//        BasicDataSource dataSource = new BasicDataSource();
//
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://138.68.75.40:3306/epam-elective");
//        dataSource.setUsername("epam-admin");
//        dataSource.setPassword("secretservice");
//        dataSource.setValidationQuery("select 1 from dual");
//        return dataSource;
    }
}