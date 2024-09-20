package com.senac.GerredWeb.controller;

import com.senac.GerredWeb.data.TarefaEntity;
import com.senac.GerredWeb.service.TarefaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<TarefaEntity> cadastrarTarefa(@RequestBody TarefaEntity t) {

        var novaTarefa = tarefaService.cadastrarTarefa(t);

        return new ResponseEntity<>(t, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TarefaEntity> atualizarTarefa(@PathVariable Integer id, @RequestBody TarefaEntity t) {

        var tarefaEntity = tarefaService.atualizarTarefa(id, t);

        return new ResponseEntity<>(tarefaEntity, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllTarefas() {

        List<TarefaEntity> tarefas = tarefaService.listarTodasTarefas();

        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    /* @GetMapping("/tarefa/usuario/{usuarioId}")
    public ResponseEntity<List> getTarefasPorUsuario (@PathVariable Integer usuarioId){
        
        List<TarefaEntity> tarefas = tarefaService.listar...
        
        return new ResponseEntity<> (tarefas, HttpStatus.OK);
        
    } */
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<TarefaEntity> getTarefaById(@PathVariable Integer id) {

        TarefaEntity t = tarefaService.getTarefaById(id);

        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarTarefa(@PathVariable Integer id) {

        tarefaService.deletarTarefa(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
