package com.senac.GerredWeb.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Tarefa")
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tipo;
    private String descricao;
    private String instrucoes;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "UsuarioId", referencedColumnName = "id")
    private UsuarioEntity responsavel;

    private boolean status;

}
