package br.com.artemis.poctcc.repository.model;

import br.com.artemis.poctcc.repository.model.enums.StatusProposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "instituisao_id", referencedColumnName = "id")
    private Instituicao instituicao;
    @Enumerated(EnumType.STRING)
    private StatusProposta status;
}
