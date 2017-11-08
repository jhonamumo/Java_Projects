/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pragma.conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ConexionDB {
    
    public Connection getConnection() {
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebard", "root", "root");
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch(SQLException sql){
            sql.printStackTrace();
        }
        
        return conn;
    }
}
