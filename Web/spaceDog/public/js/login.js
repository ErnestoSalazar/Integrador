$(document).ready(function() {

    $('#recuperar').hide();
    
    $('.btn-recuperar').click(function() {
        $('.login').hide();
        $('#recuperar').show();
    });// end click

    $('#regresar').click(function() {
        $('.login').show();
        $('#recuperar').hide();
    });// end click

    $('button.btn-recuperar-password').click(function(){
        var email = $('.login-recover').val();

        $.ajax({
            url: 'login/recover',
            type: 'POST',
            data:{ "mailTo": email},
            success: function (data) {
                if(data === 'true'){
                    location.reload();
                }
            }
        });
    });// end click


});

