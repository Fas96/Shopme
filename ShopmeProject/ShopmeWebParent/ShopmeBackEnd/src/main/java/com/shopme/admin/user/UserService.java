package com.shopme.admin.user;

import com.shopme.admin.entity.User;
import com.shopme.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository rolRep;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();


    private BCryptPasswordEncoder password;

    public Iterable<User> userList(){
        return  userRepository.findAll();
    }
    public List<Role> roleList(){
        return (List<Role>) rolRep.findAll();
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
