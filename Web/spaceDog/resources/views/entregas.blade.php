@extends('layout')

@section('entregas')
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

    <div class="modal fade" id="my-modal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4>Usuario</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form role="form">
            	<div class="form-group">
					<select class="form-control" id="sel1">
    					<option>Barco</option>
    					<option>Ka</option>
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
          		</form>
			  </div>
        </div>
      </div>
    </div>
@endsection