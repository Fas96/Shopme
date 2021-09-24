package com.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class PasswordEncoder {
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Test
    public void testPasswordEncoder(){
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
        System.out.println("=================encode=======");
    }

}
