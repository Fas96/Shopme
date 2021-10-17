package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        User user = new User("sdddd.anyass@gmail.com", "1234", "Fas", "Bhim");
        user.addRole( role);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }


    @Test
    public void testCreateFirstUserWithTwoRole() {
        Role roleEd = testEntityManager.find(Role.class, 3);
        Role roleAsis = testEntityManager.find(Role.class, 5);
        User user = new User("anyddddddsdsdsass@gmail.com", "1234", "Fas", "Bhim");
        user.addRoles( Set.of(roleAsis,roleEd));
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
        ur.setFirstName("Changed Fas");
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