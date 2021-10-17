package com.shopme.admin.user;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    RoleService Rservice;

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public User getByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    public List<User> listAll() {
        return (List<User>) userRepo.findAll(Sort.by("firstName").ascending());
    }


    public List<Role> listRoles() {
        return (List<Role>) roleRepo.findAll();
    }


    public User save(User user) {
        boolean isUpdatingUser = (user.getId() != null);
        User nUser= new User();
        if (isUpdatingUser) {
            User existingUser = userRepo.findById(user.getId()).get();

            if (user.getPassword().isEmpty()) {
                nUser.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }

        } else {
            encodePassword(user);
        }


        nUser.setId(user.getId());
        nUser.setEmail(user.getEmail());
        if(user.getPassword() != null){nUser.setPassword(user.getPassword());};
        nUser.setFirstName(user.getFirstName());
        nUser.setLastName(user.getLastName());
        nUser.setEnabled(user.isEnabled());
        nUser.setPhotos(user.getPhotos());



        return  userRepo.saveAndFlush(nUser);
    }

    public User updateAccount(User userInForm) {
        User userInDB = userRepo.findById(userInForm.getId()).get();

        if (!userInForm.getPassword().isEmpty()) {
            userInDB.setPassword(userInForm.getPassword());
            encodePassword(userInDB);
        }

        if (userInForm.getPhotos() != null) {
            userInDB.setPhotos(userInForm.getPhotos());
        }

        userInDB.setFirstName(userInForm.getFirstName());
        userInDB.setLastName(userInForm.getLastName());

        return userRepo.save(userInDB);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepo.getUserByEmail(email);

        if (userByEmail == null) return true;

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            if (userByEmail != null) return false;
        } else {
            if (userByEmail.getId() != id) {
                return false;
            }
        }

        return true;
    }

    public User get(Integer id) throws UserNotFoundException {
        try {
            return userRepo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepo.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }

        userRepo.deleteById(id);
    }

    public void updateUserEnabledStatus(Integer id, boolean enabled) {
        userRepo.updateEnabledStatus(id, enabled);
    }

}
