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

Route::resource('login','LoginController');
Route::resource('usuarios','UsuarioController');
Route::resource('entradas','EntradaController');