package com.inflex.Models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Pessoa {
    
    protected String nome;

    protected LocalDate dataNascimento;
}
