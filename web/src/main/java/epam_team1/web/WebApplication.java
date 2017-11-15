package epam_team1.web;


import epam_team1.service.ServiceApplication;
import epam_team1.service.appconfig.AppConfig;
import epam_team1.web.config.SecurityConfig;
import epam_team1.web.controllers.ElectiveController;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.context.annotation.Import;

import java.util.Map;

@EnableAutoConfiguration
@Import({AppConfig.class, SecurityConfig.class})
@SpringBootApplication(scanBasePackages={"epam_team1.service"})
@ComponentScan(basePackageClasses = ElectiveController.class)
public class WebApplication {
    public static void main(String[] args) {
    //    SpringApplication.run(WebApplication.class, args);
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.CONSOLE)
                .sources(WebApplication.class)
                .run(args);
    }
}
