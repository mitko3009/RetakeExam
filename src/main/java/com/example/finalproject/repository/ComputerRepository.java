package com.example.finalproject.repository;

import com.example.finalproject.model.entity.ComputerEntity;
import com.example.finalproject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<ComputerEntity,Long> {

    Optional<ComputerEntity> findById(Long id);
}
