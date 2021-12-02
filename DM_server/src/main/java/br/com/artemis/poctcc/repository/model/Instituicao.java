package br.com.artemis.poctcc.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instituicao {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long Id;
    private String nomeFantasia;
    private String razaoSocial;
    private String focoInstitucional;
    @Column (unique = true)
    private String cnpj;
    private String dtFundacao;
    private String telefone;
    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    @Column(length = 200000)
    private String image;
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Usuario usuario;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

}
