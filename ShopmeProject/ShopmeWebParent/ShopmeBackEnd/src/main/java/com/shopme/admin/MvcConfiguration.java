package com.shopme.admin;

import com.shopme.admin.formatter.RoleFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

//@Configuration
//public class MvcConfiguration implements WebMvcConfigurer {
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new RoleFormatter());
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String dirName="users-photos";
//        Path userPhotosDir= Paths.get(dirName);
//        String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();
//        //allow all files under dir available
//        System.out.println("===========fas==================");
//        registry.addResourceHandler("/"+dirName+"/**").addResourceLocations("file:/"+userPhotosPath+"/");
//    }
//}
