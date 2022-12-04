package com.odontologica.clinica.controller;

import com.odontologica.clinica.controller.dto.ConsultaDTO;
import com.odontologica.clinica.controller.dto.ConsultaRespostaDTO;
import com.odontologica.clinica.entity.ConsultasEntity;
import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;
import com.odontologica.clinica.service.impl.ConsultasServiceImpl;
import com.odontologica.clinica.service.impl.DentistaServiceImpl;
import com.odontologica.clinica.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsultaController {

    @Autowired
    private ConsultasServiceImpl consultasService;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private DentistaServiceImpl dentistaService;

    @PostMapping("consultas/salvar")
    @Transactional
    public ConsultaRespostaDTO salvarConsulta(@RequestBody ConsultaDTO consultaDTO) throws SQLException {
        PacienteEntity paciente = pacienteService.buscarPorId(consultaDTO.getIdPaciente()).orElse(null);
        DentistaEntity dentista = dentistaService.buscarPorId(consultaDTO.getIdDentista()).orElse(null);

        ConsultasEntity consulta = new ConsultasEntity(consultaDTO.getDataConsulta(),consultaDTO.getHoraConsulta(),
                dentista, paciente);

        consultasService.salvar(consulta);

        return consulta.dtoResposta();
    }

    @PutMapping("/consultas/alterar")
    public ResponseEntity alterarConsulta(@RequestBody ConsultasEntity consultasEntity) throws SQLException {
        return ResponseEntity.ok(consultasService.alterar(consultasEntity));
    }

    @RequestMapping(value = "/consultas", method = RequestMethod.GET, produces = "application/json")
    public List<ConsultasEntity> buscarTodos() throws SQLException {


        return consultasService.buscarTodos();
    }

    @GetMapping("/consultas/{id}")
    public ResponseEntity <Optional<ConsultasEntity>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        try{
            return ResponseEntity.ok(consultasService.buscarPorId(id));

        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi localizado a Consulta " + id);
        }
    }


    @DeleteMapping("/consultas/delete/{id}")
    public ResponseEntity excluir(@PathVariable Long id) throws SQLException, ResourceNotFoundException {
        boolean excluiu = consultasService.excluir(id);
        if (excluiu) {
            return ResponseEntity.ok("Deletado");
        }
        throw new ResourceNotFoundException("Consulta nao localizada");
    }

}




