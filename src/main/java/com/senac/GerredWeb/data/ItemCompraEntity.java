package com.senac.GerredWeb.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ItemCompra")
public class ItemCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tipo;
    private String nomeItem;
    private double valor;
    
    @ManyToOne
    @JoinColumn(name = "UsuarioId", referencedColumnName = "id")
    private UsuarioEntity responsavel;
    
    private boolean status;

}
