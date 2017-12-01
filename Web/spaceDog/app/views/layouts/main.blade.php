<!doctype html>
<html lang="{{ app()->getLocale() }}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>@yield('page_title')</title>

    @yield('fonts_bootstrap')
    @yield('header_elements')

</head>
<body>
@yield('navegacion')
<div class="container">
    @yield('content')
</div>


@yield('footer_scripts')
</body>
</html>
