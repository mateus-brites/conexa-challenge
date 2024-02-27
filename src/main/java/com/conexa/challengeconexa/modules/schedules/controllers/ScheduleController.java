package com.conexa.challengeconexa.modules.schedules.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conexa.challengeconexa.modules.schedules.dtos.CreateScheduleDTO;
import com.conexa.challengeconexa.modules.schedules.dtos.ScheduleDTO;
import com.conexa.challengeconexa.modules.schedules.entities.ScheduleEntity;
import com.conexa.challengeconexa.modules.schedules.service.ScheduleService;
import com.conexa.challengeconexa.modules.user.entities.UserEntity;

@RestController
@RequestMapping("/api/v1")
public class ScheduleController {
    final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/attendance")
    public ResponseEntity<ScheduleEntity> create(
        @AuthenticationPrincipal UserEntity user, 
        @RequestBody CreateScheduleDTO request) throws ParseException {
            ScheduleEntity scheduleEntity = new ScheduleEntity();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = formatter.parse(request.dataHora());

            System.out.println(user.getId());

            var scheduleDTO = new ScheduleDTO(
                date,
                request.paciente().nome(),
                request.paciente().cpf(),
                user.getId());
            BeanUtils.copyProperties(scheduleDTO, scheduleEntity);
            var newSchedule = scheduleService.create(scheduleEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(newSchedule);

        }
}
