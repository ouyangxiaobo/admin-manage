package com.springboot.adminmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminManageApplication.class, args);
    }

}
