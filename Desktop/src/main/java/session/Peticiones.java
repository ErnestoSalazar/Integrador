/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author fernandomarenco
 */
public class Peticiones {
    
    public String getAll(String uri, Token token) {
        String salida = null;
        try {
            CloseableHttpClient httpClient = Client.getHttpClient(token);
            
            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            
            salida = EntityUtils.toString(httpResponse.getEntity());
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return salida;
    }
    
    public String get(String uri, int number, Token token) {
        String salida = null;
        try {
            CloseableHttpClient httpClient = Client.getHttpClient(token);
            
            HttpGet httpGet = new HttpGet(uri + number);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            
            salida = EntityUtils.toString(httpResponse.getEntity());
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return salida;
    }
    
    public String post(String uri, Token token, String json) {
        String salida = null;
        try {
            CloseableHttpClient httpClient = Client.getHttpClient(token);
            HttpPost httpPost = new HttpPost(uri);
            
            //UrlEncodedFormEntity entity = new UrlEncodedFormEntity(lista);
            //httpPost.setEntity(new UrlEncodedFormEntity(lista, "UTF-8"));
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            //httpPost.setHeader("Content-type", "application/json");
            
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            salida = EntityUtils.toString(httpResponse.getEntity());
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return salida;
    }
    
    public String put(String uri, int number, Token token, String json) {
        String salida = null;
        try {
            CloseableHttpClient httpClient = Client.getHttpClient(token);
            
            HttpPut httpPut = new HttpPut(uri + number);
            
            StringEntity entity = new StringEntity(json);
            httpPut.setEntity(entity);
            
            CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
            
            int status = httpResponse.getStatusLine().getStatusCode();
            
            if (status >= 200 && status < 300) {
                salida = ""+status;
            } else {
                salida = EntityUtils.toString(httpResponse.getEntity());
            }
            
            
        } catch (IOException ex) { 
            System.out.println(ex);
        }
        
        return salida;
    }
    
    public String delete(String uri, int number, Token token, String json) {
        String salida = null;
        
        try {
            CloseableHttpClient httpClient = Client.getHttpClient(token);
            
            HttpDelete httpDelete = new HttpDelete(uri + number);
            
            CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
            
            int status = httpResponse.getStatusLine().getStatusCode();
            
            if (status >=200 && status < 300) {
                salida = ""+status;
            } else {
                salida = EntityUtils.toString(httpResponse.getEntity());
            }
            
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return salida;
    }
    
    public String report(String uri, Token token, LocalDate fechaInicio, LocalDate fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fi = fechaInicio.format(formatter);
        String ff = fechaFin.format(formatter);
        
        String salida = null;
        
        try {
            CloseableHttpClient httpClient = Client.getHttpClient(token);
            
            URI uriParam = new URIBuilder(uri)
                    .addParameter("fechaInicio", fi)
                    .addParameter("fechaFin", ff)
                    .build();
            
            HttpGet httpGet = new HttpGet(uriParam);
            
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            
            int status = httpResponse.getStatusLine().getStatusCode();
            
            if (status >= 200 && status < 300) {
                salida = EntityUtils.toString(httpResponse.getEntity());
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (URISyntaxException ex) {
            System.out.println(ex);
        }
        
        return salida;
    }
}