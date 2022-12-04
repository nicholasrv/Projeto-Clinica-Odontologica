package com.odontologica.clinica.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaRespostaDTO {

    private Date dataConsulta;
    private LocalDateTime horaConsulta;
    private DentistaDTO dentista;
    private PacienteDTO paciente;

    public ConsultaRespostaDTO(LocalDate dataConsulta, LocalDateTime horaConsulta, DentistaDTO dentistaDTO, PacienteDTO pacienteDTO) {
    }
}
