$(document).ready(function() {

    $('#recuperar').hide();
    
    $('.btn-recuperar').click(function() {
        $('.login').hide();
        $('#recuperar').show();
    });

    $('#regresar').click(function() {
        $('.login').show();
        $('#recuperar').hide();
    })
});

