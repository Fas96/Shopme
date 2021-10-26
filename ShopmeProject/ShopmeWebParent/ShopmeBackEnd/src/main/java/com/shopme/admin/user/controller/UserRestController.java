package com.shopme.admin.user.controller;

import com.shopme.admin.user.UserService;
import com.shopme.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserService service;
    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@Param("email") String email,@Param("id") Integer id,@Param("roles") ArrayList<Role> roleList){

        System.out.println("=============i was called====================");
        System.out.println(roleList);
        return service.isEmailUnique(id,email)?"Ok":"Duplicate";
    }

    @PostMapping("/users/try")
    public String checkRoleSent(@Param("message") String message){
        System.out.println("=============i was called====================");
        System.out.println(message);
        return service.listRoles().size()>0?"Ok":"Duplicate";
    }
}
