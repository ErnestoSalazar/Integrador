@extends('layout')

@section('barcos')
<form class="form-inline" style="margin-bottom: 20px;">
  <div class="form-group">
    <label for="email">Buscar:</label>
    <input type="search" placeholder="Nombre" class="form-control">
  </div>
  <button type="submit" class="btn btn-default">Buscar</button>
</form>
@endsection