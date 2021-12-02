package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.LoginResponse;
import br.com.artemis.poctcc.repository.model.Admin;
import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.repository.model.Instituicao;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class LoginMaper {

    public LoginResponse mapear(Doador doador){
        return LoginResponse
                .builder()
                .entityId(doador.getId())
                .nome(doador.getNome())
                .cpf(doador.getCpf())
                .dtNasc(doador.getDtNasc())
                .telefone(doador.getTelefone())
                .rua(doador.getRua())
                .numero(doador.getNumero())
                .complemento(doador.getComplemento())
                .estado(doador.getEstado())
                .cep(doador.getCep())
                .cidade(doador.getCidade())
                .email(doador.getUsuario().getEmail())
                .tipo(doador.getUsuario().getPerfil())
                .build();
//        .format(DateTimeFormatter.BASIC_ISO_DATE)

    }
    public LoginResponse mapear(Instituicao instituicao){
        return LoginResponse
                .builder()
                .entityId(instituicao.getId())
                .nomeFantasia(instituicao.getNomeFantasia())
                .razaoSocial(instituicao.getRazaoSocial())
                .focoInstitucional(instituicao.getFocoInstitucional())
                .cnpj(instituicao.getCnpj())
                .dtFundacao(instituicao.getDtFundacao())
                .telefone(instituicao.getTelefone())
                .rua(instituicao.getRua())
                .numero(instituicao.getNumero())
                .complemento(instituicao.getComplemento())
                .estado(instituicao.getEstado())
                .cep(instituicao.getCep())
                .cidade(instituicao.getCidade())
                .email(instituicao.getUsuario().getEmail())
                .tipo(instituicao.getUsuario().getPerfil())
                .build();

    }

    public LoginResponse mapear(Admin admin) {
        return LoginResponse
                .builder()
                .entityId(admin.getId())
                .email(admin.getUsuario().getEmail())
                .tipo(admin.getUsuario().getPerfil())
                .image(admin.getUsuario().getImage())
                .build();
    }
}
