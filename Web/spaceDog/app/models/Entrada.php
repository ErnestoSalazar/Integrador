<?php

/**
 * Created by PhpStorm.
 * User: soygo
 * Date: 27/11/2017
 * Time: 08:36 AM
 */
class Entrada
{


    static function getEntradas(){
        $service_url = Strings::SERVICE_URL."/api/entradas";
        $curl = curl_init($service_url);

        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON, Strings::AUTH_TOKEN." ".Session::get('token')) );
        // return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_HTTPGET, true);

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode){
            return true;
        }
        else{
            return false;
        }
    }

}