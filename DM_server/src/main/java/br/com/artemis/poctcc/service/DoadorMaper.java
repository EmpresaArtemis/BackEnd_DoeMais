package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.doador.DoadorRequest;
import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class DoadorMaper {
    public Doador mapearTabela(DoadorRequest request){
        Doador doador = new Doador();
        Usuario usuario = new Usuario();

        usuario.setPerfil("DOADOR");
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());

        doador.setNome(request.getNome());
        doador.setCpf(request.getCpf());
        doador.setDtNasc(request.getDtNasc());
        doador.setTelefone(request.getTelefone());
        doador.setRua(request.getRua());
        doador.setCep(request.getCep());
        doador.setNumero(request.getNumero());
        doador.setComplemento(request.getComplemento());
        doador.setCidade(request.getCidade());
        doador.setEstado(request.getEstado());
        doador.setUsuario(usuario);

        return doador;
    }

    public Doador mapearTabela(DoadorRequest request, Doador doador){
        doador.setNome(request.getNome());
        doador.setCpf(request.getCpf());
        doador.setDtNasc(request.getDtNasc());
        doador.setTelefone(request.getTelefone());
        doador.setRua(request.getRua());
        doador.setCep(request.getCep());
        doador.setNumero(request.getNumero());
        doador.setComplemento(request.getComplemento());
        doador.setCidade(request.getCidade());
        doador.setEstado(request.getEstado());
        doador.setImage(request.getImage());
        return doador;
    }
}
