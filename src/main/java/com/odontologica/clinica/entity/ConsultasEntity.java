package com.odontologica.clinica.entity;

import com.odontologica.clinica.controller.dto.ConsultaRespostaDTO;
import com.odontologica.clinica.controller.dto.DentistaDTO;
import com.odontologica.clinica.controller.dto.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Consultas")
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ConsultasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date dataConsulta;
    private LocalDateTime horaConsulta;

    @ManyToOne
    private DentistaEntity dentista;

    @ManyToOne
    private PacienteEntity paciente;

    public ConsultasEntity(Date dataConsulta, LocalDateTime horaConsulta, DentistaEntity dentista, PacienteEntity paciente) {
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.dentista = dentista;
        this.paciente = paciente;
    }

    public ConsultaRespostaDTO dtoResposta(){
        DentistaDTO dentistaDTO = new DentistaDTO(this.dentista.getNome(), this.dentista.getSobrenome());
        PacienteDTO pacienteDTO = new PacienteDTO(this.paciente.getNome(),this.paciente.getSobrenome());

        return new ConsultaRespostaDTO(
                this.dataConsulta,
                this.horaConsulta,
                dentistaDTO,
                pacienteDTO
        );

    }


}

