@extends('layout')

@section('entregas')
    <h1>Entregas: </h1>
    <form class="form-inline" style="margin-bottom: 20px;">
        <div class="form-group">
            <label for="email">Buscar:</label>
            <input type="search" placeholder="Folio" class="form-control">
        </div>
        <button type="submit" class="btn btn-default">Buscar</button>
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
                        <td>T</td>
                        <td>T</td>
                        <td>T</td>
                        <td>T</td>
                        <td>T</td>
                        <td><button>Marenco</button><button>Ernesto</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
@endsection