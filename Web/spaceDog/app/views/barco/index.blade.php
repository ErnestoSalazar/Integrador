@extends('layouts.main')
@extends('layouts.partials.navegacion')
@extends('layouts.partials.stuff')
@extends('layouts.partials.footerscripts')
@extends('barco.partials.header')

@section('content')
    <link rel="stylesheet" href="css/cards.css">
    <h1>BARCO</h1>
    <form class="form-inline" style="margin-bottom: 20px;">
        <div class="form-group">
            <label for="">Buscar:</label>
            <input type="search" placeholder="Barco" class="form-control">
        </div>
        <button type="submit" class="btn btn-default">Buscar</button>
        <button type="button" class="btn btn-lg btn-success" id="my-btn" data-toggle="modal" data-target="#my-modal">Agregar</button>
    </form>
    <!--Targetas Barcos-->
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="user__card">
                    <div class="bg">
                        <div class="perfil"><img src="img/boatProfile.png" alt=""></div>
                    </div>
                    <div class="main__content">
                        <div class="upper__card">
                            <div class="info">
                                <h3>Nombre</h3>
                                <p>Pescador</p>
                                <p>Descripcion</p>
                            </div>
                        </div>
                        <div class="card-options">
                            <button type="button" class="btn-option-editar" data-toggle="modal" data-target="#modal-edit-usuario">
                                <input type="text" value="" hidden>
                                Editar
                            </button>
                            <button type="button" class="btn-option-eliminar">
                                <input type="text" value="" hidden>
                                Eliminar
                            </button>
                        </div>
                    </div>
                </div>
            </div>   
        </div>
    </div>

    <!--Modal-->
    <div class="modal fade" id="my-modal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="padding:35px 50px;">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>Barco</h4>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    <form role="form">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="nombre" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <select class="form-control" id="sel1">
                                        <option>Pescador</option>
                                        <option>Supervisor</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" rows="5" placeholder="Descripción..."></textarea>
                            </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success btn-block">Aceptar</button>
                    <button type="submit" class="btn btn-danger btn-block">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
    </div>
@stop