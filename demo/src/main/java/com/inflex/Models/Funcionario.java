package com.inflex.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class Funcionario extends Pessoa{

    public Funcionario(String nome, LocalDate dataNascimento,BigDecimal salario, String funcao){
        this.salario = salario;
        this.funcao = funcao;
        super.setNome(nome);
        super.setDataNascimento(dataNascimento);
    }

    private BigDecimal salario;

    private String funcao;
    
}
