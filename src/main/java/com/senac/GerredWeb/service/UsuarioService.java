package com.senac.GerredWeb.service;

import com.senac.GerredWeb.data.UsuarioEntity;
import com.senac.GerredWeb.data.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioEntity cadastrarUsuario(UsuarioEntity u) {

        u.setId(null);
        usuarioRepository.save(u);

        return u;
    }

    public UsuarioEntity atualizarUsuario(Integer id, UsuarioEntity uRequest) {

        UsuarioEntity u = getUsuarioById(id);

        u.setEmail(uRequest.getEmail());
        u.setNome(uRequest.getNome());
        u.setSenha(uRequest.getSenha());
        u.setTipoUsuario(uRequest.getTipoUsuario());

        usuarioRepository.save(u);

        return u;
    }

    public UsuarioEntity getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<UsuarioEntity> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public void deletarUsuario(Integer id) {

        UsuarioEntity u = getUsuarioById(id);
        usuarioRepository.deleteById(u.getId());
    }

    // Novo método para buscar usuário pelo email
    public UsuarioEntity getUsuarioByEmail(String email) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(email);
        return usuario.orElse(null);  // Retorna o usuário ou null se não for encontrado
    }
}
