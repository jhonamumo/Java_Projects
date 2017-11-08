/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pragma.service;

import java.io.IOException;
import java.util.Set; 
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.pragma.controller.implementation.GestionUsuarioDefault;
import org.pragma.controller.interfece.GestionUsuario;
import org.pragma.dao.Usuario;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.pragma.dao.ListaUsuarios;

/**
 *
 * @author Administrador
 */
@Path("/ServiceLoginJR")
public class ServiceLoginJR {

    private static Validator validator;

    @POST
    @Path("/validarUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String validarUsuario(final String input) {
        ObjectMapper mapper = new ObjectMapper();
        Usuario usuario = null;
        Usuario usuarioSalida = null;
        ListaUsuarios listaUsuario = null;
        GestionUsuario gestionUsuario = new GestionUsuarioDefault();
        String jsonInString = "";
        String mensaje = "";
        String code = "";

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        try {
            usuario = mapper.readValue(input, Usuario.class);
            usuarioSalida = gestionUsuario.validarUsuario(usuario);
            
            if(usuarioSalida != null)
               usuario = usuarioSalida;

            Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
            if (constraintViolations.size() > 0) {
                System.out.println("problemas");
                for(ConstraintViolation cv : constraintViolations) {
                    System.out.println(cv.getMessage());
                    mensaje += cv.getMessage() + " ";
                    code = "01";
                    constraintViolations.iterator().next();
                }
            }
            
            jsonInString = mapper.writeValueAsString(usuario);
        } catch (IOException e) {
            e.printStackTrace();
            mensaje = "No es un JSON del tipo Usuario";
            code = "03";
        }
        
        if(usuario == null){
            try {
                listaUsuario = mapper.readValue(input, ListaUsuarios.class);

                for(Usuario usu : listaUsuario.getListaUsuarios()){
                    usuarioSalida = gestionUsuario.validarUsuario(usu);
                    if(usuarioSalida != null)
                        usu = usuarioSalida;
                    Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usu);
                    if (constraintViolations.size() > 0) {
                        System.out.println("problemas");
                        for(ConstraintViolation cv : constraintViolations) {
                            System.out.println(cv.getMessage());
                            mensaje += cv.getMessage() + " ";
                            code = "02";
                            constraintViolations.iterator().next();
                        }
                    }
                }
                jsonInString = mapper.writeValueAsString(listaUsuario);
            } catch (IOException e) {
                e.printStackTrace();
                code = "03";
                mensaje = "No es un JSON del tipo Usuario";
            }
        }
        
        jsonInString = "{  \"success\": true, " +
                           "\"error\": { " +
                                "\"message\": \""+mensaje+"\", " +
                                "\"code\": \""+code+"\" \n" +
                        "}, \"data\": "+jsonInString+"}";

        return jsonInString;
    }

}
