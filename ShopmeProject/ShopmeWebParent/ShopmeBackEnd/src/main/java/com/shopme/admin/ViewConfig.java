package com.shopme.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

//@Configuration
//@EnableWebMvc
//public class ViewConfig implements WebMvcConfigurer {
//
//
//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver  templateResolver = new SpringResourceTemplateResolver ();
//        templateResolver.setPrefix("classpath:template/");
//        templateResolver.setCharacterEncoding("UTF-8");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("LEGACYHTML5");
//
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine(MessageSource messageSource) {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.setTemplateEngineMessageSource(messageSource);
//
//
//        return templateEngine;
//    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    @Bean
//    @Autowired
//    public ViewResolver viewResolver(MessageSource messageSource) {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine(messageSource));
//        viewResolver.setCharacterEncoding("UTF-8");
//        viewResolver.setOrder(0);
//        return viewResolver;
//    }
//}
