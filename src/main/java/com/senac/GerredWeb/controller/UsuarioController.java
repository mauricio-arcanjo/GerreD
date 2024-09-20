package com.senac.GerredWeb.controller;

import com.senac.GerredWeb.service.UsuarioService;
import com.senac.GerredWeb.data.UsuarioEntity;
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
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioEntity> cadastrarUsuario (@RequestBody UsuarioEntity u){
        
        var novoUsuario = usuarioService.cadastrarUsuario(u);
        
        return new ResponseEntity<> (u, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario (@PathVariable Integer id, @RequestBody UsuarioEntity u){
      
        var usuarioAtualizado = usuarioService.atualizarUsuario(id, u);
        
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK );
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.listarTodosUsuarios();
        
        return new ResponseEntity<> (usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable Integer id){
        
        UsuarioEntity u = usuarioService.getUsuarioById(id);
        
        return new ResponseEntity<>(u, HttpStatus.OK);
        
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarUsuario (@PathVariable Integer  id){
        
        usuarioService.deletarUsuario(id);
        
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
