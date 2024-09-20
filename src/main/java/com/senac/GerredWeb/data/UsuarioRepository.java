package com.senac.GerredWeb.data;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // Método personalizado para buscar usuário pelo email
    Optional<UsuarioEntity> findByEmail(String email);

}
