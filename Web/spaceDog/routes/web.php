<?php


Route::get('/', function () {
    return view('index');
});

Route::get('usuario', function () {
    return view('usuario');
});

Route::get('entregas', function () {
    return view('entregas');
});

Route::get('barcos', function () {
    return view('barcos');
});

Route::get('login', function () {
    return view('login');
});