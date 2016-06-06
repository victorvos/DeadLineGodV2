package servlets;

import model.KlasDAO;
import model.User;
import model.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
 * Created by Eigenaar on 13-5-2016.
 */
public class LoginServlet extends HttpServlet {
    private String gebruikersnaam, password;
    private model.UserDAO u;
    private model.KlasDAO k;

    public void init() throws ServletException{
        u = new UserDAO();
        k = new KlasDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        gebruikersnaam = request.getParameter("gebruikersnaam");
        password = request.getParameter("password");

        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        User user = (User) session.getAttribute("user");

        if (gebruikersnaam.isEmpty()||password.isEmpty()) {
            rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("message", "<font color=red>Vul alle velden in aub !</font>");
            rd.include(request, response);
        } else if (user == null) {
            rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("message", "<font color=red>Gebruikersnaam en Wachtwoord combinatie is niet bekend</font>");
            rd.include(request, response);
        } else if (u.login(user)){
            session.setAttribute("loggedUser", user);
            session.setAttribute("klassen", k.findAll());
            rd = request.getRequestDispatcher("/deadline/"+ user.getK() + "/mydeadlines.jsp");
            rd.forward(request, response);
        }
    }
}
