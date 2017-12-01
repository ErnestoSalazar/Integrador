<?php

/**
 * Created by PhpStorm.
 * User: soygo
 * Date: 27/11/2017
 * Time: 02:33 PM
 */
class Carga
{

    static function getCarga($id){
        $service_url = Strings::SERVICE_URL."/api/cargas/$id";
        $curl = curl_init($service_url);

        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON, Strings::AUTH_TOKEN." ".Session::get('token')) );
        // return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_HTTPGET, true);

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode === 200){
            return $curl_response;
        }
        else{
            return null;
        }
    }

    static function postCarga($toneladas, $especie, $talla, $temperatura, $condicion, $barcoId){
        $service_url = Strings::SERVICE_URL."/api/cargas";
        $curl = curl_init($service_url);

        $carga = array(
            "cantidad" => $toneladas,
            "especie" => $especie,
            "talla" => $talla,
            "temperatura" => $temperatura,
            "condicion" => $condicion,
            "barcoId" => $barcoId
        );

        $carga = json_encode($carga);// array to json

        curl_setopt($curl, CURLOPT_POSTFIELDS, $carga);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON, Strings::AUTH_TOKEN." ".Session::get('token')) );
        // return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_POST, true);

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode === 201){
            return $curl_response;
        }else{
            return null;
        }
    }


}