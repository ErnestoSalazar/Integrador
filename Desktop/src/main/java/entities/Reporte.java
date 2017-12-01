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
public class Reporte extends Entrada {
    private double totalMacarela;
    private double totalJaponesa;
    private double totalMonterrey;
    private double totalRayadillo;
    private double totalBocona;
    private double totalAnchoveta;
    private double totalCrinuda;
    
    private double porcentajeMacarela;
    private double porcentajeJaponesa;
    private double porcentajeMonterrey;
    private double porcentajeRayadillo;
    private double porcentajeBocona;
    private double porcentajeAnchoveta;
    private double porcentajeCrinuda;
    
    private double totales;

    
    public List<Reporte> getListReportes(String salida) {
        Gson gson = new Gson();
        List<Reporte> entradas = gson.fromJson(salida, new TypeToken<List<Reporte>>(){}.getType());
        
        return entradas;
    }
    
    public Reporte jsonToReporte(String json) {
        Gson gson = new Gson();
        Reporte reporte = gson.fromJson(json, Reporte.class);
        return reporte;
    }
    
    public double getTotalMacarela() {
        return totalMacarela;
    }

    public void setTotalMacarela(double totalMacarela) {
        this.totalMacarela = totalMacarela;
    }

    public double getTotalJaponesa() {
        return totalJaponesa;
    }

    public void setTotalJaponesa(double totalJaponesa) {
        this.totalJaponesa = totalJaponesa;
    }

    public double getTotalMonterrey() {
        return totalMonterrey;
    }

    public void setTotalMonterrey(double totalMonterrey) {
        this.totalMonterrey = totalMonterrey;
    }

    public double getTotalRayadillo() {
        return totalRayadillo;
    }

    public void setTotalRayadillo(double totalRayadillo) {
        this.totalRayadillo = totalRayadillo;
    }

    public double getTotalBocona() {
        return totalBocona;
    }

    public void setTotalBocona(double totalBocona) {
        this.totalBocona = totalBocona;
    }

    public double getTotalAnchoveta() {
        return totalAnchoveta;
    }

    public void setTotalAnchoveta(double totalAnchoveta) {
        this.totalAnchoveta = totalAnchoveta;
    }

    public double getTotalCrinuda() {
        return totalCrinuda;
    }

    public void setTotalCrinuda(double totalCrinuda) {
        this.totalCrinuda = totalCrinuda;
    }

    public double getPorcentajeMacarela() {
        return porcentajeMacarela;
    }

    public void setPorcentajeMacarela(double porcentajeMacarela) {
        this.porcentajeMacarela = porcentajeMacarela;
    }

    public double getPorcentajeJaponesa() {
        return porcentajeJaponesa;
    }

    public void setPorcentajeJaponesa(double porcentajeJaponesa) {
        this.porcentajeJaponesa = porcentajeJaponesa;
    }

    public double getPorcentajeMonterrey() {
        return porcentajeMonterrey;
    }

    public void setPorcentajeMonterrey(double porcentajeMonterrey) {
        this.porcentajeMonterrey = porcentajeMonterrey;
    }

    public double getPorcentajeRayadillo() {
        return porcentajeRayadillo;
    }

    public void setPorcentajeRayadillo(double porcentajeRayadillo) {
        this.porcentajeRayadillo = porcentajeRayadillo;
    }

    public double getPorcentajeBocona() {
        return porcentajeBocona;
    }

    public void setPorcentajeBocona(double porcentajeBocona) {
        this.porcentajeBocona = porcentajeBocona;
    }

    public double getPorcentajeAnchoveta() {
        return porcentajeAnchoveta;
    }

    public void setPorcentajeAnchoveta(double porcentajeAnchoveta) {
        this.porcentajeAnchoveta = porcentajeAnchoveta;
    }

    public double getPorcentajeCrinuda() {
        return porcentajeCrinuda;
    }

    public void setPorcentajeCrinuda(double porcentajeCrinuda) {
        this.porcentajeCrinuda = porcentajeCrinuda;
    }

    public double getTotales() {
        return totales;
    }

    public void setTotales(double totales) {
        this.totales = totales;
    }

    @Override
    public String toString() {
        return "Reporte{" + "totalMacarela=" + totalMacarela + ", totalJaponesa=" + totalJaponesa + ", totalMonterrey=" + totalMonterrey + ", totalRayadillo=" + totalRayadillo + ", totalBocona=" + totalBocona + ", totalAnchoveta=" + totalAnchoveta + ", totalCrinuda=" + totalCrinuda + ", porcentajeMacarela=" + porcentajeMacarela + ", porcentajeJaponesa=" + porcentajeJaponesa + ", porcentajeMonterrey=" + porcentajeMonterrey + ", porcentajeRayadillo=" + porcentajeRayadillo + ", porcentajeBocona=" + porcentajeBocona + ", porcentajeAnchoveta=" + porcentajeAnchoveta + ", porcentajeCrinuda=" + porcentajeCrinuda + ", totales=" + totales + '}';
    }

    
    
}
