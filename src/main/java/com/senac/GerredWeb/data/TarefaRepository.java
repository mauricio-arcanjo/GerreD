package com.senac.GerredWeb.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Integer> {

    // Método para buscar pelo ID do usuario
 //   List<TarefaEntity> findByUsuarioId(Integer UsuarioId);

}
