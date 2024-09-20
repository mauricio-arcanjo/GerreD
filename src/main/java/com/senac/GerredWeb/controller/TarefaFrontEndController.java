package com.senac.GerredWeb.controller;

import com.senac.GerredWeb.data.TarefaEntity;
import com.senac.GerredWeb.service.TarefaService;
import com.senac.GerredWeb.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TarefaFrontEndController {

    @Autowired
    TarefaService tarefaService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/cadastrar-tarefa")
    public String cadastrarTarefaForm(Model model) {
        TarefaEntity tarefa = new TarefaEntity();
        model.addAttribute("tarefa", tarefa);
        model.addAttribute("usuarios", usuarioService.listarTodosUsuarios()); // Adiciona a lista de usuários
        return "cadastrar-tarefa";
    }

    @PostMapping("/cadastrar-tarefa")
    public String cadastrarTarefa(@ModelAttribute("tarefa") @Valid TarefaEntity tarefa, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastrar-tarefa";
        }
        tarefaService.cadastrarTarefa(tarefa);
        return "tarefas"; // Redireciona para a página de tarefas após o cadastro
    }

    @GetMapping("/tarefas")
    public String listarTarefas(Model model) {
        model.addAttribute("tarefas", tarefaService.listarTodasTarefas());
        return "tarefas"; // Retorna a página de tarefas
    }

    @GetMapping("/atualizarTarefaForm/{id}")
    public String atualizarTarefaForm(@PathVariable(value = "id") Integer id, Model model) {
        TarefaEntity tarefa = tarefaService.getTarefaById(id);
        model.addAttribute("tarefa", tarefa);
        model.addAttribute("usuarios", usuarioService.listarTodosUsuarios()); // Adiciona a lista de usuários
        return "atualizar-tarefa"; // Pode usar a mesma página de cadastro para atualizar
    }

    @PostMapping("/atualizar-tarefa/{id}")
    public String atualizarTarefa(@PathVariable(value = "id") Integer id, @ModelAttribute("tarefa") @Valid TarefaEntity tarefa, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastrar-tarefa";
        }
        tarefaService.atualizarTarefa(id, tarefa);
        return "tarefas"; // Redireciona para a página de tarefas após a atualização
    }
}
