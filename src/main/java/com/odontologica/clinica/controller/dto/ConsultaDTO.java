package com.odontologica.clinica.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataConsulta;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime horaConsulta;

    private Long idDentista;
    private Long idPaciente;
}
