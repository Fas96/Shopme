package com.shopme.admin.user.controller;

import com.shopme.admin.formatter.FileUploadUtil;
import com.shopme.admin.user.UserNotFoundException;
import com.shopme.admin.user.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("users", userService.userList());
        model.addAttribute("pageTitle", "Users List");
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        List<Role> roles = userService.roleList();

        user.setEnabled(true);

        model.addAttribute("rolesList", roles);

        model.addAttribute("pageTitle", "Create New User");
        return "users_form";
    }

    @GetMapping("users/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

        try {
            List<Role> roles = userService.roleList();
            User user = userService.getUser(id);

            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User");
            model.addAttribute("rolesList", roles);

            return "users_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("sMessage", e.getMessage());
            return "redirect:/users";
        }

    }


    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) throws UserNotFoundException {
       try {
           userService.deleteUser(id);
           redirectAttributes.addFlashAttribute("sMessage", "Successfully Deleted");

       }catch (UserNotFoundException e){
           redirectAttributes.addFlashAttribute("sMessage", e.getMessage());
       }
        return "redirect:/users";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if(!multipartFile.isEmpty()){
            String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            System.out.println("============user roles check=============");
            System.out.println(user.getRoles());
            System.out.println("--------------------------");
            User savedUser = userService.save(user);

            String uploadDir= "users-photos/"+savedUser.getId();
            //clean-> delete old files in directory before saving file to directory
            FileUploadUtil.clearDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
        }else{

            System.out.println("============user roles check=============");
            System.out.println(user.getRoles());
            System.out.println("--------------------------");
            if(user.getPhotos().isEmpty())user.setPhotos(null);
            user.setRoles(user.getRoles());

            userService.save(user);

        }

        redirectAttributes.addFlashAttribute("sMessage","User has been saved Successfully");
        return "redirect:/users";
    }


    @GetMapping("users/{id}/enabled/{status}")
    public String updateUserEnableStatus(@PathVariable("id") Integer id,@PathVariable("status") boolean status, RedirectAttributes redirectAttributes){

        userService.updateUserEnabledStatus(id,status);
        String msg="";
        if(status){
            msg="User "+id +" enable status is enabled";
        }else {
            msg="User enable " +id+ " status is disabled";
        }
        redirectAttributes.addFlashAttribute("sMessage",msg);

        return "redirect:/users";
    }
}
