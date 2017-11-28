<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
*/

Route::get('/usuarios','UsuarioController@index');
Route::get('login', 'LoginController@index');
Route::get('/barcos','BarcoController@index');
Route::get('/entradas', 'EntradaController@index');
Route::get('/reporte','ReporteController@index');


Route::post('/usuarios/edit/{id}', 'UsuarioController@editUsuario');
Route::post('/usuarios/delete/{id}','UsuarioController@deleteUsuario');


Route::put('/barcos/edit/{id}','BarcoController@updateBarco');

Route::put('/entradas/edit/{id}','EntradaController@updateEntrada');


Route::resource('login','LoginController');
Route::resource('usuarios','UsuarioController');
Route::resource('barcos', 'BarcoController');
Route::resource('cargas', 'CargaController');
Route::resource('entradas','EntradaController');