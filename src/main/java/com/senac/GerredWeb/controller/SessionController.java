package com.senac.GerredWeb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessoes")
public class SessionController {

    @GetMapping("/grava")
    public String gravaSessao(HttpServletRequest request, String nome) {
        HttpSession sessao = request.getSession();
        if (sessao != null) {
            sessao.setAttribute("usuario", nome);
            return "Sessão atualizada";
        }
        return "Sem sessão";
    }

    @GetMapping("/le")
    public String leSessao(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao != null) {
            return "Valor da sessão: " + (String) sessao.getAttribute("usuario");
        }
        return "Sem sessão";
    }

    @GetMapping("/exclui")
    public String excluiSessao(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao != null) {
            sessao.removeAttribute("usuario");
        }
        return "Excluído";
    }

}
