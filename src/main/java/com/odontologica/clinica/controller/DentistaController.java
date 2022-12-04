package com.odontologica.clinica.controller;

import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.exceptions.BadRequestException;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;
import com.odontologica.clinica.service.impl.DentistaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class DentistaController {
    private DentistaServiceImpl dentistaService;

    public DentistaController(DentistaServiceImpl dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping("/dentista/salvar")
    public ResponseEntity<DentistaEntity> salvarDentista(@RequestBody DentistaEntity dentistaEntity) throws BadRequestException {
        try {
            return ResponseEntity.ok(dentistaService.salvar(dentistaEntity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/dentista/alterar")
    public ResponseEntity alterarDentista(@RequestBody DentistaEntity dentistaEntity) throws SQLException {
        return ResponseEntity.ok(dentistaService.alterar(dentistaEntity));
    }

    @RequestMapping(value = "/dentistas", method = RequestMethod.GET, produces = "application/json")
    public List<DentistaEntity> buscarTodos() throws SQLException {
        return dentistaService.buscarTodos();
    }

    @GetMapping("/dentista/{id}")
    public ResponseEntity<Optional<DentistaEntity>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            Optional<DentistaEntity> dentistaEntity = dentistaService.buscarPorId(id);
            if (dentistaEntity != null && dentistaEntity.isPresent()) {
                return ResponseEntity.ok(dentistaEntity);
            }
            throw new ResourceNotFoundException("Não foi encontrado o Dentista " + id);
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Erro ao buscar o dentista " + id);
        }
    }

    @DeleteMapping("/dentista/delete/{id}")
    public ResponseEntity excluirDentista(@PathVariable Long id) throws ResourceNotFoundException, SQLException {
//        try {
            boolean excluiu = dentistaService.excluir(id);
            if (excluiu) {
                return ResponseEntity.ok("Deletado");
            }
//        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o Dentista com o id: " + id);
//        }
    }
}



