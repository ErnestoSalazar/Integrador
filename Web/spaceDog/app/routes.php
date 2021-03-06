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
Route::get('/','UsuarioController@index');
Route::get('/usuarios','UsuarioController@index');
Route::get('login', 'LoginController@index');
Route::get('/barcos','BarcoController@index');
Route::get('/entradas', 'EntradaController@index');
Route::get('/reporte','ReporteController@index');


Route::post('/login/recover', 'LoginController@recoverPassword');

Route::post('/usuarios/edit/{id}', 'UsuarioController@editUsuario');
Route::post('/usuarios/delete/{id}','UsuarioController@deleteUsuario');


Route::post('/usuarios/search_results','UsuarioController@findByName');

Route::put('/barcos/edit/{id}','BarcoController@updateBarco');
Route::post('/barcos/nombre', 'BarcoController@findByNombre');
Route::post('/barcos/delete/{id}','BarcoController@deleteBarco');

Route::put('/entradas/edit/{id}','EntradaController@updateEntrada');
Route::post('/entradas/folio', 'EntradaController@findByFolio');
Route::post('/entradas/search_results','EntradaController@findByDate');



Route::resource('login','LoginController');
Route::resource('usuarios','UsuarioController');
Route::resource('barcos', 'BarcoController');
Route::resource('cargas', 'CargaController');
Route::resource('entradas','EntradaController');