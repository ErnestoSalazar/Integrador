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
        @if(Session::has('message'))
            <blockquote>
                {{{Session::get('message')}}}
            </blockquote>
        @endif
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
                @if(isset($listEntradas))
                    @foreach($listEntradas as $entrada)
                        <tr>
                            <td>{{{$entrada->folio}}}</td>
                            <td>{{{Carbon\Carbon::parse($entrada->fecha)->format('d/m/Y')}}}</td>
                            <td>{{{$entrada->hora}}}</td>
                            <td>{{{$entrada->turno}}}</td>
                            <td>{{{$entrada->usuario->nombre}}}</td>
                            <td>
                                <button type="button" class="btn bckg-transparent btn-option-editar" data-toggle="modal" data-target="#modal-edit-entrada">
                                    <input type="text" value="{{{$entrada->id}}}" hidden>
                                    <i class="material-icons">check</i>
                                </button>
                                {{Form::open([ 'method'  => 'delete', 'route' => [ 'entradas.destroy', $entrada->id ], 'class'=>'form-gatito' ])}}
                                    <button type="submit" name="sent" value="sent" class="btn bckg-transparent"><i class="material-icons">close</i></button>
                                {{Form::close()}}
                            </td>
                        </tr>
                    @endforeach
                @endif
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
                    <div class="col-md-4">
                        <div class="form-group">
                            <select class="form-control input-sm" id="option-barco" name="option-barco">
                                <option value="" disabled selected>Barcos</option>
                                @if(isset($listBarcos))
                                    @foreach($listBarcos as $barco)
                                        <option value="{{$barco->id}}">{{$barco->nombre}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <input type="text" class="form-control input-sm" id="toneladas" name="toneladas" placeholder="Toneladas">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <select class="form-control input-sm" id="option-especie" name="option-especie">
                                <option value="" disabled selected>Especie</option>
                                @if(isset($listEspecies))
                                    @foreach($listEspecies as $especie)
                                        <option value="{{{$especie}}}">{{{$especie}}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-4">
                        <div class="form-group">
                            <select class="form-control input-sm" id="option-talla" name="option-talla">
                                <option value="" disabled selected>Talla</option>
                                @if(isset($listTallas))
                                    @foreach($listTallas as $talla)
                                        <option value="{{{$talla}}}">{{{$talla}}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-4">
                        <div class="form-group">
                            {{Form::text('temperatura',null, ['placeholder'=>'Temperatura', 'class'=>'form-control'])}}
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-4">
                        <div class="form-group">
                            <select class="form-control input-sm" id="option-condicion" name="option-condicion">
                                <option value="" disabled selected>Condiciones</option>
                                @if(isset($listCondiciones))
                                    @foreach($listCondiciones as $condicion)
                                        <option value="{{{$condicion}}}">{{{$condicion}}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <button type="button" class="btn btn-primary btn-block btn-carga">Aceptar</button>
                    </div>
                    <div class="col-sm-12">
                        <div class="table-responsive">
                            <table class="table" id="tabla-cargas">
                                <thead>
                                <th>Barco</th>
                                <th>Cantidad</th>
                                <th>Especie</th>
                                <th>Talla</th>
                                <th>Temperatura</th>
                                <th>Condición</th>
                                <th>Acciones</th>
                                </thead>
                                <tbody>
                                {{--<tr>--}}
                                {{--<td>Contenido</td>--}}
                                {{--<td>Contenido</td>--}}
                                {{--<td>Contenido</td>--}}
                                {{--<td>Contenido</td>--}}
                                {{--<td>Contenido</td>--}}
                                {{--<td>Contenido</td>--}}
                                {{--<td>--}}
                                {{--<button type="submit" class="btn bckg-transparent"><i class="material-icons">check</i></button>--}}
                                {{--<button type="submit" class="btn bckg-transparent"><i class="material-icons">close</i></button>--}}
                                {{--</td>--}}
                                {{--</tr>--}}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    {{Form::open(array('route'=> 'entradas.store', 'id'=>'entrada-form'))}}
                        <button type="submit" name="sent" value="sent" class="btn btn-success btn-block">Aceptar</button>
                        <button type="button" class="btn btn-danger btn-block" data-dismiss="modal">Cancelar</button>
                    {{Form::close()}}
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modal-edit-entrada" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="padding:35px 50px;">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>Editar Entrada</h4>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    <div class="col-md-4">
                        <div class="form-group">
                            <select class="form-control input-sm" id="option-barco" name="option-barco-update">
                                <option value="" disabled selected>Barcos</option>
                                @if(isset($listBarcos))
                                    @foreach($listBarcos as $barco)
                                        <option value="{{$barco->id}}">{{$barco->nombre}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <input type="text" class="form-control input-sm" id="toneladas" name="toneladas-update" placeholder="Toneladas">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <select class="form-control input-sm" id="option-especie" name="option-especie-update">
                                <option value="" disabled selected>Especie</option>
                                @if(isset($listEspecies))
                                    @foreach($listEspecies as $especie)
                                        <option value="{{{$especie}}}">{{{$especie}}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-4">
                        <div class="form-group">
                            <select class="form-control input-sm" id="option-talla" name="option-talla-update">
                                <option value="" disabled selected>Talla</option>
                                @if(isset($listTallas))
                                    @foreach($listTallas as $talla)
                                        <option value="{{{$talla}}}">{{{$talla}}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-4">
                        <div class="form-group">
                            {{Form::text('temperatura-update',null, ['placeholder'=>'Temperatura', 'class'=>'form-control'])}}
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-4">
                        <div class="form-group">
                            <select class="form-control input-sm" id="option-condicion" name="option-condicion-update">
                                <option value="" disabled selected>Condiciones</option>
                                @if(isset($listCondiciones))
                                    @foreach($listCondiciones as $condicion)
                                        <option value="{{{$condicion}}}">{{{$condicion}}}</option>
                                    @endforeach
                                @endif
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <button type="button" class="btn btn-primary btn-block btn-carga-update">Aceptar</button>
                    </div>
                    <div class="col-sm-12">
                        <div class="table-responsive">
                            <table class="table" id="tabla-cargas-update">
                                <thead>
                                <th>Barco</th>
                                <th>Cantidad</th>
                                <th>Especie</th>
                                <th>Talla</th>
                                <th>Temperatura</th>
                                <th>Condición</th>
                                <th>Acciones</th>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    {{Form::open(['route'=>['entradas.update',""],'method'=>'PUT', 'id'=>'update-entrada-form'])}}
                    <button type="submit" name="sent" value="sent" class="btn btn-success btn-block">Aceptar</button>
                    <button type="button" class="btn btn-danger btn-block" data-dismiss="modal">Cancelar</button>
                    {{Form::close()}}
                </div>
            </div>
        </div>
    </div>

@stop