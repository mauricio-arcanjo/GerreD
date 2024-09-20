package com.senac.GerredWeb.service;

import com.senac.GerredWeb.data.ItemCompraEntity;
import com.senac.GerredWeb.data.ItemCompraRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCompraService {
    
    @Autowired
    ItemCompraRepository itemCompraRepository;
    
    public ItemCompraEntity cadastrarItemCompra (ItemCompraEntity i) {
        
        i.setId(null);
        itemCompraRepository.save(i);
        
        return i;
    }
    
    public ItemCompraEntity atualizarItemCompra (Integer id, ItemCompraEntity iRequest) {
        
        ItemCompraEntity i = getItemCompraById(id);
        
        i.setNomeItem(iRequest.getNomeItem());
        i.setValor(iRequest.getValor());
        i.setResponsavel(iRequest.getResponsavel());
        i.setStatus(iRequest.isStatus());
        i.setTipo(iRequest.getTipo());
        
        itemCompraRepository.save(i);
        
        return i;
    }
    
    public ItemCompraEntity getItemCompraById (Integer id) {
        return itemCompraRepository.findById(id).orElse(null);
    }
    
    public List<ItemCompraEntity> listarTodosItens() {
        return itemCompraRepository.findAll();
    }
    
    public void deletarItemCompra (Integer id){
        
        ItemCompraEntity t = getItemCompraById(id);
        itemCompraRepository.deleteById(t.getId());
    }
    
 /*   public List<ItemCompraEntity> listarItensPorUsuarioId (Integer usuarioId) {
        return itemCompraRepository.findByUsuarioId(usuarioId);
    } */
    
}
