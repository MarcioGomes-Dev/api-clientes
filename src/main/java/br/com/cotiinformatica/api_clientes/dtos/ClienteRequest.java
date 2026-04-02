package br.com.cotiinformatica.api_clientes.dtos;

public record ClienteRequest(
        String nome,
        String cpf
) {
}
