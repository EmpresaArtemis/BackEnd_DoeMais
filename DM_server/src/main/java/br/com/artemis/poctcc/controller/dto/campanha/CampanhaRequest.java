package br.com.artemis.poctcc.controller.dto.campanha;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaRequest {
        private Long id;
        private String nome;
        private String image;
        private Long quantidade;
        private String descricao;
}
