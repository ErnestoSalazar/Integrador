<?php

class BarcoController extends \BaseController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$barcos = Barco::getBarcos();
		$usuarios = Usuario::getUsuarios();
		return View::make('barco.index')->with([
            'listBarcos' => $barcos,
            'listUsuarios'=> $usuarios
        ]);
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

		if($button == "sent"){
		    $nombre = Input::get('nombre');
		    $descripcion = Input::get('descripcion');
		    $usuarioId = (int)Input::get('usuario');

		    $bool = Barco::postBarco($nombre,$descripcion,$usuarioId);
		    if($bool){
		        return Redirect::route('barcos.index')->withMessage("Barco registrado");
            }
            else{
		        return Redirect::route('barcos.index')->withMessage(Strings::ERROR);
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
	    return Redirect::route('usuarios.index');
	}


	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
	    $button = Input::get('sent');
	    if($button == 'sent'){
            $bool = Barco::deleteBarco($id);
            if($bool){
                return Redirect::route('barcos.index')->withMessage('Registro eliminado');
            }
            else{
                return Redirect::route('barcos.index')->withMessage(Strings::ERROR);
            }
        }
	}

	public function updateBarco($id){
	    $button = Input::get('sent');
	    if($button == 'sent'){
	        $nombre = Input::get('nombre');
	        $descripcion = Input::get('descripcion');
	        $usuarioId = (int)Input::get('usuario');

	        $bool = Barco::updateBarco($id, $nombre, $descripcion, $usuarioId);

	        if($bool[0]){
	            return Redirect::route('barcos.index')->withMessage('Información actualizada');
            }
            else{
                return Redirect::route('barcos.index')->withMessage($bool[1]);
            }

        }
    }

}
