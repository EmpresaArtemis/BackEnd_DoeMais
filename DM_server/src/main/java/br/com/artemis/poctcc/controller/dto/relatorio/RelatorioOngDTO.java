package br.com.artemis.poctcc.controller.dto.relatorio;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioOngDTO {

    private Long id;
    private Long idInstituicao;
    private String nomeOng;
    private String razaoSocial;
    private String cnpj;
    private String categoria;
}
