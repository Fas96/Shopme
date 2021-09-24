package com.shopme.admin.formatter;

import com.shopme.common.entity.Role;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {

    @Override
    public Role parse(String s, Locale locale) throws ParseException {
        Role r= new Role();
        r.setName(s);
        return r;
    }

    @Override
    public String print(Role role, Locale locale) {
        return null;
    }
}
