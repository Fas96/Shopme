package com.shopme.admin.controller;

import com.shopme.admin.entity.User;
import com.shopme.admin.formatter.FileUploadUtil;
import com.shopme.admin.formatter.UserNotFoundException;
import com.shopme.admin.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.NoSuchElementException;

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
        user.setEnabled(true);
        model.addAttribute("rolesList", userService.roleList());
        model.addAttribute("pageTitle", "Create New User");
        return "users_form";
    }

    @GetMapping("users/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            model.addAttribute("rolesList", userService.roleList());
            model.addAttribute("rolesList", userService.roleList());
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User");
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
            User savedUser = userService.save(user);
            String uploadDir= "users-photos/"+savedUser.getId();
            FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
        }else{
            userService.save(user);
        }
        //userService.save(user);
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
