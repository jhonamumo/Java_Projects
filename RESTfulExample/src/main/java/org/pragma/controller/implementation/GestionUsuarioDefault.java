/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pragma.controller.implementation;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pragma.conexion.ConexionDB;
import org.pragma.controller.interfece.GestionUsuario;
import org.pragma.dao.Usuario;

/**
 *
 * @author Administrador
 */
public class GestionUsuarioDefault implements GestionUsuario {

    public Usuario validarUsuario(Usuario obj) {
        Usuario usuario = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            conn = new ConexionDB().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM usuario WHERE usuario = ? AND password = ?");
            ps = conn.prepareStatement(sb.toString());
            ps.setString(1, obj.getUsuario());
            ps.setString(2, obj.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){
                usuario = new Usuario();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEdad(rs.getInt("edad"));
            }
            ps.close();
            rs.close();
            conn.close();
        }catch(SQLException sql){
            sql.printStackTrace();
        }
        
        return usuario;
    }
    
}
