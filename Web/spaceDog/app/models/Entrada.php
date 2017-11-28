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

        if($httpcode === 200){
            return json_decode($curl_response);
        }
        else{
            return null;
        }
    }

    static function getEntrada($id){
        $service_url = Strings::SERVICE_URL."/api/entradas/$id";
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

    static function getEntradaByFolio($folio){
        $service_url = Strings::SERVICE_URL."/api/entradas?folio=$folio";
        $curl = curl_init($service_url);

        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON, Strings::AUTH_TOKEN." ".Session::get('token')) );
        // return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_HTTPGET, true);

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode === 200){
            return array(json_decode($curl_response));
        }
        else{
            return null;
        }
    }


    static function postEntrada($usuarioId, $cargasId){
        $service_url = Strings::SERVICE_URL."/api/entradas";
        $curl = curl_init($service_url);

        $entrada = array(
            "usuarioId" => $usuarioId,
            "cargasId" => $cargasId
        );

        $entrada = json_encode($entrada);// array to json

        curl_setopt($curl, CURLOPT_POSTFIELDS, $entrada);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON, Strings::AUTH_TOKEN." ".Session::get('token')) );
        // return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_POST, true);

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode === 201){
            return true;
        }
        else{
            return false;
        }
    }

    static function updateEntrada($id, $cargasId){
        $service_url = Strings::SERVICE_URL."/api/entradas/$id";
        $curl = curl_init($service_url);

        $entrada = array(
            "cargasId" => $cargasId
        );

        $entrada = json_encode($entrada);// array to json

        curl_setopt($curl, CURLOPT_POSTFIELDS, $entrada);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON, Strings::AUTH_TOKEN." ".Session::get('token')) );
        // return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, 'PUT');

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode === 204){
            return true;
        }
        else{
            return false;
        }
    }

    static function deleteEntrada($id){
        $service_url = Strings::SERVICE_URL."/api/entradas/$id";
        $curl = curl_init($service_url);

        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON, Strings::AUTH_TOKEN." ".Session::get('token')) );
        // return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, 'DELETE');

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode === 204){
            return true;
        }
        else{
            return false;
        }
    }




}