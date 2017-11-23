@extends('layout')

@section('content')
  <h1>Reportes</h1>
	<div>
  		<form class="form-inline">
    		<div class="form-group">
      			<label for="email">Ingresar fecha:</label>
      			<input type="search" placeholder="Inicio" class="form-control">
    		</div>
    		<div class="form-group">
      			<input type="search" placeholder="Fin" class="form-control" id="pwd">
    		</div>
    		<button type="submit" class="btn btn-default">Buscar</button>
			<button type="button" class="btn btn-primary">Generar Reporte</button>
  		</form>
  	</div>
	<h2>Entregas</h2>
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
				<tr>
					<td>1</td>
					<td>Marco Diaz Anita Acosta</td>
					<td>1</td>
					<td>2017-11-11</td>
					<td>18:00</td>
				</tr>
			</tbody>
    	</table>
  	</div>
	<h2>Cargas:</h2>
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
				<tr>
					<td>Goku Doctrina egoista</td>
					<td>Japonesa</td>
					<td>1050</td>
					<td>L</td>
					<td>12:43</td>
					<td>Muy chida</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row">
		<div class="col-md-3">
			<h3>Macarela</h3>
			<form>
  				<div class="form-group">
    				<label for="total">Total:</label>
    				<input class="form-control" id="total">
  				</div>
  				<div class="form-group">
    				<label for="pwd">Porcentaje:</label>
    				<input class="form-control" id="pwd">
  				</div>
			</form>
		</div>
		<div class="col-md-3">
			<h3>Japonesa</h3>
			<form>
  				<div class="form-group">
    				<label for="total">Total:</label>
    				<input class="form-control" id="total">
  				</div>
  				<div class="form-group">
    				<label for="pwd">Porcentaje:</label>
    				<input class="form-control" id="pwd">
  				</div>
			</form>
		</div>
		<div class="col-md-3">
			<h3>Monterrey</h3>
			<form>
  				<div class="form-group">
    				<label for="total">Total:</label>
    				<input class="form-control" id="total">
  				</div>
  				<div class="form-group">
    				<label for="pwd">Porcentaje:</label>
    				<input class="form-control" id="pwd">
  				</div>
			</form>
		</div>
		<div class="col-md-3">
			<h3>Rayadillo</h3>
			<form>
  				<div class="form-group">
    				<label for="total">Total:</label>
    				<input class="form-control" id="total">
  				</div>
  				<div class="form-group">
    				<label for="pwd">Porcentaje:</label>
    				<input class="form-control" id="pwd">
  				</div>
			</form>
		</div>
		<div class="col-md-3">
			<h3>Bocona</h3>
			<form>
  				<div class="form-group">
    				<label for="total">Total:</label>
    				<input class="form-control" id="total">
  				</div>
  				<div class="form-group">
    				<label for="pwd">Porcentaje:</label>
    				<input class="form-control" id="pwd">
  				</div>
			</form>
		</div>
		<div class="col-md-3">
			<h3>Anchoveta</h3>
			<form>
  				<div class="form-group">
    				<label for="total">Total:</label>
    				<input class="form-control" id="total">
  				</div>
  				<div class="form-group">
    				<label for="pwd">Porcentaje:</label>
    				<input class="form-control" id="pwd">
  				</div>
			</form>
		</div>
		<div class="col-md-3">
			<h3>Crinuda</h3>
			<form>
  				<div class="form-group">
    				<label for="total">Total:</label>
    				<input class="form-control" id="total">
  				</div>
  				<div class="form-group">
    				<label for="pwd">Porcentaje:</label>
    				<input class="form-control" id="pwd">
  				</div>
			</form>
		</div>
		<div class="col-md-3" style= "margin-top: 100px;">
			<form>
  				<div class="form-group">
    				<label for="total">Total:</label>
    				<input class="form-control" id="total">
  				</div>
			</form>
		</div>
	</div>
@endsection