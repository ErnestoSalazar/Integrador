<?php

class EntradaController extends \BaseController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
	    $especies = ['Macarela', 'Japonesa', 'Monterrey', 'Rayadillo', 'Bocona', 'Anchoveta', 'Crinuda'];
	    $tallas = ['S', 'M', 'L', 'XL'];
	    $condiciones = ['Mala', 'Regular', 'Buena'];

	    $entradas = Entrada::getEntradas();
	    $barcos = Barco::getBarcos();
		return View::make('entrada.index')->with([
		    "listBarcos" => $barcos,
            "listEntradas" => $entradas,
            "listEspecies" => $especies,
            "listTallas" => $tallas,
            "listCondiciones" => $condiciones
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
	    if($button === 'sent'){

	        $usuarioId = Session::get('userId');
	        $cargasId = Input::get('cargasId');

	        $bool = Entrada::postEntrada($usuarioId, $cargasId);

	        if($bool){
	            return Redirect::route('entradas.index')->withMessage(Strings::R_AGREGADO);
            }
            else{
                return Redirect::route('entradas.index')->withMessage(Strings::ERROR);
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
	    return Entrada::getEntrada($id);
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
	    $button = Input::get('sent');
	    if($button === "sent"){
            $bool = Entrada::deleteEntrada($id);
            if($bool){
                return Redirect::route('entradas.index')->withMessage(Strings::R_ELIMINADO);
            }
            else{
                return Redirect::route('entradas.index')->withMessage(Strings::ERROR);
            }
        }
        else{
            return Redirect::route('entradas.index')->withMessage(Strings::ERROR);
        }
	}

	public function updateEntrada($id){
	    $button = Input::get('sent');
	    if($button == 'sent'){
            $cargasId = Input::get('cargasIdUpdate');

            $bool = Entrada::updateEntrada($id,$cargasId);

            if($bool){
                return Redirect::route('entradas.index')->withMessage(Strings::R_ACTUALIZADO);
            }
            else{
                return Redirect::route('entradas.index')->withMessage(Strings::ERROR);
            }
        }
    }

    public function findByFolio(){
	    $button = Input::get('sent');
	    if($button == 'sent'){

            $especies = ['Macarela', 'Japonesa', 'Monterrey', 'Rayadillo', 'Bocona', 'Anchoveta', 'Crinuda'];
            $tallas = ['S', 'M', 'L', 'XL'];
            $condiciones = ['Mala', 'Regular', 'Buena'];

	        $folio = Input::get('folio');
	        $entrada = Entrada::getEntradaByFolio($folio);
            $barcos = Barco::getBarcos();
	        return View::make('entrada.index')->with([
                "listBarcos" => $barcos,
                "listEntradas" => $entrada,
                "listEspecies" => $especies,
                "listTallas" => $tallas,
                "listCondiciones" => $condiciones
            ]);
        }
    }


}
