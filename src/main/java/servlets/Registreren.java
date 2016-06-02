package servlets;

import model.DeadlineService;
import model.Klas;
import model.ServiceProvider;
import model.User;
import model.UserDAO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Victor on 26-4-2016.
 */
public class Registreren extends HttpServlet {
    private String naam, emailadres, password1, password2;
    private String tussenvoegsel;
    private int isDocent;
    private model.Klas klas;
    private model.UserDAO u;


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        tussenvoegsel = request.getParameter("tussenvoegsel");
        isDocent = new Integer(request.getParameter("isDocent"));
        klas = new Klas(request.getParameter("klas"));
        naam = request.getParameter("naam");
        emailadres = request.getParameter("emailadres");
        password1 = request.getParameter("pass1");
        password2 = request.getParameter("pass2");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        RequestDispatcher rd = null;
        DeadlineService service = ServiceProvider.getDeadlineService();

        if(emailadres.isEmpty()||naam.isEmpty()||emailadres.isEmpty()|| password1.isEmpty()||password2.isEmpty())        {
            rd = request.getRequestDispatcher("registreren.jsp");
            request.setAttribute("message", "<font color=red>Vul alle velden in aub !</font>");
            rd.include(request, response);
        }
        else if (!password1.equals(password2)) {
            rd = request.getRequestDispatcher("registreren.jsp");
            request.setAttribute("message", "<font color=red>Wachtwoorden komen niet overeen !</font>");
            rd.include(request, response);
        } else {
            User user = new User(password1, emailadres, naam, tussenvoegsel, isDocent, klas);
            u.registerUser(user);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
