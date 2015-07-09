/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bassini
 */
public class DB {

    public static PreparedStatement prepare( String sql) throws SQLException {
        Connection conexao = ConectaPostgreSQL.geraConexao();
        return conexao.prepareStatement(sql);
    }

    public static void cleanUp(PreparedStatement stmt, ResultSet rs)  {
        
        try{
        rs.close();
        stmt.close();
        }
        catch(Exception e){
            System.out.println("Erro:" + e.getMessage());
        }
    }

    public static void cleanUp(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
