@extends('layouts.main')
@extends('layouts.partials.navegacion')
@extends('layouts.partials.stuff')
@extends('layouts.partials.footerscripts')
@extends('entrada.partials.header')


@section('content')
    <h1>Entregas: </h1>
    <form class="form-inline" style="margin-bottom: 20px;">
        <div class="form-group">
            <label for="email">Buscar:</label>
            <input type="search" placeholder="Folio" class="form-control">
        </div>
        <button type="submit" class="btn btn-default">Buscar</button>
        <button type="button" class="btn btn-lg btn-success" id="my-btn" data-toggle="modal" data-target="#my-modal">Agregar</button>
    </form>
    <div class="container">
        <div class="table-responsive">
            <table class="table">
                <thead>
                <th>Folio</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Turno</th>
                <th>Generado por</th>
                <th>Acciones</th>
                </thead>
                <tbody>
                <tr>
                    <td>Contenido</td>
                    <td>Contenido</td>
                    <td>Mas Contenido</td>
                    <td>Contenido</td>
                    <td>Mas contenido</td>
                    <td>
                        <button type="submit" class="btn bckg-transparent"><i class="material-icons">check</i></button>
                        <button type="submit" class="btn bckg-transparent"><i class="material-icons">close</i></button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="modal fade" id="my-modal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="padding:35px 50px;">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>Entregas</h4>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    {{Form::open(array('route'=> 'entradas.store'))}}

                        <div class="form-group">
                            <select class="form-control" id="sel1" name="">
                                @if(isset($listBarcos))
                                    @foreach($listBarcos as $barco)
                                        <option value="{{$barco->id}}">{{$barco->nombre}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <input type="text" class="form-control" id="nombre" placeholder="Toneladas">
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <select class="form-control" id="sel1">
                                    <option>Especie</option>
                                    <option>Ka</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4">
                            <div class="form-group">
                                <select class="form-control" id="sel1">
                                    <option>Talla</option>
                                    <option>Ka</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4">
                            <div class="form-group">
                                <select class="form-control" id="sel1">
                                    <option>Temperatura</option>
                                    <option>Khe</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4">
                            <div class="form-group">
                                <select class="form-control" id="sel1">
                                    <option>Condicion</option>
                                    <option>Que</option>
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-success btn-block">Aceptar</button>
                        <button type="submit" class="btn btn-danger btn-block">Cancelar</button>

                    {{Form::close()}}
                </div>
            </div>
        </div>
    </div>
@stop