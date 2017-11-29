<?php

class Barco
{

    static function getBarcos(){
        $service_url = Strings::SERVICE_URL."/api/barcos";
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

    static function getBarcosByName($nombre){
        $service_url = Strings::SERVICE_URL."/api/barcos?nombre=$nombre";
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

    static function postBarco($nombre,$descripcion,$usuarioId){
        $service_url = Strings::SERVICE_URL.'/api/barcos';
        $curl = curl_init($service_url);

        $barco = array(
            "nombre" => $nombre,
            "descripcion" => $descripcion,
            "usuarioId" => $usuarioId
        );

        $barco = json_encode($barco);// array to json

        curl_setopt($curl, CURLOPT_POSTFIELDS, $barco);
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

    static function updateBarco($id, $nombre, $apellido, $usuarioId){
        $service_url = Strings::SERVICE_URL."/api/barcos/$id";
        $curl = curl_init($service_url);

        $barco = array(
            "nombre" => $nombre,
            "descripcion" => $apellido,
            "usuarioId" => $usuarioId
        );

        $barco = json_encode($barco);// array to json

        curl_setopt($curl, CURLOPT_POSTFIELDS, $barco);
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

    static function deleteBarco($id){
        $service_url = Strings::SERVICE_URL."/api/barcos/$id";
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