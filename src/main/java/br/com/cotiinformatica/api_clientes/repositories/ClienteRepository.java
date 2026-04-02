package br.com.cotiinformatica.api_clientes.repositories;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    public void inserir(Cliente cliente) throws Exception{

        try(var connection = ConnectionFactory.getConnection()){

            var statement =  connection.prepareStatement("INSERT INTO CLIENTES(NOME, CPF) VALUES(?,?)");
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.execute();
        }
    }

   public static boolean cpfExistente(String cpf) throws Exception{
        try(var connection = ConnectionFactory.getConnection()){
            var statement = connection.prepareStatement("SELECT COUNT(*) AS QTD FROM CLIENTES WHERE CPF = ?");
            statement.setString(1, cpf);
            var result = statement.executeQuery();
            if(result.next()) {
                return result.getInt("QTD") == 1;
            }

            return false;
        }
    }

    public List<Cliente> listar(String nome) throws Exception {
        try(var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM CLIENTES WHERE NOME ILIKE ? ORDER BY NOME");
            statement.setString(1, "%" + nome + "%");
            var result = statement.executeQuery();

            var lista = new ArrayList<Cliente>();

            while (result.next()){
                var cliente = new Cliente();
                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));

                lista.add(cliente);
            }

            return lista;
        }
    }
}
