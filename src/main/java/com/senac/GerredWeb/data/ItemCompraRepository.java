package com.senac.GerredWeb.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompraEntity, Integer> {

    // Método para buscar pelo ID do usuario
//    List<ItemCompraEntity> findByUsuarioId (Integer UsuarioId);
}
