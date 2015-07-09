/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tiago
 */
public class ConectaPostgreSQL {

    public static Connection geraConexao() {
        Connection conexao = null;
        try {
//carrega o drive de comunicação com o banco de dados
            Class.forName("org.postgresql.Driver");
//Não esqueca de mudar o usuario e senha do banco de dados.
            String url = "jdbc:postgresql://localhost:5432/DW_Roteiro_JSF";
            String usuario = "postgres";
            String senha = "123";
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro de SQL. Erro: " + e);
        } catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        return conexao;
    }
}
