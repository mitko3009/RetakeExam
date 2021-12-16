package com.example.finalproject.repository;

import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRole(Roles role);
}
