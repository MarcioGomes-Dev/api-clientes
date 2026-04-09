package br.com.cotiinformatica.api_clientes.entities;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "nome",
        "cpf",
        "enderecos"
})
public class Cliente {

    private Integer id;
    private String nome;
    private String cpf;
    private List<Endereco> enderecos;

}
