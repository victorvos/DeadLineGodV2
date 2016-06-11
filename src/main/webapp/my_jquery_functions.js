
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

    $( document ).ready(function() {

        $( "#showForm" ).click(function() {
            $("#countryForm").toggle();
        });

        $( "#showList" ).click(function() {
            $("#countryList").toggle();
        });

        $("#all").click(function () {
            $.get( "restservices/countries/", function( data ) {
                $("#countries tr").remove();
                var header = "<tr>";
                var headerDone = false;
                var string = "";
                data.forEach(function(entry) {
                    string = string + "<tr>";
                    for (var p in entry) {
                        if (!headerDone) {
                            header = header + "<td>" + p + "</td>";
                        }
                        string = string + "<td>" + entry[p] + "</td>";
                    }
                    headerDone = true;
                    string = string + "</tr>";
                    header = header + "</tr>";
                });
                $('#countries').append(header + string);
            });
        });

        $("#populations").click(function () {
            $.get( "restservices/countries/largestpopulations", function( data ) {
                $("#countries tr").remove();
                var header = "<tr>";
                var headerDone = false;
                var string = "";
                data.forEach(function(entry) {
                    string = string + "<tr>";
                    for (var p in entry) {
                        if (!headerDone) {
                            header = header + "<td>" + p + "</td>";
                        }
                        string = string + "<td>" + entry[p] + "</td>";
                    }
                    headerDone = true;
                    string = string + "</tr>";
                    header = header + "</tr>";
                });
                $('#countries').append(header + string);
            });
        });

        $("#surfaces").click(function () {
            $.get( "restservices/countries/largestsurfaces", function( data ) {
                $("#countries tr").remove();
                var header = "<tr>";
                var headerDone = false;
                var string = "";
                data.forEach(function(entry) {
                    string = string + "<tr>";
                    for (var p in entry) {
                        if (!headerDone) {
                            header = header + "<td>" + p + "</td>";
                        }
                        string = string + "<td>" + entry[p] + "</td>";
                    }
                    headerDone = true;
                    string = string + "</tr>";
                    header = header + "</tr>";
                });
                $('#countries').append(header + string);
            });
        });
    });

});