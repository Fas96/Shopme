package com.shopme.admin.user;
import static org.assertj.core.api.Assertions.assertThat;
import com.shopme.admin.user.user.RoleRepository;
import com.shopme.common.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleReposityTest {
    @Autowired
    private RoleRepository roleRepository;
    Role role = new Role("Admin","manage everything");

    Role saveRole= roleRepository.save(role);




}
