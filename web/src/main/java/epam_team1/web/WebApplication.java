package epam_team1.web;


import epam_team1.service.appconfig.AppConfig;
import epam_team1.web.controllers.ElectiveController;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(AppConfig.class)
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
