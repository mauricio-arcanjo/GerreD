package com.senac.GerredWeb.controller;

import com.senac.GerredWeb.data.UsuarioEntity;
import com.senac.GerredWeb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioFrontEndController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/cadastrar-usuario")
    public String cadastrarUsuarioForm(Model model) {
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute(usuario);
        return "cadastrar-usuario";
    }

    @PostMapping("/cadastrar-usuario")
    public String cadastrarUsuario(@ModelAttribute("usuario") UsuarioEntity u, BindingResult result) {

        if (result.hasErrors()) {
            return "cadastrar-usuario";
        }
        if (u.getId() == null) {
            usuarioService.cadastrarUsuario(u);
        } else {
            usuarioService.atualizarUsuario(u.getId(), u);
            return "usuarios";
        }
        return "login";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {

        model.addAttribute("usuarios", usuarioService.listarTodosUsuarios());

        return "usuarios";
    }

    @GetMapping("/atualizarUsuarioForm/{id}")
    public String atualizarUsuarioForm(@PathVariable(value = "id") Integer id, Model model) {
        UsuarioEntity u = usuarioService.getUsuarioById(id);
        model.addAttribute("usuario", u);
        return "atualizar-usuario";
    }
}
