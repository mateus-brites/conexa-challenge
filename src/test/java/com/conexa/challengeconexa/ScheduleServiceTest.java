package com.conexa.challengeconexa;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.conexa.challengeconexa.modules.schedules.dtos.ScheduleDTO;
import com.conexa.challengeconexa.modules.schedules.entities.ScheduleEntity;
import com.conexa.challengeconexa.modules.schedules.repositories.ScheduleRepository;
import com.conexa.challengeconexa.modules.schedules.service.ScheduleService;
import com.conexa.challengeconexa.modules.user.dtos.CreateUserDTO;
import com.conexa.challengeconexa.modules.user.entities.UserEntity;
import com.conexa.challengeconexa.modules.user.services.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ScheduleServiceTest {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserService userService;

    UserEntity user;

    @BeforeAll
    public void createUser() {
        UserEntity userEntity = new UserEntity();
        CreateUserDTO userDTO = new CreateUserDTO(
            "testeschedule@gmail.com", 
            "senha",
            "senha",
            "dermatologista",
            "571.988.230-84",
            "12/11/2000",
            "(19) 99887-7665"
            );

            BeanUtils.copyProperties(userDTO, userEntity);

            var newUser = userService.create(userEntity);

            this.user = newUser;
    }

    @Test
    @DisplayName("Shoulde create a new schedule with sucess")
    public void createScheduleWithSucess() throws ParseException {
        ScheduleEntity scheduleEntity = new ScheduleEntity();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date nextDate = calendar.getTime();

        var scheduleDTO = new ScheduleDTO(
                nextDate,
                "Mateus",
                "376.456.090-87",
                user.getId());
            BeanUtils.copyProperties(scheduleDTO, scheduleEntity);
            scheduleService.create(scheduleEntity);

            var size = scheduleRepository.findAll().size();

            Assertions.assertEquals(1, size);

    }

    @Test
    @DisplayName("Shoulde not be able to create a new schedule if the date is before today")
    public void createScheduleWithErroIfDateIsBeforeToday() throws ParseException {
        ScheduleEntity scheduleEntity = new ScheduleEntity();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date nextDate = calendar.getTime();

        var scheduleDTO = new ScheduleDTO(
                nextDate,
                "Mateus",
                "376.456.090-87",
                user.getId());
            BeanUtils.copyProperties(scheduleDTO, scheduleEntity);
            Assertions.assertThrows(Error.class,() -> scheduleService.create(scheduleEntity));
    }

    
}
