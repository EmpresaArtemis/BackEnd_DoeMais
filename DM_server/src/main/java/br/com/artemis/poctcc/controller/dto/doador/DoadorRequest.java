package br.com.artemis.poctcc.controller.dto.doador;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoadorRequest {
    private String nome;
    private String cpf;
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @JsonSerialize(using = LocalDateSerializer.class)
    private String dtNasc;
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
//    public LocalDateTime getDtNasc() {
//        return this.dtNasc.atTime(0,0);
//    }
}
