<?php

/**
 * Created by PhpStorm.
 * User: soygo
 * Date: 25/11/2017
 * Time: 01:45 PM
 */
class Usuario
{

    static function getUsuarios(){

        $service_url = Strings::SERVICE_URL."/api/users";
        $curl = curl_init($service_url);

        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON) );
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

    static function postUsuario($nombre, $apellido, $rfc, $rol,$correo){
        $service_url = Strings::SERVICE_URL."/api/users";
        $curl = curl_init($service_url);

        $usuario = array(
            "nombre" => $nombre,
            "apellido" => $apellido,
            "correo" => $correo,
            "rfc" => $rfc,
            "rol" => $rol
        );

        $usuario = json_encode($usuario);// array to json

        curl_setopt($curl, CURLOPT_POSTFIELDS, $usuario);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON) );
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

    static function updateUsuario($id, $nombre, $apellido, $correo, $rfc, $rol){
        $service_url = Strings::SERVICE_URL."/api/users/$id";
        $curl = curl_init($service_url);

        $usuario = array(
            "nombre" => $nombre,
            "apellido" => $apellido,
            "correo" => $correo,
            "rfc" => $rfc,
            "rol" => $rol
        );

        $usuario = json_encode($usuario);// array to json

        curl_setopt($curl, CURLOPT_POSTFIELDS, $usuario);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type:application/json') );
// return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, 'PUT');

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode == 204){
            return true;
        }
        else{
            return false;
        }


    }

    static function deleteUsuario($id){

        $service_url = Strings::SERVICE_URL."/api/users/$id";
        $curl = curl_init($service_url);

        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type:application/json') );
        // return response instead of printing
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, 'DELETE');

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode === 204){
            return "true";
        }
        else{
            return "false";
        }

    }

}