package com.conexa.challengeconexa.modules.schedules.dtos;

public record CreateScheduleDTO(
    String dataHora,
    PacienteDTO paciente
) {
    
}
