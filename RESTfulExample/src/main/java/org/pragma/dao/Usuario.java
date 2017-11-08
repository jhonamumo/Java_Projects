/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pragma.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrador
 */
public class Usuario {
    @NotNull
    @Size(min = 4, max = 30)
    private String usuario;
    
    @NotNull
    @Size(min = 4, max = 30)
    private String password;
    
    private boolean userValido;
    private String nombre;
    private Integer edad;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUserValido() {
        return userValido;
    }

    public void setUserValido(boolean userValido) {
        this.userValido = userValido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
   
}
