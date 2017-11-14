package epam_team1.service.appconfig;

import epam_team1.service.dao.CourseJdbcDaoImpl;
import epam_team1.service.services.UserManagerImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(mySqlDataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(mySqlDataSource());
    }

    @Bean
    public DataSource mySqlDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(environment.getProperty("datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("datasource.url"));
        dataSource.setUsername(environment.getProperty("datasource.username"));
        dataSource.setPassword(environment.getProperty("datasource.password"));
        return dataSource;
    }
}