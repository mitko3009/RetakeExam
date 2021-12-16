package com.example.finalproject.repository;

import com.example.finalproject.model.entity.LaptopEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEnity,Long> {

    Optional<LaptopEnity> findById(Long id);
}
