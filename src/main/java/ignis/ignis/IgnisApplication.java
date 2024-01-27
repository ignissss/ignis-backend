package ignis.ignis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class IgnisApplication {

    public static void main(String[] args) {
        SpringApplication.run(IgnisApplication.class, args);
    }

}
