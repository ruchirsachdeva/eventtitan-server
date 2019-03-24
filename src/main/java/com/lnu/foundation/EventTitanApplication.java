package com.lnu.foundation;

import com.lnu.foundation.config.RepositoryRestConfig;
import com.lnu.foundation.config.SecurityConfig;
import com.lnu.foundation.config.WebConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventTitanApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EventTitanApplication.class, WebConfig.class, SecurityConfig.class, RepositoryRestConfig.class).run(args);
    }

}
