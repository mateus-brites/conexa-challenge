package com.conexa.challengeconexa.modules.schedules.dtos;

import java.util.Date;
import java.util.UUID;

public record ScheduleDTO(
    Date dataHora,
    String nome_paciente,
    String cpf_paciente,
    UUID userId
) {
    
}
