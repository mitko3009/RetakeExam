package com.example.finalproject.model.entity;

import com.example.finalproject.model.entity.baseEntity.BaseEntity;
import com.example.finalproject.model.enums.Roles;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
