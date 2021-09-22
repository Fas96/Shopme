package com.shopme.admin.user;

import com.shopme.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> userList(){
        return  userRepository.findAll();
    }
}
