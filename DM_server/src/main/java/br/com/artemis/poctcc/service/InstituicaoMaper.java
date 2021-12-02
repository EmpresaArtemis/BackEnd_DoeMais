package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.instituicao.InstituicaoResquest;
import br.com.artemis.poctcc.repository.model.Instituicao;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InstituicaoMaper {

    public Instituicao mapearTabela (InstituicaoResquest request){
        Instituicao instituicao = new Instituicao();
        Usuario usuario = new Usuario();

        usuario.setPerfil("INSTITUICAO");
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());

        instituicao.setNomeFantasia(request.getNomeFantasia());
        instituicao.setRazaoSocial(request.getRazaoSocial());
        instituicao.setFocoInstitucional(request.getFocoInstitucional());
        instituicao.setCnpj(request.getCnpj());
        instituicao.setDtFundacao(request.getDtFundacao());
        instituicao.setTelefone(request.getTelefone());
        instituicao.setRua(request.getRua());
        instituicao.setNumero(request.getNumero());
        instituicao.setComplemento(request.getComplemento());
        instituicao.setCidade(request.getCidade());
        instituicao.setEstado(request.getEstado());
        instituicao.setCep(request.getCep());
        instituicao.setDataCriacao(LocalDateTime.now());
        instituicao.setUsuario(usuario);

        return instituicao;
    }

    public Instituicao mapearTabela(InstituicaoResquest request, Instituicao instituicao) {
        instituicao.setNomeFantasia(request.getNomeFantasia());
        instituicao.setRazaoSocial(request.getRazaoSocial());
        instituicao.setFocoInstitucional(request.getFocoInstitucional());
        instituicao.setCnpj(request.getCnpj());
        instituicao.setDtFundacao(request.getDtFundacao());
        instituicao.setTelefone(request.getTelefone());
        instituicao.setRua(request.getRua());
        instituicao.setNumero(request.getNumero());
        instituicao.setComplemento(request.getComplemento());
        instituicao.setCidade(request.getCidade());
        instituicao.setEstado(request.getEstado());
        instituicao.setCep(request.getCep());
        instituicao.setImage(request.getImage());

        return instituicao;
    }
}
