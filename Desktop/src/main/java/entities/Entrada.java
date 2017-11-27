/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author fernandomarenco
 */
public class Entrada {
    private int id;
    private String folio;
    private String fecha;
    private String hora;
    private String turno;
    private int usuarioId;
    private Usuario usuario;
    
    private List<Integer> cargasId;
    private List<Carga> cargas;

    //Constructor
    public Entrada() {
    }

    public Entrada(int usuarioId, List<Integer> cargasId) {
        this.usuarioId = usuarioId;
        this.cargasId = cargasId;
    }

    public Entrada(List<Integer> cargasId) {
        this.cargasId = cargasId;
    }
    
    
    
    public List<Entrada> getListEntradas(String salida) {
        Gson gson = new Gson();
        List<Entrada> entradas = gson.fromJson(salida, new TypeToken<List<Entrada>>(){}.getType());
        
        return entradas;
    }
    
    public Entrada jsonToEntrada(String json) {
        Gson gson = new Gson();
        Entrada entrada = gson.fromJson(json, Entrada.class);
        return entrada;
    }
    
    public String entradaToJson() {
        Gson gson = new Gson();
        String json = gson.toJson(Entrada.this);
        return json;
    }
    
    
    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Integer> getCargasId() {
        return cargasId;
    }

    public void setCargasId(List<Integer> cargasId) {
        this.cargasId = cargasId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<Carga> getCargas() {
        return cargas;
    }

    public void setCargas(List<Carga> cargas) {
        this.cargas = cargas;
    }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", folio=" + folio + ", fecha=" + fecha + ", hora=" + hora + ", turno=" + turno + ", usuarioId=" + usuarioId + ", usuario=" + usuario + ", cargasId=" + cargasId + ", cargas=" + cargas + '}';
    }

    
    
}
