package br.com.cotiinformatica.api_clientes.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Endereco {

    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private Cliente cliente;
}
