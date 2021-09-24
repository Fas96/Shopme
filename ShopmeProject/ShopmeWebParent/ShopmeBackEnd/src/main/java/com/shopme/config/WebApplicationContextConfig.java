package com.shopme.config;

import com.shopme.admin.formatter.RoleFormatter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan("com.shopme")
public class WebApplicationContextConfig implements WebMvcConfigurer {
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new RoleFormatter());
    }


}
