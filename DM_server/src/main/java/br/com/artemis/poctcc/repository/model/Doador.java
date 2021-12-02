package br.com.artemis.poctcc.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doador {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
//    @Column(name = "dt_nasc")
    private String dtNasc;
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
    private LocalDate dataCriacao;
}
