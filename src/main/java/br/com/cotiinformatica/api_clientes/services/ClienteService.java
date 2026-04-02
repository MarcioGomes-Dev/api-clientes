package br.com.cotiinformatica.api_clientes.services;

import br.com.cotiinformatica.api_clientes.dtos.ClienteRequest;
import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.repositories.ClienteRepository;

import java.util.List;

public class ClienteService {

    public void cadastrarCliente(ClienteRequest request) throws Exception{

        if(request.nome() == null || request.nome().trim().length() < 6) {
            throw new IllegalArgumentException("O nome do cliente é obirgatório e deve ter pelo menos 6 caracteres.");
        }
        if(request.cpf() == null) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }

        var clienteRepository = new ClienteRepository();
        if(ClienteRepository.cpfExistente(request.cpf())) {
            throw new IllegalArgumentException("O CPF já está cadastado. Tente outro.");
        }

        var cliente = new Cliente();
        cliente.setNome(request.nome());
        cliente.setCpf(request.cpf());

        clienteRepository.inserir(cliente);
    }

    public List<Cliente> pesquisarClientes(String nome) throws Exception {
        if (nome == null || nome.trim().length() < 5) {
            throw new IllegalArgumentException("O nome do cliente para pesquisa deve ter pelo menos  caracteres.");
        }

        var clienteRepository = new ClienteRepository();
        var lista = clienteRepository.listar(nome);

        return lista;
    }
}
