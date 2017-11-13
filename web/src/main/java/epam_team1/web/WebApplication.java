package epam_team1.web;


import epam_team1.service.ServiceApplication;
import epam_team1.service.appconfig.AppConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@Import(AppConfig.class)
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.CONSOLE)
                .sources(ServiceApplication.class, WebApplication.class)
                .run(args);
    }
}
