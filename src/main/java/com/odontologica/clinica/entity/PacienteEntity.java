package com.odontologica.clinica.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Paciente")
public class PacienteEntity {

    @Id
    @SequenceGenerator(name = "paciente_sequence",sequenceName = "paciente_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_generator")
    private Long id;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String rg;
    private Date dataAlta;

    public PacienteEntity(String nome, String sobrenome, String endereco, String rg) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.rg = rg;
    }
}

