package com.odontologica.clinica.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Dentista")
public class DentistaEntity {

    @Id
    @SequenceGenerator(name = "dentista_sequence",sequenceName = "dentista_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_generator")
    private Long id;
    private String nome;
    private String sobrenome;
    private String matricula;

    public DentistaEntity(String nome, String sobrenome, String matricula) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = matricula;
    }
}
