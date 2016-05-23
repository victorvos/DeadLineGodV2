$(document).ready(function() {

    $("#showForm").click(function () {
        $("#countryForm").toggle();
    });

    $("#populations").click(function () {
        $.get( "restservices/countries/largestpopulations", function( data ) {
            $( "#countries" ).append( data );
            console.log( "Load was performed." );
        });
    });

});