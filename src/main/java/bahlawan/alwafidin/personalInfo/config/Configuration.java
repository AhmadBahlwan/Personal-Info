package bahlawan.alwafidin.personalInfo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    SimpleDateFormat dateFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

}
