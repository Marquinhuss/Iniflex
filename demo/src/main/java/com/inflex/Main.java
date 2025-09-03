package com.inflex;

import java.util.ArrayList;
import java.util.List;

import com.inflex.Controller.Controlador;
import com.inflex.Models.Funcionario;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Controlador controlador = new Controlador();
        List<Funcionario> funcionarios = new ArrayList<>();
    
        controlador.inserirFuncionarios(funcionarios);

        controlador.imprimirFuncionarios(funcionarios);
        System.out.println("------------------");

        controlador.removerJoao(funcionarios);

        controlador.imprimirFuncionarios(funcionarios);
        System.out.println("------------------");

        controlador.aumentarSalario10(funcionarios);

        controlador.imprimirFuncionarios(funcionarios);
        System.out.println("------------------");

        controlador.imprimirPorFuncao(funcionarios, "Operador");

        System.out.println("------------------");

        controlador.imprimirPorData(funcionarios);

        System.out.println("------------------");

        controlador.imprimirMaisVelho(funcionarios);
    
        System.out.println("------------------");

        controlador.imprimirOrdemAlfa(funcionarios);

        System.out.println("------------------");
        controlador.limparFuncionarios(funcionarios);

        controlador.inserirFuncionarios(funcionarios);

        controlador.verificarSalarios(funcionarios);

        System.out.println("------------------");

        controlador.verificaQuantidadeSalarioMinimo(funcionarios);

        System.out.println("------------------");

        controlador.agruparFuncionarios(funcionarios);
    }
}