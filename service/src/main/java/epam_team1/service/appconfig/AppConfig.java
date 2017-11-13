package epam_team1.service.appconfig;


import epam_team1.service.dao.CourseJdbcDaoImpl;
import org.apache.commons.dbcp.managed.BasicManagedDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@ComponentScan(basePackageClasses = CourseJdbcDaoImpl.class)
@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource mySqlDataSource() {
        String className = environment.getProperty("driverClassName");
        BasicManagedDataSource dataSource = new BasicManagedDataSource();

        dataSource.setDriverClassName(className);
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("user"));
        dataSource.setPassword(environment.getProperty("password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(mySqlDataSource());
    }
}
