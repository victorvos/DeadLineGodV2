
$(document).ready(function() {

    $("#showForm").click(function () {
        $("#countryForm").toggle();
    });

    $("#showList").click(function () {
        $("#countryList").toggle();
    });

    $("#all").click(function () {
        $.get("restservices/countries", function(data) {
            $("#countries").append(data);
            console.log( "Load was performed." );
        });
    });

    $("#surfaces").click(function () {
        $.get("restservices/countries/largestsurfaces", function(data) {
            $("#surfaces").append(data);
            console.log( "Load was performed." );
        });
    });

    $("#populations").click(function () {
        $.get("restservices/countries/largestpopulations", function(data) {
            $("#populations").append(data);
            console.log( "Load was performed." );
        });
    });

});