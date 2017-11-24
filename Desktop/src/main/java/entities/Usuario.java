/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 *
 * @author fernandomarenco
 */
public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String rfc;
    private String correo;
    private String password;
    private String passwordConfirmation;
    private String rol;
    
    private String message;
    private String error;
   

    //Constructor
    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String rfc, String correo, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rfc = rfc;
        this.correo = correo;
        this.rol = rol;
    }
    
    
    
    public List<Usuario> getListUsers(String salida) {
        Gson gson = new Gson();
        List<Usuario> usuarios = gson.fromJson(salida, new TypeToken<List<Usuario>>(){}.getType());
        
        return usuarios;
    }
    
    public Usuario jsonToUser(String json) {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(json, Usuario.class);
        return usuario;
    }
    
    public String userToJson() {
        Gson gson = new Gson();
        String json = gson.toJson(Usuario.this);
        return json;
    }
    
    
    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", rfc=" + rfc + ", correo=" + correo + ", password=" + password + ", passwordConfirmation=" + passwordConfirmation + ", rol=" + rol + ", message=" + message + ", error=" + error + '}';
    }

    
    
    
    
}
