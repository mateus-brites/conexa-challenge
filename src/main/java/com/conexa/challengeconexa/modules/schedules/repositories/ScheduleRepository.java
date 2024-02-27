package com.conexa.challengeconexa.modules.schedules.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conexa.challengeconexa.modules.schedules.entities.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, UUID> {
    
}
