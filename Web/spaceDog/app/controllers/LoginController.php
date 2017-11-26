<?php

class LoginController extends \BaseController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		return View::make('login.index');
	}


	/**
	 * Show the form for creating a new resource.
	 *
	 * @return Response
	 */
	public function create()
	{
		//
	}


	/**
	 * Store a newly created resource in storage.
	 *
	 * @return Response
	 */
	public function store()
	{
		$button = Input::get('sent');
		if($button === "Login"){

		    $userName = Input::get('login');
		    $password = Input::get('password');

		    $service_url = Strings::SERVICE_URL."/login";
		    $curl = curl_init($service_url);

            $login = array(
                "grant_type" => "password",
                "userName" => $userName,
                "password" => $password
            );

		    $login = http_build_query($login);

            curl_setopt($curl, CURLOPT_POSTFIELDS, $login);
            curl_setopt($curl, CURLOPT_HTTPHEADER, array(Strings::CONTENT_FORM));

            curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($curl, CURLOPT_POST, true);

            $curl_response = curl_exec($curl);
            $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
            curl_close($curl);

            if($httpcode === 200){
                $curl_response = json_decode($curl_response);
                Session::put("token",$curl_response->access_token);
                Session::put("userId", (int)$curl_response->userId);
                Session::put('rol', $curl_response->rol);

                return Redirect::route('usuarios.index');
            }
            else{
                return Redirect::route('login.index')->withMessage($httpcode);
            }

        }

	}


	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function show($id)
	{
		//
	}


	/**
	 * Show the form for editing the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function edit($id)
	{
		//
	}


	/**
	 * Update the specified resource in storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function update($id)
	{
		//
	}


	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
		//
	}


}
