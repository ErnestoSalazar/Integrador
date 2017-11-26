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

}