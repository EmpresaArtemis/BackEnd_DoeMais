package br.com.artemis.poctcc.controller.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    private Long id;
    private String nome;
    private String image;
    private Integer quantidade;
    private String descricao;
    private Long idInstituicao;
}
