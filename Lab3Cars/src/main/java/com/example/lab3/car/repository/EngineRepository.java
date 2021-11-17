package com.example.lab3.car.repository;

import com.example.lab3.car.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, String> {
}
