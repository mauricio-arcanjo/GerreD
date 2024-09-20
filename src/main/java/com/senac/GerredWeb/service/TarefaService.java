package com.senac.GerredWeb.service;

import com.senac.GerredWeb.data.TarefaEntity;
import com.senac.GerredWeb.data.TarefaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {
    
    @Autowired
    TarefaRepository tarefaRepository;
    
    public TarefaEntity cadastrarTarefa (TarefaEntity t) {
        
        t.setId(null);
        tarefaRepository.save(t);
        
        return t;
    }
    
    public TarefaEntity atualizarTarefa (Integer id, TarefaEntity tRequest) {
        
        TarefaEntity t = getTarefaById(id);
        
        t.setData(tRequest.getData());
        t.setDescricao(tRequest.getDescricao());
        t.setInstrucoes(tRequest.getInstrucoes());
        t.setResponsavel(tRequest.getResponsavel());
        t.setStatus(tRequest.isStatus());
        t.setTipo(tRequest.getTipo());
        
        tarefaRepository.save(t);
        
        return t;
    }
    
    public TarefaEntity getTarefaById (Integer id) {
        return tarefaRepository.findById(id).orElse(null);
    }
    
    public List<TarefaEntity> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }
    
    public void deletarTarefa (Integer id){
        
        TarefaEntity t = getTarefaById(id);
        tarefaRepository.deleteById(t.getId());
    }
    
 /*   public List<TarefaEntity> listarTarefasPorUsuarioId (Integer usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    } */
    
}
