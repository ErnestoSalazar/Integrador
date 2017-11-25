<!doctype html>
<html lang="{{ app()->getLocale() }}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Laravel</title>

        <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Raleway:100,600" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.css"> 
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body> 
<div class="header__nav">
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar3">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/"><img src="img/SpaceLogo.png" alt="Space Bitch c:">
        </a>
      </div>
      <div class="navbar3 navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right hidden-xs">
          <li><a href="#">Nombre usuario</a></li>
          <li><a href="#"> <i class="material-icons">power_settings_new</i></a></li>
        </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
    <!--/.container-fluid -->
  </nav>
</div>
    <nav class="navbar">
        <div class="container-fluid">
          <div class="navbar3 navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Reporte</a></li>
                <li><a href="/usuario">Usuario</a></li>
                <li><a href="/entregas">Entregas</a></li>
                <li><a href="/barcos">Barcos</a></li>
            </ul>  
            <ul class="nav navbar-nav navbar-right hidden-lg">
            <li><a href="#">Nombre usuario</a></li>
                <li><a href="#"> <i class="material-icons">power_settings_new</i></a></li>
            </ul>      
          </div>
        </div>
    </nav>
    <div class="container">
        @yield('content')
        @yield('usuario')
        @yield('entregas')
        @yield('barcos')
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/docs.min.js"></script>
</body>
</html>
