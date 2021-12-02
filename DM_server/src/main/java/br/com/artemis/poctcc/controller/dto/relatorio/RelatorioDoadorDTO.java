package br.com.artemis.poctcc.controller.dto.relatorio;


import br.com.artemis.poctcc.service.CalculadorIdadeUtils;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RelatorioDoadorDTO {
    private Long id;
    private Long idDoador;
    private LocalDateTime dtNasc;
    private String cpf;
    private String nomeDoador;
    public Long getIdade() {
        return CalculadorIdadeUtils.calcularIdade(this.dtNasc);

    }
}
