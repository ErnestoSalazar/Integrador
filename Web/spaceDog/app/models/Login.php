<?php

/**
 * Created by PhpStorm.
 * User: soygo
 * Date: 29/11/2017
 * Time: 09:59 AM
 */
class Login
{


    static function passwordRecover($mail){
        $service_url = Strings::SERVICE_URL."/login/recover";
        $curl = curl_init($service_url);

        $mailTo = json_encode(array(
            "mailTo" => $mail
        ));

        curl_setopt($curl, CURLOPT_POSTFIELDS, $mailTo);
        curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_JSON));

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_POST, true);

        $curl_response = curl_exec($curl);
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        curl_close($curl);

        if($httpcode === 200){
            return "true";
        }
        else{
            return "false";
        }
    }


}