<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Deadline" %>
<%@ page import="java.util.List" %>
<%@ page import="model.DeadlineDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MyDeadlines</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../css/clean-blog.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

  <title>MyDeadlines</title>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/index.jsp"><font color=#8b0000>Logout</font></a>
                </li>
                <li>
                    <a href="/deadline/mydeadlines.jsp"><font color=#8b0000>My Deadlines</font></a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('../img/deadline.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="page-heading">
                    <h1><font color=black>My Deadlines</font></h1>
                    <%--<%--%>
                        <%--if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {--%>
                            <%--out.println("You are not logged in");--%>
                        <%--}--%>
                        <%--else {--%>
                            <%--out.print("Welkom op jou Blog ");--%>
                        <%--}--%>
                    <%--%>--%>
                    <hr class="small">
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content -->
    <div class="deadlineForm" style="width:500px;
            float:left;
            padding:10px;" >
            <!-- Contact Form - Enter your email address on line 19 of the mail/contact_me.php file to make this form work. -->
            <!-- WARNING: Some web hosts do not allow emails to be sent through forms to common mail hosts like Gmail or Yahoo. It's recommended that you use a private domain email address! -->
            <!-- NOTE: To use the contact form, your site must be on a live web host with PHP! The form will not work locally! -->
            <form name="sentMessage" action="/deadline/mydeadlines.do" method="post">

                <%
                    model.DeadlineDAO dDAO;
                    dDAO = new DeadlineDAO();

                    User userSession = (User) session.getAttribute("loggedUser");

                    List<Deadline> deadlineListThisWeek = dDAO.getDeadlinesThisWeekPerKlas(userSession.getK());
                    List<Deadline> deadlineListThisMonth = dDAO.getDeadlinesThisMonthPerKlas(userSession.getK());

                    request.setAttribute("user", userSession);

                    request.setAttribute("deadLinesThisWeek", deadlineListThisWeek);
                    request.setAttribute("deadlineListThisMonth", deadlineListThisMonth);
                %>

                <%!
                    DateFormat tipe = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar cal = Calendar.getInstance();
                %>
                <%
                    String time = tipe.format(cal.getTime());
                    request.setAttribute("date", time);
                %>

                <p>${message}</p>

                <div>
                    <div class="form-group col-xs-12 floating-label-form-group controls">
                        <label>Datum</label>
                        <input type="text" placeholder="Datum" name="datum" required data-validation-required-message="Datum" value = "${date}" size="40">
                    </div>
                </div>

                <div>
                    <div class="form-group col-xs-12 floating-label-form-group controls">
                        <label>Naam</label>
                        <input type="text" placeholder="Naam" name="naam" required data-validation-required-message="Naam" value = "" size="40">
                    </div>
                </div>

                <div>
                    <div class="form-group col-xs-12 floating-label-form-group controls">
                        <label>Beschrijving</label>
                        <textarea rows="4" cols="40" placeholder="Beschrijving" name="beschrijving" required data-validation-required-message="Beschrijving">${post.beschrijving}</textarea>
                    </div>
                </div>

                <div>
                    <div class="form-group col-xs-12 floating-label-form-group controls">
                        <label>URI</label>
                        <input type="text" placeholder="URI" name="URI" required data-validation-required-message="URI" value = "${post.URI}" size="40">
                    </div>
                </div>

                <c:if test="${user.isDocent() == 1}">
                    <div>
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label>Beoordeling</label>
                            <input type="text" placeholder="beoordeling" name="beoordeling" required data-validation-required-message="beoordeling" value = "${post.beoordeling}" size="40">
                        </div>
                    </div>
                </c:if>
                <c:if test="${user.isDocent() == 0}">
                    <div>
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label>Beoordeling</label>
                            <input type="text" placeholder="beoordeling" name="beoordeling" required data-validation-required-message="beoordeling" value = "${post.beoordeling}" size="40" readonly>
                        </div>
                    </div>
                </c:if>
                <%--<div class="row control-group">--%>
                    <%--<div class="form-group col-xs-12 floating-label-form-group controls">--%>
                        <%--<label>Datum</label>--%>
                        <%--<textarea rows="10" cols="60" placeholder="Datum" name="datum" required--%>
                                  <%--data-validation-required-message=" value = ""></textarea>--%>
                    <%--</div>--%>
                    <%--<div class="form-group col-xs-12 floating-label-form-group controls">></div>--%>
                <%--</div>--%>
            </form>
    </div>


<div class="deadlineThisWeek" style="width:500px;
            float:left;
            padding:20px;" >
        <form name="sentMessage" action="/deadline/mydeadlines.do" method="post">
            <br>
            <br>
            <%
                String naam, beschrijving, URI, datum;
                int beoordeling;
                dDAO = new DeadlineDAO();

                naam = request.getParameter("naamUpdate");
                URI = request.getParameter("URIUpdate");
                if (request.getParameter("beoordelingUpdate") != null){
                    beoordeling = Integer.parseInt(request.getParameter("beoordelingUpdate"));
                }
                else{
                    beoordeling = 0;
                }
                beschrijving = request.getParameter("beschrijvingUpdate");

                if (request.getParameter("datumUpdate") != null){
                    datum = request.getParameter("datumUpdate");
                    java.sql.Date sqlDate = java.sql.Date.valueOf(datum);
                    Deadline deadline = new Deadline(naam, sqlDate, userSession.getK());
                    deadline.setBeschrijving(beschrijving);
                    deadline.setBeoordeling(beoordeling);
                    deadline.setURI(URI);
                    request.setAttribute("updateDeadline", dDAO.updateDeadline(deadline));
                }
            %>
        <c:forEach var="post" items="${deadLinesThisWeek}" varStatus="vs">
            <div class="post">
                ${post.datum} - <b>${post.naam}</b>
                <!-- Button trigger modal -->
                <div class="button">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}">
                        Aanpassen
                    </button>
                </div>
                <!-- Modal -->
                <div class="modal fade" id="myModal${vs.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" id="myModal${vs.index}"`role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="myModalLabel">${post.datum} - <b>${post.naam}</b></h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row control-group">
                                    <div class="form-group col-xs-12 floating-label-form-group controls">
                                        <label>Datum</label>
                                        <input type="text" placeholder="Datum" name="datumUpdate" required data-validation-required-message="Datum" value = "${post.datum}" size="40">
                                    </div>
                                </div>
                                <div class="row control-group">
                                    <div class="form-group col-xs-12 floating-label-form-group controls">
                                        <label>Naam</label>
                                        <input type="text" placeholder="Naam" name="naamUpdate" required data-validation-required-message="Naam" value = "${post.naam}" size="40">
                                    </div>
                                </div>
                                <div class="row control-group">
                                    <div class="form-group col-xs-12 floating-label-form-group controls">
                                        <label>Beschrijving</label>
                                        <textarea rows="4" cols="40" placeholder="Beschrijving" name="beschrijvingUpdate" required data-validation-required-message="Beschrijving">${post.beschrijving}</textarea>
                                    </div>
                                </div>
                                <div class="row control-group">
                                    <div class="form-group col-xs-12 floating-label-form-group controls">
                                        <label>URI</label>
                                        <input type="text" placeholder="URI" name="URIUpdate" required data-validation-required-message="URI" value = "${post.URI}" size="40">
                                    </div>
                                </div>
                                <c:if test="${user.isDocent() == 1}">
                                    <div class="row control-group">
                                        <div class="form-group col-xs-12 floating-label-form-group controls">
                                            <label>Beoordeling</label>
                                            <input type="text" placeholder="beoordeling" name="beoordelingUpdate" required data-validation-required-message="beoordeling" value = ${post.beoordeling} size="40">
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${user.isDocent() == 0}">
                                    <div class="row control-group">
                                        <div class="form-group col-xs-12 floating-label-form-group controls">
                                            <label>Beoordeling</label>
                                            <input type="text" placeholder="beoordeling" name="beoordelingUpdate" required data-validation-required-message="beoordeling" value = ${post.beoordeling} size="40" readonly>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" data-toggle="${updateDeadline}">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        </form>
    </div>






                <%--<c:forEach var="post" items="${deadlineListThisMonth}">--%>
                    <%--<div class="post">--%>
                        <%--${post.datum} - <b>${post.naam}</b> - ${post.beoordeling}--%>
                        <%--<!-- Button trigger modal -->--%>
                        <%--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">--%>
                            <%--Aanpassen--%>
                        <%--</button>--%>
                        <%--<!-- Modal -->--%>
                        <%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
                            <%--<div class="modal-dialog" role="document">--%>
                                <%--<div class="modal-content">--%>
                                    <%--<div class="modal-header">--%>
                                        <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                                            <%--<span aria-hidden="true">&times;</span>--%>
                                        <%--</button>--%>
                                        <%--<h4 class="modal-title" id="myModalLabel">Modal title</h4>--%>
                                    <%--</div>--%>
                                    <%--<div class="modal-body">--%>
                                        <%--...--%>
                                    <%--</div>--%>
                                    <%--<div class="modal-footer">--%>
                                        <%--<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>--%>
                                        <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</c:forEach>--%>


<!-- jQuery -->
<script src="../js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../js/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../js/clean-blog.min.js"></script>

</body>
</html>