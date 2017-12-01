$(document).ready(function(){

    $('[data-toggle="tooltip"]').tooltip();

    
    $('button.btn-option-editar').click(function(){
        var entradaId = $(this).children(0).val();

        var url = $('#update-entrada-form').prop('action');

        url = url+"/edit/"+entradaId;

        $('#update-entrada-form').prop('action', url);

        getCargasFromEntrada(entradaId);

    });// end click

});// end ready


function getCargasFromEntrada(entradaId){
    $.getJSON('entradas/'+entradaId, function(data){
        $.each(data.cargas,function(key,val){
            var row = '<tr><td>'+val.barco.nombre+'</td>' +
                '<td>'+val.cantidad+'</td>' +
                '<td>'+val.especie+'</td>' +
                '<td>'+val.talla+'</td>' +
                '<td>'+val.temperatura+'</td>' +
                '<td>'+val.condicion+'</td>' +
                '<td><button type="submit" class="btn bckg-transparent"><i class="material-icons">check</i></button>' +
                '<button type="submit" class="btn bckg-transparent"><i class="material-icons">close</i></button>' +
                '</td>' +
                '</tr>';

            $('#tabla-cargas-update tbody').append(row);

            var input = '<input type="text" name="cargasIdUpdate[]" value="'+val.id+'" hidden>';
            $('#update-entrada-form').append(input);
        });// end each
    }); // end getJSON
}
