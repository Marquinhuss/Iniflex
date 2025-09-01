package com.inflex.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.inflex.Models.Funcionario;

public class Controlador {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    NumberFormat formatoBrasileiro = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public void inserirFuncionarios(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosAux = List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        funcionarios.addAll(funcionariosAux);
    }

    public void removerJoao(List<Funcionario> funcionarios) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
    }

    public void imprimirFuncionarios(List<Funcionario> funcionarios) {
        if(funcionarios.isEmpty()){
            System.out.println("A lista de funcionários está vazia, por favor alimentar a lista");
        }
        for (Funcionario funcionario : funcionarios) {
            String salarioFormatado = formatoBrasileiro.format(funcionario.getSalario());
            System.out.printf("%s, %s, %s, %s%n",
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(formatter),
                    salarioFormatado,
                    funcionario.getFuncao());
        }
    }

    public void aumentarSalario10(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            funcionario.setSalario(funcionario.getSalario()
                    .multiply(BigDecimal.valueOf(1.10)));
        }
    }

    public void imprimirPorFuncao(List<Funcionario> funcionarios, String funcao) {
        List<Funcionario> funcionariosAux = new ArrayList<>();
        /*
         * Iterator<Funcionario> iteradorFuncionarios = funcionarioAux.iterator();
         * while (iteradorFuncionarios.hasNext()) {
         * Funcionario funcionario = iteradorFuncionarios.next();
         * if(funcionario.getFuncao() != funcao){
         * iteradorFuncionarios.remove();
         * }
         * System.out.println(funcionario);
         * }
         */
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getFuncao() == funcao) {
                funcionariosAux.add(funcionario);
            }
        }
        imprimirFuncionarios(funcionariosAux);
    }

    public void imprimirPorData(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosAux = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            int mesNascimento = funcionario.getDataNascimento().getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                funcionariosAux.add(funcionario);
            }
        }
        imprimirFuncionarios(funcionariosAux);
    }

    public void imprimirMaisVelho(List<Funcionario> funcionarios) {
        Map<Integer, Integer> funcionariosIdade = new HashMap<>();

        for (int i = 0; i < funcionarios.size(); i++) {
            Integer anoNascimento = funcionarios.get(i).getDataNascimento().getYear();
            funcionariosIdade.put(i, anoNascimento);
        }
        int menorAno = Collections.min(funcionariosIdade.values());

        int indiceMaisVelho = 0;
        for (Map.Entry<Integer, Integer> entry : funcionariosIdade.entrySet()) {
            if (entry.getValue() == menorAno) {
                indiceMaisVelho = entry.getKey();
                break;
            }
        }
        Funcionario funcionarioMaisVelho = funcionarios.get(indiceMaisVelho);

        System.out.println(funcionarioMaisVelho.getNome());
        System.out.println(2025 - funcionarioMaisVelho.getDataNascimento().getYear());
    }

    public void imprimirOrdemAlfa(List<Funcionario> funcionarios) {
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));
        imprimirFuncionarios(funcionarios);
    }

    public void verificarSalarios(List<Funcionario> funcionarios){
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioFuncionario = funcionario.getSalario();
            totalSalarios = totalSalarios.add(salarioFuncionario);
        }
        String salarioFormatado = formatoBrasileiro.format(totalSalarios);
        System.out.println("Total gasto com salários: " + salarioFormatado);
    }

    public void limparFuncionarios(List<Funcionario> funcionarios){
        funcionarios.clear();
    }

    public void verificaQuantidadeSalarioMinimo(List<Funcionario> funcionarios){
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212);
        BigDecimal salarioFuncionario = BigDecimal.ZERO;
    
        for (Funcionario funcionario : funcionarios) {
            salarioFuncionario = funcionario.getSalario();
            BigDecimal qntdSalariosMinimo = salarioFuncionario.divide(salarioMinimo, 2 , RoundingMode.HALF_EVEN);
            System.out.println(String.format("O Funcionario: %s recebe %s salários mínimos" ,funcionario.getNome(), qntdSalariosMinimo.toString()));
        }
    }

    public void agruparFuncionarios(List<Funcionario> funcionarios){
        HashMap<String, List<Funcionario>> funcionariosAux = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            funcionariosAux.put(funcionario.getFuncao(), funcionarios);
        }
        System.out.println(funcionariosAux);
    }
}
