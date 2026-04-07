package br.com.cotiinformatica.api_clientes.services;

import br.com.cotiinformatica.api_clientes.dtos.ClienteRequest;
import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.entities.Endereco;
import br.com.cotiinformatica.api_clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void cadastrarCliente(ClienteRequest request) throws Exception{

        if(request.nome() == null || request.nome().trim().length() < 6) {
            throw new IllegalArgumentException("O nome do cliente é obirgatório e deve ter pelo menos 6 caracteres.");
        }
        if(request.cpf() == null) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        if(request.enderecos() == null || request.enderecos().length ==0){
            throw new IllegalArgumentException("O cliente deve ter pelo menos 1 endereço para ser cadastrado.");
        }
        if(clienteRepository.cpfExistente(request.cpf())) {
            throw new IllegalArgumentException("O CPF já está cadastado. Tente outro.");
        }

        var cliente = new Cliente();
        cliente.setEnderecos(new ArrayList<>());
        cliente.setNome(request.nome());
        cliente.setCpf(request.cpf());

        for(var item : request.enderecos()){
            var endereco = new Endereco();
            endereco.setLogradouro(item.logradouro());
            endereco.setNumero(item.numero());
            endereco.setComplemento(item.complemento());
            endereco.setBairro(item.bairro());
            endereco.setCidade(item.cidade());
            endereco.setUf(item.uf());
            endereco.setCep(item.cep());

            cliente.getEnderecos().add(endereco);
        }

        clienteRepository.inserir(cliente);
    }

    public List<Cliente> pesquisarClientes(String nome) throws Exception {
        if (nome == null || nome.trim().length() < 5) {
            throw new IllegalArgumentException("O nome do cliente para pesquisa deve ter pelo menos  caracteres.");
        }

        var lista = clienteRepository.listar(nome);

        return lista;
    }
}
