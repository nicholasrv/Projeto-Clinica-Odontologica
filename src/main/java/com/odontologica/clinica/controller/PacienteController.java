package com.odontologica.clinica.controller;

import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.exceptions.BadRequestException;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;
import com.odontologica.clinica.service.impl.PacienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class PacienteController {

    private PacienteServiceImpl pacienteService;

    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/paciente/salvar")
    public ResponseEntity<PacienteEntity>salvaPaciente(@RequestBody PacienteEntity pacienteEntity) throws BadRequestException {
        try{
            return ResponseEntity.ok(pacienteService.salvar(pacienteEntity));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/paciente/alterar")
    public ResponseEntity alterarPaciente(@RequestBody PacienteEntity pacienteEntity) throws SQLException {
        return ResponseEntity.ok(pacienteService.alterar(pacienteEntity));
    }

    @RequestMapping(value = "/pacientes", method = RequestMethod.GET, produces = "application/json")
    public List<PacienteEntity> buscarTodos() throws SQLException {
        return pacienteService.buscarTodos();
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity <Optional<PacienteEntity>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            Optional<PacienteEntity> pacienteEntity = pacienteService.buscarPorId(id);
            if (pacienteEntity != null && pacienteEntity.isPresent()) {
                return ResponseEntity.ok(pacienteEntity);
            }
            throw new ResourceNotFoundException("Não foi encontrado o Dentista " + id);
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Erro ao buscar o dentista " + id);
        }
    }



    @DeleteMapping("/paciente/delete/{id}")
    public ResponseEntity excluir(@PathVariable Long id) throws SQLException, ResourceNotFoundException {
        boolean excluiu = pacienteService.excluir(id);
        if (excluiu) {
            return ResponseEntity.ok("Deletado");
        }
        throw new ResourceNotFoundException("Paciente nao encontrado");
    }
}
