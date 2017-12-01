<?php

class UsuarioController extends \BaseController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{

		if(!Usuario::checkIfUserIsLoged()) return Redirect::route('login.index');
		if(Usuario::checkIfUserIsSupervisor()) return Redirect::route('entradas.index');
		

	    $usuarios = Usuario::getUsuarios();

	    return View::make('usuario.index')->with('listUsuarios', $usuarios);
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
	    if($button === 'sent') {
	        $nombre = Input::get('nombre');
	        $apellido = Input::get('apellido');
            $rfc = Input::get('rfc');
            $rol = Input::get('rol');
            $correo = Input::get('correo');
            $bool = Usuario::postUsuario($nombre,$apellido,$rfc,$rol,$correo);
            if($bool){
                return Redirect::route('usuarios.index')->withMessage("Usuario registrado");
            }
            else{
                return Redirect::route('usuarios.index')->withMessage("Algo ha salido mal");
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


	public function editUsuario($id){

	    $button = Input::get('sent');
	    if($button === 'sent'){

	        $nombre = Input::get('nombre');
	        $apellido = Input::get('apellido');
	        $rfc = Input::get('rfc');
	        $rol = Input::get('rol');
	        $correo = Input::get('correo');

	        $bool = Usuario::updateUsuario($id,$nombre,$apellido,$correo,$rfc,$rol);
	        if($bool){
                return Redirect::route('usuarios.index')->withMessage("Informacion actualizada");
            }
            else{
                return Redirect::route('usuarios.index')->withMessage("Algo ha salido mal");
            }
        }
    }

    public function deleteUsuario($id){
	    return Usuario::deleteUsuario($id);
    }

    public function findByName(){
        $button = Input::get('sent');
        if($button == 'sent'){
            $nombre = Input::get('nombre');
            $apellido = Input::get('apellido');

            $usuarios = Usuario::findByName($nombre,$apellido);

            if(!Usuario::checkIfUserIsLoged()) return Redirect::route('login.index');
            return View::make('usuario.index')->with([
                "listUsuarios" => $usuarios
            ]);

        }

    }


}
