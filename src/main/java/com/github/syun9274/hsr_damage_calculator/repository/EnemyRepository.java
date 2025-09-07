package com.github.syun9274.hsr_damage_calculator.repository;

import com.github.syun9274.hsr_damage_calculator.model.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Long> {
}
