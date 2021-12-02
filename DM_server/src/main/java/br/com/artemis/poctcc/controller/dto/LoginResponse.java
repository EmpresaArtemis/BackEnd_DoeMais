package br.com.artemis.poctcc.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private Long entityId ;
    private String nomeFantasia;
    private String razaoSocial;
    private String focoInstitucional;
    private String cnpj;
    private String dtFundacao;
    private String telefone;
    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    private String email;
    private String tipo;
    private String image;
    private String nome;
    private String cpf;
    private String dtNasc;


}
