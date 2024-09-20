package com.senac.GerredWeb.controller;

import com.senac.GerredWeb.data.UsuarioEntity;
import com.senac.GerredWeb.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AutenticacaoController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("/")
    public String index() {
        return "login";

    }

    @PostMapping("/autentica")
    public String autentica(HttpServletRequest request, UsuarioEntity usuario) {
        HttpSession sessao = request.getSession();
        UsuarioEntity login = usuarioService.getUsuarioByEmail(usuario.getEmail());

        if (login != null) {
            if (sessao != null && usuario.getSenha().equals(login.getSenha())) {
                sessao.setAttribute("usuario", login.getNome());
                return "principal";
            }
        }
        return "login";
    }

    @RequestMapping("/principal")
    public ModelAndView acessaPrincipal(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("usuario") != null) {
            return new ModelAndView("principal");

        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/logoff")
    public ModelAndView fazLogoff(HttpServletRequest request) {
        HttpSession sessao = request.getSession(false);
        if (sessao != null) {
            sessao.removeAttribute("usuario");
            sessao.invalidate();
        }
        return new ModelAndView("redirect:/");
    }

}
