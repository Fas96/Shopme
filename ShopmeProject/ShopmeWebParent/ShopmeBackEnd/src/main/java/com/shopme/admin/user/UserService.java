package com.shopme.admin.user;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository rolRep;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();


    public List<User> userList(){
        return (List<User>) userRepository.findAll();
    }
    public List<Role> listRoles(){
        return (List<Role>) rolRep.findAll();
    }


    public User save(User user) {
        boolean isUpdatingUser=user.getId()!=null;

        if(isUpdatingUser){
            User gotuser = userRepository.findById(user.getId()).get();
            if(user.getPassword().isEmpty()){
                gotuser.setPassword(user.getPassword());
            }else{
                user.setPassword(bCryptPasswordEncoder.encode(gotuser.getPassword()));
            }

        }else{

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        User newUser=new User(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.isEnabled());
        newUser.setId(user.getId());
        newUser.getRoles().addAll(user.getRoles().stream().map(v->{
            Optional<Role> r=rolRep.findById(v.getId()) ;
            System.out.println("===============++++");
            System.out.println(r);
            v.getUsers().add(newUser);
            return v;
        }).collect(Collectors.toList()));


        return userRepository.saveAndFlush(newUser);
    }
    public void updateUserEnabledStatus(Integer id,boolean enabled){
        userRepository.updateEnabledStatus(id, enabled);
    }
    public boolean isEmailUnique(Integer id,String email){
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

    public User get(Integer id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        }catch (NoSuchElementException e){
            throw  new UserNotFoundException("The user with id: "+id+" could not be found");
           }
        }


}
