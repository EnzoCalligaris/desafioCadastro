package util;

import java.util.regex.Pattern;

public class Validador {
    private static final String NAO_INFORMADO = "NÃO INFORMADO";

    public static String validarNome(String nome){
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome do Pet é obrigatório.");
        }

        if (!Pattern.matches("^[A-Za-z ]+$", nome)) {
            throw new IllegalArgumentException("⚠ O nome só pode conter letras de A-Z!");
        }

        if (!nome.contains(" ")){
            throw new IllegalArgumentException("O nome deve conter nome e sobrenome!");
        }

        return nome;
    }

    public static double validarPeso(double peso){
        if (peso < 0.5 || peso > 60.0){
            throw new IllegalArgumentException("O peso deve estar entre 0.5kg e 60.0kg.");

        }
        return peso;
    }

    public static int validarIdade(double idade){
        if (idade > 20){
            throw new IllegalArgumentException("Idade inválida! Máximo 20 anos.");
        }
        if (idade < 1){
            return 0;
        }
        return (int)idade;
    }

    public static String validarTexto(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return NAO_INFORMADO;
        }
        if (!Pattern.matches("^[A-Za-z ]+$", valor)) {
            throw new IllegalArgumentException("⚠ O campo só pode conter letras de A-Z!");
        }
        return valor;
    }

    public static String validarEndereco(String numero, String rua, String cidade){
        if (numero == null || numero.trim().isEmpty()){
            numero = NAO_INFORMADO;
        }
        if (rua == null || rua.trim().isEmpty()){
            rua = NAO_INFORMADO;
        }
        if (cidade == null || cidade.trim().isEmpty()){
            cidade = NAO_INFORMADO;
        }
        return "Nº " + numero + ", " + rua + ", " + cidade;
    }
}
