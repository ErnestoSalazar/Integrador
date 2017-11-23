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
public class Carga {
    private int id;
    private double cantidad;
    private String especie;
    private String talla;
    private double temperatura;
    private String condicion;
    private int barcoId;
    private Barco barco;
    private int entradaId;

    //Constructor
    public Carga() {
    }

    public Carga(double cantidad, String especie, String talla, double temperatura, String condicion, int barcoId) {
        this.cantidad = cantidad;
        this.especie = especie;
        this.talla = talla;
        this.temperatura = temperatura;
        this.condicion = condicion;
        this.barcoId = barcoId;
    }
    
    public List<Carga> getListCargas(String salida) {
        Gson gson = new Gson();
        List<Carga> cargas = gson.fromJson(salida, new TypeToken<List<Carga>>(){}.getType());
        
        return cargas;
    }
    
    public Carga jsonToCarga(String json) {
        Gson gson = new Gson();
        Carga carga = gson.fromJson(json, Carga.class);
        return carga;
    }
    
    public String cargaToJson() {
        Gson gson = new Gson();
        String json = gson.toJson(Carga.this);
        return json;
    }
    

    //Getter and Setter
    public int getBarcoId() {
        return barcoId;
    }

    public void setBarcoId(int barcoId) {
        this.barcoId = barcoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public int getEntradaId() {
        return entradaId;
    }

    public void setEntradaId(int entradaId) {
        this.entradaId = entradaId;
    }

    @Override
    public String toString() {
        return "Carga{" + "id=" + id + ", cantidad=" + cantidad + ", especie=" + especie + ", talla=" + talla + ", temperatura=" + temperatura + ", condicion=" + condicion + ", barcoId=" + barcoId + ", barco=" + barco + ", entradaId=" + entradaId + '}';
    }
    
    
}
