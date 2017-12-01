var userId;
var baseUrl;
$(document).ready(function(){

    baseUrl = $('#update-usuario').prop('action');

    $('button.btn-option-editar').click(function(){
        var userId = $(this).children(0).val();

        $('#update-usuario').prop('action', baseUrl+"/edit/"+userId);

    });// end click


    $('button.btn-option-eliminar').click(function(){
        userId = $(this).children(0).val();
    });// end click

    $('button.btn-option-eliminar-confirm').click(function(){
        var url = baseUrl+"/delete/"+userId;
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