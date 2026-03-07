package ua.vlad.hg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ua.vlad.hg.config.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class HuntingGroundsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuntingGroundsApplication.class, args);
    }

}
