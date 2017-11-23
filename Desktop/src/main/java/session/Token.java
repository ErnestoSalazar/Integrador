/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import com.google.gson.Gson;

/**
 *
 * @author fernandomarenco
 */
public class Token {

    private String access_token;
    private String token_type;
    private String error_description;
    private static Token token;

    public Token() {
    }
    
    public static Token getToken(String json) {
        if(token == null) {
            Gson gson = new Gson();
            token = gson.fromJson(json, Token.class);
            System.out.println(token.toString());
        } else {
            System.out.println("Ya existe el Token");
        }
        return token;
    }
    
    public static void setToken() {
        token = null;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        return "Token{" + "access_token=" + access_token + ", token_type=" + token_type + ", error_description=" + error_description + '}';
    }
    
    
    
    
}
