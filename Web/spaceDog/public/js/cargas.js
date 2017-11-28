var cargasId = [];
var nombreBarco;
$(document).ready(function () {

    $('button.btn-carga').click(function(){

        var barcoId = $('select[name="option-barco"]').val();
        var especie = $('select[name="option-especie"]').val();
        var talla   = $('select[name="option-talla"]').val();
        var condicion = $('select[name="option-condicion"]').val();

        var toneladas = $('input[name="toneladas"]').val();
        var temperatura = $('input[name="temperatura"]').val();

        var tabla = $('#tabla-cargas tbody');
        var entradaForm = $('#entrada-form');
        postCarga(nombreBarco,barcoId,especie,talla,condicion,toneladas,temperatura,tabla,entradaForm,false);


    });// end click


    $('button.btn-carga-update').click(function(){
        var barcoId = $('select[name="option-barco-update"]').val();
        var especie = $('select[name="option-especie-update"]').val();
        var talla   = $('select[name="option-talla-update"]').val();
        var condicion = $('select[name="option-condicion-update"]').val();

        var toneladas = $('input[name="toneladas-update"]').val();
        var temperatura = $('input[name="temperatura-update"]').val();

        var tabla = $('#tabla-cargas-update tbody');
        var entradaForm = $('#update-entrada-form');
        postCarga(nombreBarco,barcoId,especie,talla,condicion,toneladas,temperatura,tabla,entradaForm, true);
    });// end click


    $('select[name="option-barco"]').on('change', function(){
        nombreBarco = $(':selected',this).text();
    });// end on change

    $('select[name="option-barco-update"]').on('change', function(){
        nombreBarco = $(':selected',this).text();
    });// end on change

});// end ready



function postCarga(barcoNombre,barcoId, especie, talla, condicion, toneladas, temperatura, tabla, entradaForm, isUpdating){
    $.ajax({
        url: 'cargas/',
        type: 'POST',
        data: {
            barcoId: barcoId,
            especie: especie,
            talla: talla,
            condicion: condicion,
            toneladas: toneladas,
            temperatura: temperatura
        },
        success: function(data){
            data = JSON.parse(data);
            cargasId.push(data.id);
            var row = '<tr><td>'+barcoNombre+'</td>' +
                '<td>'+data.cantidad+'</td>' +
                '<td>'+data.especie+'</td>' +
                '<td>'+data.talla+'</td>' +
                '<td>'+data.temperatura+'</td>' +
                '<td>'+data.condicion+'</td>' +
                '<td><button type="submit" class="btn bckg-transparent"><i class="material-icons">check</i></button>' +
                '<button type="submit" class="btn bckg-transparent"><i class="material-icons">close</i></button>' +
                '</td>' +
                '</tr>';
            $(tabla).append(row);

            var input;
            if(isUpdating){
                input = '<input type="text" name="cargasIdUpdate[]" value="'+data.id+'" hidden>';
            }
            else{
                input = '<input type="text" name="cargasId[]" value="'+data.id+'" hidden>';
            }
            $(entradaForm).append(input);

        }
    });// end ajax
}

// function getCarga(id){
//     $.getJSON('cargas/'+id, function(data){
//         console.log(data);
//     });// end getJSON
//}