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
        @if(Session::has('message'))
            <blockquote>
                {{{Session::get('message')}}}
            </blockquote>
        @endif
        <div class="row">
            @if(isset($listBarcos))
                @foreach($listBarcos as $barco)
                    <div class="col-md-4">
                        <div class="user__card">
                            <div class="bg">
                                <div class="perfil"><img src="img/boatProfile.png" alt=""></div>
                            </div>
                            <div class="main__content">
                                <div class="upper__card">
                                    <div class="info">
                                        <h3>{{{$barco->nombre}}}</h3>
                                        <p>{{{$barco->usuario->nombre}}}</p>
                                        <p>{{{$barco->descripcion}}}</p>
                                    </div>
                                </div>
                                <div class="card-options">
                                    <button type="button" class="btn-option-editar" data-toggle="modal" data-target="#modal-edit-barco">
                                        <input type="text" value="{{{$barco->id}}}" hidden>
                                        Editar
                                    </button>
                                    {{Form::open([ 'method'  => 'delete', 'route' => [ 'barcos.destroy', $barco->id ] ])}}
                                    <button type="submit" name="sent" value="sent" class="btn-option-eliminar">
                                        Eliminar
                                    </button>
                                    {{Form::close()}}
                                </div>
                            </div>
                        </div>
                    </div>
                @endforeach
            @endif
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
                    {{Form::open(array('route'=>'barcos.store'))}}
                    <form role="form">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="form-group">
                                    {{Form::text("nombre",null,['placeholder'=> 'Nombre', 'id' => 'nombre', 'class' => 'form-control'])}}
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <select class="form-control" id="sel1" name="usuario">
                                        @if(isset($listUsuarios))
                                            @foreach($listUsuarios as $usuario)
                                                @if($usuario->rol == 'Pescador')
                                                    <option value="{{{$usuario->id}}}">{{{$usuario->nombre}}}</option>
                                                @endif
                                            @endforeach
                                        @endif
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <textarea name="descripcion" class="form-control" rows="5" placeholder="Descripción..."></textarea>
                            </div>
                            <button type="submit" name="sent" value="sent" class="btn btn-success btn-block">Aceptar</button>
                            <button type="button" class="btn btn-danger btn-block" data-dismiss="modal">Cancelar</button>
                        </div>
                    </form>
                    {{Form::close()}}
                </div>
            </div>
        </div>
    </div>

    {{--Modal for update--}}
    <div class="modal fade" id="modal-edit-barco" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="padding:35px 50px;">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>Barco</h4>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    {{Form::open(['route'=>['barcos.update',""],'method'=>'PUT', 'id'=>'update-barco'])}}
                    <form role="form">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="form-group">
                                    {{Form::text("nombre",null,['placeholder'=> 'Nombre', 'id' => 'nombre', 'class' => 'form-control'])}}
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <select class="form-control" id="sel1" name="usuario">
                                        @if(isset($listUsuarios))
                                            @foreach($listUsuarios as $usuario)
                                                @if($usuario->rol == 'Pescador')
                                                    <option value="{{{$usuario->id}}}">{{{$usuario->nombre}}}</option>
                                                @endif
                                            @endforeach
                                        @endif
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <textarea name="descripcion" class="form-control" rows="5" placeholder="Descripción..."></textarea>
                            </div>
                            <button type="submit" name="sent" value="sent" class="btn btn-success btn-block">Aceptar</button>
                            <button type="button" class="btn btn-danger btn-block" data-dismiss="modal">Cancelar</button>
                        </div>
                    </form>
                    {{Form::close()}}
                </div>
            </div>
        </div>
    </div>

    </div>
@stop