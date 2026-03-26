package br.com.cotiinformatica.api_clientes.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection getConnection() throws Exception{

        var host = "jdbc:postgresql://localhost:5432/bd-api-clientes";
        var user = "postgres";
        var pass = "coti";

        return DriverManager.getConnection(host, user, pass);
    }
}
