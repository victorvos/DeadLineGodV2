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
    private String emailadres, password;
    private model.UserDAO userDAO;
    private model.KlasDAO klasDAO;

    public void init() throws ServletException{
        userDAO = new UserDAO();
        klasDAO = new KlasDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        emailadres = request.getParameter("emailadres");
        password = request.getParameter("password");

        HttpSession session = request.getSession();
        RequestDispatcher rd = null;

        User user = userDAO.getUser(emailadres);

        if (emailadres.isEmpty()||password.isEmpty()) {
            rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("message", "<font color=red>Vul alle velden in aub !</font>");
            rd.include(request, response);
        } else if (user == null) {
            rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("message", "<font color=red>Gebruikersnaam en Wachtwoord combinatie is niet bekend</font>");
            rd.include(request, response);
        } else if (userDAO.login(user)){
            session.setAttribute("loggedUser", user);
            session.setAttribute("klassen", klasDAO.findAll());
            rd = request.getRequestDispatcher("/deadline/"+ user.getK() + "/mydeadlines.jsp");
            rd.forward(request, response);
        }
    }
}
