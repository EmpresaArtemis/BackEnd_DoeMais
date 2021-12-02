package br.com.artemis.poctcc.controller.dto.instituicao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoResquest {
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
    private String image;
    private String email;
    private String senha;
}
