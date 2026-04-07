package br.com.cotiinformatica.api_clientes.repositories;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    private ConnectionFactory connectionFactory;

    public void inserir(Cliente cliente) throws Exception{

        try(var connection = connectionFactory.getConnection()){

            connection.setAutoCommit(false);

            var statement =  connection.prepareStatement("INSERT INTO CLIENTES(NOME, CPF) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.execute();

            var generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                cliente.setId(generatedKeys.getInt(1));
            }

            if (cliente.getEnderecos() != null){
                for (var endereco : cliente.getEnderecos()){
                    statement = connection.prepareStatement("""
                        INSERT INTO ENDERECOS(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, CLIENTE_ID)
                        VALUES(?,?,?,?,?,?,?,?)
                    """);
                    statement.setString(1,endereco.getLogradouro());
                    statement.setString(2,endereco.getNumero());
                    statement.setString(3,endereco.getComplemento());
                    statement.setString(4,endereco.getBairro());
                    statement.setString(5,endereco.getCidade());
                    statement.setString(6,endereco.getUf());
                    statement.setString(7,endereco.getCep());
                    statement.setInt(8,cliente.getId());
                    statement.execute();
                }
            }

            connection.commit();
        }
    }

   public boolean cpfExistente(String cpf) throws Exception{
        try(var connection = connectionFactory.getConnection()){
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
        try(var connection = connectionFactory.getConnection()) {
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
