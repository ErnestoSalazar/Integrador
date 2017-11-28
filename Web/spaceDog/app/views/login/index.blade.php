@extends('layouts.main')
@extends('login.partials.header')

@section('content')

    <section class="container">
        @if(Session::has('message'))
            <blockquote>
                {{{Session::get('message')}}}
            </blockquote>
        @endif
        <div class="login">
            <h1 class="login-header">Space Dog</h1>

            {{Form::open(array('route'=>'login.store'))}}
                <p>{{Form::text('login',null,['placeholder'=> 'Email'])}}</p>
                <p>{{Form::password('password',['placeholder'=>'Password'])}}</p>
                <div class="login-btns">
                     <button type="button" class="btn-login" >
                        <input type="submit" name="sent" value="Login">
                    </button>
                    <button type="button" class="btn-recuperar">Recuperar Contraseña</button>
                </div>

            {{Form::close()}}
        </div>
    </section>

@stop