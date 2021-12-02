package br.com.artemis.poctcc.controller.dto.proposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropostaRequest {
    private Long idItem;
    private Long idInstituicao;
}
