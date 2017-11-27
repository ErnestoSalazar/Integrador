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
            <h1>Space Dog</h1>

            {{Form::open(array('route'=>'login.store'))}}
                <p>{{Form::text('login',null,['placeholder'=> 'Email'])}}</p>
                <p>{{Form::password('password',['placeholder'=>'Password'])}}</p>
                
                <p class="submit">
                     <button type="button" class="btn-login" >
                    <input type="submit" name="sent" value="Login">
                    </button>
                </p>
                <p class="submit"><input type="submit" value="Recuperar contraseña"></p>
            {{Form::close()}}
        </div>
    </section>

@stop