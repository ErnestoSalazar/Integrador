/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author fernandomarenco
 */
public class Client {
    private static String salida;
    private static CloseableHttpClient httpClient;
    
    
    public static String postLoginForm(String uri, String user, String password) {
        try {
            CloseableHttpClient httpClientLogin = HttpClients.createDefault();            
            HttpPost httpPost = new HttpPost(uri);

            httpPost.setEntity(new UrlEncodedFormEntity(setLogin(user, password), "UTF-8"));

            CloseableHttpResponse httpResponse = httpClientLogin.execute(httpPost);

            salida = EntityUtils.toString(httpResponse.getEntity());

        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return salida;
    }
    
    private static List<NameValuePair> setLogin(String user, String password) {
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("grant_type", "password"));
        form.add(new BasicNameValuePair("username", user));
        form.add(new BasicNameValuePair("password", password));
        
        return form;
    }
    
    public static CloseableHttpClient getHttpClient(Token token) {
        if(httpClient == null) {
            List<Header> headers = setHeaders(token);
            httpClient = HttpClients.custom().setDefaultHeaders(headers).build();
        }
        
        return httpClient;
    }
    
    private static List<Header> setHeaders(Token token) {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", token.getToken_type()+" "+token.getAccess_token()));
        headers.add(new BasicHeader("Content-Type", "application/json"));
        //headers.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
        return headers;
    }
    
}
