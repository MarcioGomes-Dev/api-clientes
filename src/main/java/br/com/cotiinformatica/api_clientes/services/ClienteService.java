package br.com.cotiinformatica.api_clientes.services;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.repositories.ClienteRepository;

public class ClienteService {

    public void cadastrarCliente(String nome, String cpf) throws Exception{

        if(nome == null || nome.trim().length() < 6) {
            throw new IllegalArgumentException("O nome do cliente é obirgatório e deve ter pelo menos 6 caracteres.");
        }
        if(cpf == null) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }

        var clienteRepository = new ClienteRepository();
        if(ClienteRepository.cpfExistente(cpf)) {
            throw new IllegalArgumentException("O CPF já está cadastado. Tente outro.");
        }

        var cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);

        clienteRepository.inserir(cliente);
    }
}
