package ua.vlad.hg;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("application.security")
public class ApplicationProperties {

    private String googleMapsApiKey;

}
