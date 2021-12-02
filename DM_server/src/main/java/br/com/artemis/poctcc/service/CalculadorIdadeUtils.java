package br.com.artemis.poctcc.service;

import java.time.LocalDateTime;
import java.time.Period;


public class CalculadorIdadeUtils {

    public static  Long calcularIdade(LocalDateTime data){
        LocalDateTime idade = LocalDateTime.now();
        long anos = Period.between(data.toLocalDate(), idade.toLocalDate()).getYears();
        return anos;
    }
}
