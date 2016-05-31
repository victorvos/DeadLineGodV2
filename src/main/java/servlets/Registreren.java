package servlets;

import model.BlogService;
import model.ServiceProvider;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Victor on 26-4-2016.
 */
public class Registreren extends HttpServlet {
    private String gebruikersnaam, naam, emailadres, password1, password2;


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        gebruikersnaam = request.getParameter("gebruikersnaam");
        naam = request.getParameter("naam");
        emailadres = request.getParameter("emailadres");
        password1 = request.getParameter("pass1");
        password2 = request.getParameter("pass2");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        RequestDispatcher rd = null;
        BlogService service = ServiceProvider.getBlogService();

        if(gebruikersnaam.isEmpty()||naam.isEmpty()||emailadres.isEmpty()|| password1.isEmpty()||password2.isEmpty())        {
            rd = request.getRequestDispatcher("registreren.jsp");
            request.setAttribute("message", "<font color=red>Vul alle velden in aub !</font>");
            rd.include(request, response);
        }
        else if (!password1.equals(password2)) {
            rd = request.getRequestDispatcher("registreren.jsp");
            request.setAttribute("message", "<font color=red>Wachtwoorden komen niet overeen !</font>");
            rd.include(request, response);
        } else {
            service.registerUser(gebruikersnaam, password1, emailadres, naam);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
