package epam_team1.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:database.properties")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT Login, Password, 1 FROM User WHERE Login = ?")
                .authoritiesByUsernameQuery("select Login, 'ROLE_ADMIN' from User where Login=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login.html",
                        "/pages/register.html",
                        "/signin.css",
                        "/dist/css/bootstrap.min.css",
                        "/dist/css/bootstrap.min.css.map",
                        "/assets/js/vendor/popper.min.js",
                        "/dist/js/bootstrap.min.js",
                        "/dist/js/bootstrap.min.js.map",
                        "/assets/js/vendor/jquery-slim.min.js",
                        "/js/jquery.cookie.js",
                        "/js/jquery.i18n.properties-1.0.9.js",
                        "/js/jquery.i18n.properties-min-1.0.9.js",
                        "/Messeges.properties",
                        "/Messeges_en_EN.properties",
                        "/Messeges_ru_RU.properties"
                        ).permitAll()
                .anyRequest().authenticated()
                .antMatchers("/prof.html").permitAll()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .failureForwardUrl("/login.html")
                .and()
                .exceptionHandling().accessDeniedPage("/pages/register.html")
                .and()
                .csrf().disable();

        http.logout()
                .permitAll();


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**,/static/login.html");
    }

}
