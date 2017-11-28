/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author fernandomarenco
 */
public class Constantes {
    private static final String URL = "http://spacedog.azurewebsites.net";
    public static final String LOGIN = URL + "/login";
    
    public static final String RECOVER = LOGIN + "/recover";
    
    public static final String USERS = URL + "/api/users";
    public static final String MODIFY_USERS = URL + "/api/users/";
    
    public static final String BARCOS = URL + "/api/barcos";
    public static final String MODIFY_BARCOS = URL + "/api/barcos/";
    
    public static final String CARGAS = URL + "/api/cargas";
    public static final String MODIFY_CARGAS = URL + "/api/cargas/";
    
    public static final String ENTRADAS = URL + "/api/entradas";
    public static final String MODIFY_ENTRADAS = URL + "/api/entradas/";
    
    public static final String REPORTES = ENTRADAS + "?";
}
