package br.com.projeto.arenapernambuco.util;

public class MaskUtils {

    public static String mascararCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) return cpf;
        return "***." + cpf.substring(3, 6) + ".***.***-**";
    }

    public static String mascararCartao(String numero) {
        if (numero == null || numero.length() != 16) return numero;
        return "**** **** **** " + numero.substring(12);
    }

    public static String mascararNome(String nome) {
        if (nome == null || nome.isBlank()) return nome;
        String[] partes = nome.trim().split(" ");
        if (partes.length == 1) return partes[0].charAt(0) + "***";
        return partes[0] + " " + partes[partes.length - 1].charAt(0) + "***";
    }
}