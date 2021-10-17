package com.shopme.common.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRole implements Serializable {
    private Integer user_id;
    private Integer role_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(getUser_id(), userRole.getUser_id()) && Objects.equals(getRole_id(), userRole.getRole_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getRole_id());
    }
}
