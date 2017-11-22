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
</form>
<div class="container">
<div class="row">
    <div class="col-md-4">
    <div class="user__card">
  <div class="bg">
    <div class="perfil"><h1 class="profile">FN</h1></div>
	</div>
	<div class="main__content">
		<div class="upper__card">
			<div class="info">
				<h3>John Doe</h3>
				<p>Web Designer</p>
                <p>RFC: 23412323</p>
                <p>Correo</p>
			</div><!-- .info -->
			<div class="social">
			</div><!-- .social -->
		</div><!-- .upper__card -->
	</div><!-- .main__content -->
</div><!-- .user__card -->
    </div>
    <div class="col-md-4">
    <div class="user__card">
  <div class="bg">
    <div class="perfil"><h1 class="profile">FN</h1></div>
	</div>
	<div class="main__content">
		<div class="upper__card">
			<div class="info">
				<h3>John Doe</h3>
				<p>Web Designer</p>
                <p>RFC: 23412323</p>
                <p>Correo</p>
			</div><!-- .info -->
			<div class="social">
			</div><!-- .social -->
		</div><!-- .upper__card -->
	</div><!-- .main__content -->
</div><!-- .user__card -->
    </div>
    <div class="col-md-4">
    <div class="user__card">
  <div class="bg">
    <div class="perfil"><h1 class="profile">FN</h1></div>
	</div>
	<div class="main__content">
		<div class="upper__card">
			<div class="info">
				<h3>John Doe</h3>
				<p>Web Designer</p>
                <p>RFC: 23412323</p>
                <p>Correo</p>
			</div><!-- .info -->
			<div class="social">
			</div><!-- .social -->
		</div><!-- .upper__card -->
	</div><!-- .main__content -->
</div><!-- .user__card -->
    </div>
    <div class="col-md-4">
    <div class="user__card">
  <div class="bg">
    <div class="perfil"><h1 class="profile">FN</h1></div>
	</div>
	<div class="main__content">
		<div class="upper__card">
			<div class="info">
				<h3>John Doe</h3>
				<p>Web Designer</p>
                <p>RFC: 23412323</p>
                <p>Correo</p>
			</div><!-- .info -->
			<div class="social">
			</div><!-- .social -->
		</div><!-- .upper__card -->
	</div><!-- .main__content -->
</div><!-- .user__card -->
    </div>
</div>

</div>

@endsection

