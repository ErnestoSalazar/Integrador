$(document).ready(function(){


    $('button.btn-option-editar').click(function(){
        var userId = $(this).children(0).val();

        var url = $('#update-usuario').prop('action');
        url = url+"/edit/"+userId;

        $('#update-usuario').prop('action', url);

    });// end click


    $('button.btn-option-eliminar').click(function(){
        var userId = $(this).children(0).val();
        var url = $('#update-usuario').prop('action')+"/delete/"+userId;

        $.ajax({
            url: url,
            type: "POST",
            success: function(data){
                console.log(data);
                if(data == "true"){
                    location.reload();
                }
            }
        });// end ajax

    });// end click


});// end ready