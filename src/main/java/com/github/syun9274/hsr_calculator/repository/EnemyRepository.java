package com.github.syun9274.hsr_calculator.repository;

import com.github.syun9274.hsr_calculator.model.entity.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Long> {
}
