@extends('layout')


@section('usuario')
<link rel="stylesheet" href="css/cards.css">
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
  <div class="row">
    <div class="col-md-4">
      <div class="user__card">
        <div class="bg">
          <div class="perfil"><h3 class="profile">NU</h3></div>
	      </div>
	      <div class="main__content">
		      <div class="upper__card">
			      <div class="info">
				      <h3>Nombre Usuario</h3>
				      <p>Rol</p>
              <p>RFC: 23412323</p>
              <p>Correo</p>
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
          <h4>Usuario</h4>
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
             				<input type="text" class="form-control" id="psw" placeholder="Apellido">
									</div>
							</div>
							<div class="col-xs-6">
            			<div class="form-group">
              			<input type="text" class="form-control" id="psw" placeholder="RFC">
									</div>
							</div>
							<div class="col-xs-6">
            			<div class="form-group">
										<select class="form-control" id="sel1">
    									<option>Administrador</option>
    									<option>Supervisor</option>
  									</select>
									</div>
							</div>
							<div class="col-md-6">
							<button type="submit" class="btn btn-success btn-block">Aceptar</button>
							</div>
							<div class="col-md-6">
        			<button type="submit" class="btn btn-danger btn-block">Cancelar</button>
							</div>
          </form>
			  </div>
        </div>
      </div>
    </div>
</div> 

@endsection

