package com.odontologica.clinica.service.impl;

import com.odontologica.clinica.entity.ConsultasEntity;
import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.repository.IConsultasRepository;
import com.odontologica.clinica.service.IClinicaService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultasServiceImpl implements IClinicaService<ConsultasEntity> {

    private final IConsultasRepository consultasRepository;
    private PacienteEntity pacienteEntity;
    private DentistaEntity dentistaEntity;

    public ConsultasServiceImpl(IConsultasRepository consultasRepository) {
        this.consultasRepository = consultasRepository;
    }

    @Override
    public ConsultasEntity salvar(ConsultasEntity consultasEntity)  {
        if(consultasEntity != null) {
            return consultasRepository.save(consultasEntity);
        }
        return new ConsultasEntity();
    }

    @Override
    public String alterar(ConsultasEntity consultasEntity) {
        if(consultasEntity != null && consultasRepository.findById(consultasEntity.getId()).isPresent()){
            consultasRepository.saveAndFlush(consultasEntity);
            return "Consulta alterado com sucesso!";
        }
        return "Não foi possível alterar os dados";
    }

    @Override
    public List<ConsultasEntity> buscarTodos() throws SQLException {
        return consultasRepository.findAll();
    }

    @Override
    public Optional<ConsultasEntity> buscarPorId(Long id) throws SQLException {
        return consultasRepository.findById(id);
    }

    @Override
    public boolean excluir(Long id) throws SQLException {
        if(consultasRepository.findById(id).isPresent()){
            consultasRepository.deleteById(id);
            return  true; //"Consulta deletada com sucesso!";
        }
        return false;//"Consulta não encontrada!";
    }
}
