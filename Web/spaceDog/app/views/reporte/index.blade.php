@extends('layouts.main')
@extends('layouts.partials.navegacion')
@extends('layouts.partials.stuff')
@extends('layouts.partials.footerscripts')
@extends('reporte.partials.header')

@section('content')

    <h1>Reportes</h1>
    
    <h2>Entradas</h2>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <th>Folio</th>
            <th>Generado por</th>
            <th>Turno</th>
            <th>Fecha</th>
            <th>Hora</th>
            </thead>
            <tbody>
            @if(isset($reporte))
                <tr>
                    <td>{{{$reporte->folio}}}</td>
                    <td>{{{$reporte->usuario->nombre}}}</td>
                    <td>{{{$reporte->turno}}}</td>
                    <td>{{{Carbon\Carbon::parse($reporte->fecha)->format('d/m/Y')}}}</td>
                    <td>{{{$reporte->hora}}}</td>
                </tr>
            @endif
            </tbody>
        </table>
    </div>
    <h2>Cargas</h2>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <th>Barco</th>
            <th>Especie</th>
            <th>Cantidad</th>
            <th>Talla</th>
            <th>Temp</th>
            <th>Condicion</th>
            </thead>
            <tbody>
            @if(isset($reporte))
                @foreach($reporte->cargas as $carga)
                    <tr>
                        <td>barco</td>
                        <td>{{{$carga->especie}}}</td>
                        <td>{{{$carga->talla}}}</td>
                        <td>{{{$carga->cantidad}}}</td>
                        <td>{{{$carga->condicion}}}</td>
                        <td>{{{$carga->temperatura}}}</td>
                    </tr>
                @endforeach
            @endif
            </tbody>
        </table>
    </div>
    @if(isset($reporte))
        <div class="row">
            <div class="col-md-3">
                <h3>Macarela</h3>
                <div class="col-md-6">
                    <div class="testt">
                        <span class="label label-default">Total:</span>
                        <label for="">{{{$reporte->totalMacarela}}}</label>
                    </div>
                    <div class="testt">
                        <span class="label label-default">Porcentaje:</span>
                        <label for="">{{{$reporte->porcentajeMacarela}}}</label>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <h3>Japonesa</h3>
                <div class="col-md-6">
                    <div class="testt">
                        <span class="label label-default">Total:</span>
                        <label for="">{{{$reporte->totalJaponesa}}}</label>
                    </div>
                    <div class="testt">
                        <span class="label label-default">Porcentaje:</span>
                        <label for="">{{{$reporte->porcentajeJaponesa}}}</label>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <h3>Monterrey</h3>
                <div class="col-md-6">
                    <div class="testt">
                        <span class="label label-default">Total:</span>
                        <label for="">{{{$reporte->totalMonterrey}}}</label>
                    </div>
                    <div class="testt">
                        <span class="label label-default">Porcentaje:</span>
                        <label for="">{{{$reporte->porcentajeMonterrey}}}</label>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <h3>Rayadillo</h3>
                <div class="col-md-6">
                    <div class="testt">
                        <span class="label label-default">Total:</span>
                        <label for="">{{{$reporte->totalRayadillo}}}</label>
                    </div>
                    <div class="testt">
                        <span class="label label-default">Porcentaje:</span>
                        <label for="">{{{$reporte->porcentajeRayadillo}}}</label>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <h3>Bocona</h3>
                <div class="col-md-6">
                    <div class="testt">
                        <span class="label label-default">Total:</span>
                        <label for="">{{{$reporte->totalBocona}}}</label>
                    </div>
                    <div class="testt">
                        <span class="label label-default">Porcentaje:</span>
                        <label for="">{{{$reporte->porcentajeBocona}}}</label>
                    </div>
                </div>

            </div>
            <div class="col-md-3">
                <h3>Anchoveta</h3>
                <div class="col-md-6">
                    <div class="testt">
                        <span class="label label-default">Total:</span>
                        <label for="">{{{$reporte->totalAnchoveta}}}</label>
                    </div>
                    <div class="testt">
                        <span class="label label-default">Porcentaje:</span>
                        <label for="">{{{$reporte->porcentajeAnchoveta}}}</label>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <h3>Crinuda</h3>
                <div class="col-md-6">
                    <div class="testt">
                        <span class="label label-default">Total:</span>
                        <label  for="">{{{$reporte->totalCrinuda}}}</label>
                    </div>
                    <div class="testt">
                        <span class="label label-default">Porcentaje:</span>
                        <label for="">{{{$reporte->porcentajeCrinuda}}}</label>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="col-md-6">
                    <div class="testt">
                        <h3><span class="label label-default">Total:</span></h3>
                        <label for="">{{{$reporte->totales}}}</label>
                    </div>
                </div>
            </div>
        </div>
    @endif
@stop