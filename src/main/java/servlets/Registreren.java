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
    private String naam, emailadres, password1, password2, tussenvoegsel, achternaam, klasCode;
    private int isDocent;
    private model.Klas klas;
    private model.UserDAO userDAO;
    private model.KlasDAO klassenDAO;

    public void init() throws ServletException{
        userDAO = new UserDAO();
        klassenDAO = new KlasDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("klassen", klassenDAO.findAll());

        tussenvoegsel = request.getParameter("tussenvoegsel");
        isDocent = Integer.parseInt(request.getParameter("isDocent"));
        klasCode = request.getParameter("klasCode");
        klas = new Klas(klasCode);
        naam = request.getParameter("naam");
        achternaam = request.getParameter("achternaam");
        emailadres = request.getParameter("emailadres");
        password1 = request.getParameter("pass1");
        password2 = request.getParameter("pass2");

        RequestDispatcher rd = null;

        if(emailadres.isEmpty()||naam.isEmpty()||password1.isEmpty()||password2.isEmpty()||isDocent==-1||klasCode.isEmpty())        {
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
            userDAO.registerUser(user);
            session.setAttribute("user", user);
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
