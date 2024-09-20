package com.senac.GerredWeb.controller;

import com.senac.GerredWeb.data.ItemCompraEntity;
import com.senac.GerredWeb.service.ItemCompraService;
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
public class ItemCompraFrontEndController {

    @Autowired
    ItemCompraService itemCompraService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/cadastrar-item-compra")
    public String cadastrarItemCompraForm(Model model) {
        ItemCompraEntity item = new ItemCompraEntity();
        model.addAttribute("item", item);
        model.addAttribute("usuarios", usuarioService.listarTodosUsuarios());
        return "cadastrar-item-compra";
    }

    @PostMapping("/cadastrar-item-compra")
    public String cadastrarItemCompra(@ModelAttribute("item") ItemCompraEntity item, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastrar-item-compra";
        }
        if (item.getId() == null) {
            itemCompraService.cadastrarItemCompra(item);
        } else {
            itemCompraService.atualizarItemCompra(item.getId(), item);
            return "itens";
        }
        return "redirect:/itens";
    }

    @GetMapping("/itens")
    public String listarItens(Model model) {
        model.addAttribute("itens", itemCompraService.listarTodosItens());
        return "itens";
    }

    @GetMapping("/atualizarItemCompraForm/{id}")
    public String atualizarItemCompraForm(@PathVariable(value = "id") Integer id, Model model) {
        ItemCompraEntity item = itemCompraService.getItemCompraById(id);
        model.addAttribute("item", item);
        model.addAttribute("usuarios", usuarioService.listarTodosUsuarios());
        return "atualizar-item-compra";
    }
}
