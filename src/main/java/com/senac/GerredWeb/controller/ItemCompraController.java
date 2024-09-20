package com.senac.GerredWeb.controller;

import com.senac.GerredWeb.data.ItemCompraEntity;
import com.senac.GerredWeb.service.ItemCompraService;
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
@RequestMapping("/item")
public class ItemCompraController {

    @Autowired
    ItemCompraService itemCompraService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ItemCompraEntity> cadastrarItemCompra(@RequestBody ItemCompraEntity i) {

        var novoItemCompra = itemCompraService.cadastrarItemCompra(i);

        return new ResponseEntity<>(i, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ItemCompraEntity> atualizarItemCompra(@PathVariable Integer id, @RequestBody ItemCompraEntity i) {

        var itemAtualizado = itemCompraService.atualizarItemCompra(id, i);

        return new ResponseEntity<>(itemAtualizado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> getAllItensCompra() {

        List<ItemCompraEntity> itens = itemCompraService.listarTodosItens();

        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /* @GetMapping("/tarefa/usuario/{usuarioId}")
    public ResponseEntity<List> getItensCompraPorUsuario (@PathVariable Integer usuarioId){
        
        List<ItemCompraEntity> tarefas = itemCompraService.listar...
        
        return new ResponseEntity<> (tarefas, HttpStatus.OK);
        
    } */
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<ItemCompraEntity> getItemCompraById(@PathVariable Integer id) {

        ItemCompraEntity i = itemCompraService.getItemCompraById(id);

        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarItemCompra(@PathVariable Integer id) {

        itemCompraService.deletarItemCompra(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
