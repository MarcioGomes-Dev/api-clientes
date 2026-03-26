package br.com.cotiinformatica.api_clientes.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cliente {

    private Integer id;
    private String nome;
    private String cpf;
    private List<Endereco> enderecos;

}
