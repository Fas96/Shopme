package com.shopme.admin.user;

import com.shopme.admin.entity.User;
import com.shopme.admin.formatter.UserNotFoundException;
import com.shopme.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
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
        boolean isUpdatingUser=user.getId()!=null;
        if(isUpdatingUser){
            User gotuser = userRepository.findById(user.getId()).get();
            if(user.getPassword().isEmpty()){
                gotuser.setPassword(user.getPassword());
            }else{
                user.setPassword(bCryptPasswordEncoder.encode(gotuser.getPassword()));
            }
        }else{ user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));}


        userRepository.save(user);
    }
    public void updateUserEnabledStatus(Integer id,boolean enabled){
        userRepository.updateEnabledStatus(id, enabled);
    }
    public boolean isEmailUnique(String email,Integer id){
        User userByEmail = userRepository.getUserByEmail(email);

        if (userByEmail==null) return true;

        boolean isCreatingNew=(id==null);

        if(isCreatingNew){
            if(userByEmail!=null){return false;}
        }else{
            return userByEmail.getId() == id;
        }

        return true;
    }

    public void deleteUser(Integer id) throws UserNotFoundException {
        if(userRepository.countById(id)==0||userRepository.countById(id)==null){
            throw  new UserNotFoundException("The user with id: "+id+" could not be found");
        }
        userRepository.deleteById(id);
    }

    public User getUser(Integer id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw  new UserNotFoundException("The user with id: "+id+" could not be found");
           }
        }


}
