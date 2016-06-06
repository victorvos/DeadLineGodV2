package servlets;

import model.Klas;
import model.KlasDAO;
import model.User;
import model.UserDAO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Created by Victor on 26-4-2016.
 */

public class Registreren extends HttpServlet {
    private String naam, emailadres, password1, password2;
    private String tussenvoegsel;
    private String achternaam;
    private int isDocent;
    private model.Klas klas;
    private model.UserDAO u;
    private model.KlasDAO k;

    public void init() throws ServletException{
        u = new UserDAO();
        k = new KlasDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        tussenvoegsel = request.getParameter("tussenvoegsel");
        isDocent = new Integer(request.getParameter("isDocent"));
        klas = new Klas(request.getParameter("klas"));
        naam = request.getParameter("naam");
        achternaam = request.getParameter("achternaam");
        emailadres = request.getParameter("emailadres");
        password1 = request.getParameter("pass1");
        password2 = request.getParameter("pass2");


        HttpSession session = request.getSession();

        RequestDispatcher rd = null;

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
            User user = new User(password1, emailadres, naam, achternaam, isDocent, klas);
            if (!tussenvoegsel.isEmpty()){
                user.setTussenvoegsel(tussenvoegsel);
            }
            u.registerUser(user);
            session.setAttribute("klassen", k.findAll());
            session.setAttribute("user", user);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
