/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fernandomarenco
 */
public class Barco {
    private int id;
    private String nombre;
    private String descripcion;
    private int usuarioId;
    private Usuario usuario;
    
    //Contructor
    public Barco() {}

    public Barco(String nombre, String descripcion, int usuarioId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarioId = usuarioId;
    }
    
    
    public List<Barco> getListBarcos(String salida) {
        Gson gson = new Gson();
        List<Barco> barcos = gson.fromJson(salida, new TypeToken<List<Barco>>(){}.getType());
        
        return barcos;
    }
    
    public Barco jsonToBarco(String json) {
        Gson gson = new Gson();
        Barco barco = gson.fromJson(json, Barco.class);
        return barco;
    }
    
    public String barcoToJson() {
        Gson gson = new Gson();
        String json = gson.toJson(Barco.this);
        return json;
    }
    
    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Barco{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", usuarioId=" + usuarioId + ", usuario=" + usuario + '}';
    }
    
    
    
}
