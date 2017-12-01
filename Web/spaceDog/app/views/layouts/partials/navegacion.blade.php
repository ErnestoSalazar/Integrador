
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
                    <a class="navbar-brand" href="/"><img src="{{asset('img/SpaceLogo.png')}}" alt="Space Bitch c:">
                    </a>
                </div>
                <div class="navbar3 navbar-collapse collapse">
                    <ul class="nav navbar-nav  visible-xs">
                    <li><a href="/usuarios" class="nav-options"><i class="material-icons">supervisor_account</i>  Usuario</a></li>
                    @if(Session::get('rol') == 'Administrador')
                        <li><a href="/barcos" class="nav-options"><i class="material-icons">directions_boat</i>  Barcos</a></li>
                        <li><a href="/entradas" class="nav-options"><i class="material-icons">system_update_alt</i>  Entradas</a></li>
                    @endif
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="hidden-xs"><a href="#">{{{Session::get('nombre')}}} {{{Session::get('apellido')}}} - {{{Session::get('rol')}}}</a></li>
                        <li><a href="{{route('login.show',Session::get('userId'))}}"class=""><i class="material-icons">power_settings_new</i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <nav class="navbar hidden-xs">
        <div class="container-fluid">
            <div class="navbar3 navbar-collapse collapse">
                <ul class="nav navbar-nav">
                @if(Session::get('rol') == 'Administrador')
                    <li><a href="/usuarios">Usuario</a></li>
                    <li><a href="/barcos">Barcos</a></li>
                @endif
                    <li><a href="/entradas">Entradas</a></li>
                </ul>
            </div>
        </div>
    </nav>

@endsection