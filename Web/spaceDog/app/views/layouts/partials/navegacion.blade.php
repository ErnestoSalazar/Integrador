
@section('navegacion')

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
                    <a class="navbar-brand" href="/reporte"><img src="{{asset('img/SpaceLogo.png')}}" alt="Space Bitch c:">
                    </a>
                </div>
                <div class="navbar3 navbar-collapse collapse">
                    <ul class="nav navbar-nav  visible-xs">
                    <li><a href="/reporte">Reporte</a></li>
                    <li><a href="/usuarios">Usuario</a></li>
                    <li><a href="/entradas">Entregas</a></li>
                    <li><a href="/barcos">Barcos</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="hidden-xs    "><a href="#">Nombre usuario</a></li>
                        <li><a href="#"> <i class="material-icons">power_settings_new</i></a></li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
            <!--/.container-fluid -->
        </nav>
    </div>
    <nav class="navbar hidden-xs">
        <div class="container-fluid">
            <div class="navbar3 navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/reporte">Reporte</a></li>
                    <li><a href="/usuarios">Usuario</a></li>
                    <li><a href="/entradas">Entregas</a></li>
                    <li><a href="/barcos">Barcos</a></li>
                </ul>
            </div>
        </div>
    </nav>

@endsection