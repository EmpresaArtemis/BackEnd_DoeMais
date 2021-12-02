package br.com.artemis.poctcc.controller.dto.relatorio;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioDoacoesDTO {
    private Long id;
    private Long idItem;
    private String nomeItem;
    private Integer quantidade;
    private Double valor;
    private String dataCriacao;
}
