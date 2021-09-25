package com.shopme.admin.user;
import com.shopme.admin.entity.User;
import com.shopme.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testDisable(){
        userRepository.updateEnabledStatus(1,false);
    }
    @Test
    public  void testCountBYId(){
        Long count= userRepository.countById(100);
        assertThat(count).isNotNull();
    }

    @Test
    public  void testGetUserBYEmail(){
        User user = userRepository.getUserByEmail("abac@gmail.com");
        assertThat(user).isNotNull();
    }
    @Test
    public void testCreateFirstUserWithOneRole() {
        Role role = testEntityManager.find(Role.class, 1);
        User user = new User("firibu.anyass@gmail.com", "1234", "Fas", "Bhim");
        user.addRole(role);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }


    @Test
    public void testCreateFirstUserWithTwoRole() {
        Role roleEd = testEntityManager.find(Role.class, 3);
        Role roleAsis = testEntityManager.find(Role.class, 5);
        User user = new User("anyass@gmail.com", "1234", "Fas", "Bhim");
        user.addRole(roleEd);
        user.addRole(roleAsis);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> all = userRepository.findAll();
        System.out.println("==============================start");
        all.forEach(e -> System.out.println(e.toString()));
        System.out.println("==============================end");
    }

    @Test
    public void testGetUsersById() {
        User ur = userRepository.findById(1).get();
        assertThat(ur).isNotNull();
    }

    @Test
    public void testUpdateUserById() {
        User ur = userRepository.findById(1).get();
        ur.setEnabled(true);
        ur.setFirstname("Changed Fas");
        userRepository.save(ur);
    }

    @Test
    public void testUpdateUserRoles() {
        Role roleEd = testEntityManager.find(Role.class, 3);
        Role roleEddd = testEntityManager.find(Role.class, 5);
        User ur = userRepository.findById(2).get();
        ur.getRoles().remove(roleEd);
        ur.addRole(roleEddd);

        userRepository.save(ur);
    }

    @Test
    public void testDeleteUser() {
        userRepository.deleteById((Integer) 2);
    }
}