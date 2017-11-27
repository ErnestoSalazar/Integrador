$(document).ready(function(){


    $('button.btn-option-editar').click(function(){
        var barcoId = $(this).children(0).val();

        var url = $('#update-barco').prop('action');

        url = url+'/edit/'+barcoId;

        $('#update-barco').prop('action', url);

    }); // end click

}); // end ready