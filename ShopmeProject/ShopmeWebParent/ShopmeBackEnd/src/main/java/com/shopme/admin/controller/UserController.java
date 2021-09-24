package com.shopme.admin.controller;

import com.shopme.admin.entity.User;
import com.shopme.admin.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String usersList(Model model){
        model.addAttribute("users",userService.userList());
        return "users";
    }
    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user,Model model){
        user.setEnabled(true);
        model.addAttribute("rolesList",userService.roleList());
        return "users_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        System.out.println(user);
        System.out.println("=======================user========================");
        userService.save(user);
        redirectAttributes.addFlashAttribute("sMessage","User has been saved Successfully");
        return "redirect:/users";
    }
}
