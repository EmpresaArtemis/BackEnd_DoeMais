package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.item.ItemRequest;
import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ItemMaper {

    public Item mapearTabela(ItemRequest request, Usuario usuario){
        Item item = new Item();

        item.setNome(request.getNome());
        item.setImage(request.getImage());
        item.setQuantidade(request.getQuantidade());
        item.setDescricao(request.getDescricao());
        item.setUsuario(usuario);

        return item;
    }

    public Item mapearTabela(ItemRequest request, Item item){
        item.setNome(request.getNome());
        item.setImage(request.getImage());
        item.setQuantidade(request.getQuantidade());
        item.setDescricao(request.getDescricao());

        return item;
    }
}
