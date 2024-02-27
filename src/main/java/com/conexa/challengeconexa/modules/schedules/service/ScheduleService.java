package com.conexa.challengeconexa.modules.schedules.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.conexa.challengeconexa.modules.schedules.entities.ScheduleEntity;
import com.conexa.challengeconexa.modules.schedules.repositories.ScheduleRepository;

@Service
public class ScheduleService {
    final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleEntity create(ScheduleEntity scheduleEntity) {
        if(!scheduleEntity.getDataHora().after(new Date())) {
            throw new Error("A data precisa ser posteriro ao dia de hoje");
        }
        return scheduleRepository.save(scheduleEntity);
    }
}
