package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.campanha.CampanhaRequest;
import br.com.artemis.poctcc.repository.model.Campanha;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CampanhaMaper {

    public Campanha mapearTabela(CampanhaRequest request, Usuario usuario){
        Campanha campanha = new Campanha();

        campanha.setNome(request.getNome());
        campanha.setImage(request.getImage());
        campanha.setQuantidade(request.getQuantidade());
        campanha.setDescricao(request.getDescricao());
        campanha.setUsuario(usuario);

        return campanha;
    }

    public Campanha mapearTabela(CampanhaRequest request, Campanha campanha){
        campanha.setNome(request.getNome());
        campanha.setImage(request.getImage());
        campanha.setQuantidade(request.getQuantidade());
        campanha.setDescricao(request.getDescricao());

        return campanha;
    }

}
