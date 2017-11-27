@extends('layouts.main')
@extends('layouts.partials.navegacion')
@extends('layouts.partials.stuff')
@extends('layouts.partials.footerscripts')
@extends('usuario.partials.header')


@section('content')

    <link rel="stylesheet" href="{{asset('css/cards.css')}}">
    <h1>USUARIO</h1>
    <form class="form-inline" style="margin-bottom: 20px;">
        <div class="form-group">
            <label for="email">Buscar:</label>
            <input type="search" placeholder="Nombre" class="form-control">
        </div>
        <button type="submit" class="btn btn-default">Buscar</button>
        <button type="button" class="btn btn-lg btn-success" id="my-btn" data-toggle="modal" data-target="#my-modal">Agregar</button>
    </form>
    <!--Targetas de usuario-->
    <div class="container">
        @if(Session::has('message'))
            <blockquote>
                {{{Session::get('message')}}}
            </blockquote>
        @endif
        <div class="row">

                @if(isset($listUsuarios))
                    @foreach($listUsuarios as $usuario)
                    <div class="col-md-4">
                        <div class="user__card">
                            <div class="bg">
                                <div class="perfil"><h3 class="profile">{{substr($usuario->nombre,0,1).substr($usuario->apellido,0,1);}}</h3></div>
                            </div>
                            <div class="main__content">
                                <div class="upper__card">
                                    <div class="info">
                                        <h3>{{{$usuario->nombre}}}</h3>
                                        <p>{{{$usuario->rol}}}</p>
                                        <p>RFC: {{{$usuario->rfc}}}</p>
                                        <p>{{{$usuario->correo}}}</p>
                                    </div>
                                </div>
                                <div class="card-options">
                                    <button type="button" class="btn-option-editar" data-toggle="modal" data-target="#modal-edit-usuario">
                                        <input type="text" value="{{{$usuario->id}}}" hidden>
                                        Editar
                                    </button>
                                    <button type="button" class="btn-option-eliminar">
                                        <input type="text" value="{{{$usuario->id}}}" hidden>
                                        Eliminar
                                    </button>
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
                    <h4>Usuario</h4>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    {{Form::open(array('route' => 'usuarios.store'))}}

                        <div class="row">
                            <div class="col-xs-6">
                                <div class="form-group">
                                    {{Form::text('nombre',null,['placeholder'=> 'Nombre', 'class'=>'form-control'])}}
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="form-group">
                                    {{Form::text('apellido',null,['placeholder'=>'Apellido', 'class'=>'form-control'])}}
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="form-group">
                                    {{Form::text('rfc',null,['placeholder'=>'RFC', 'class'=>'form-control'])}}
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <select class="form-control" id="sel1" name="rol">
                                        <option value="Admin">Administrador</option>
                                        <option value="Supervisor">Supervisor</option>
                                        <option value="Pescador">Pescador</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group">
                                    {{Form::text('correo',null,['placeholder'=>'Correo', 'class'=>'form-control'])}}
                                </div>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" name="sent" value="sent" class="btn btn-success btn-block">Aceptar</button>
                    <button type="button" class="btn btn-danger btn-block" data-dismiss="modal">Cancelar</button>
                </div>
                {{Form::close()}}
            </div>
        </div>
    </div>

    <div class="modal fade" id="modal-edit-usuario" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="padding:35px 50px;">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>Editar usuario</h4>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    {{Form::open(array('route'=>'usuarios.store', 'id'=> 'update-usuario'))}}
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                {{Form::text('nombre',null,['placeholder'=> 'Nombre', 'class'=>'form-control', "id"=> "nombre-update"])}}
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                {{Form::text('apellido',null,['placeholder'=>'Apellido', 'class'=>'form-control', "id" => "apellido-update"])}}
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                {{Form::text('rfc',null,['placeholder'=>'RFC', 'class'=>'form-control',"id"=>"rfc-update"])}}
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <select class="form-control" id="rol-update" name="rol">
                                    <option value="Admin">Administrador</option>
                                    <option value="Supervisor">Supervisor</option>
                                    <option value="Pescador">Pescador</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="form-group">
                                {{Form::text('correo',null,['placeholder'=>'Correo', 'class'=>'form-control'])}}
                            </div>
                        </div>
                        <div class="col-md-6">
                            <button type="submit" name="sent" value="sent" class="btn btn-success btn-block">Aceptar</button>
                        </div>
                        <div class="col-md-6">
                            <button type="button" class="btn btn-danger btn-block" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>

                    {{Form::close()}}
                </div>
            </div>
        </div>
    </div>

@stop