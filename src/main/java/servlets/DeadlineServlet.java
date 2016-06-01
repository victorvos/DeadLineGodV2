package servlets;

import model.DeadlineService;
import model.Klas;
import model.ServiceProvider;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Eigenaar on 22-5-2016.
 */
public class DeadlineServlet extends HttpServlet{
    private String naam, beschrijving, URI, datum;

    int beoordeling;
    Klas klas;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        naam = request.getParameter("naam");
        beschrijving = request.getParameter("beschrijving");
        URI = request.getParameter("URI");
        datum = request.getParameter("datum");

        HttpSession session = request.getSession();

        RequestDispatcher rd = null;

        DeadlineService service = ServiceProvider.getDeadlineService();
        User userSession = (User) session.getAttribute("loggedUser");

//        User user = service.logingUser(userSession.getUsername(), userSession.getPassword());

        if (userSession == null) {
            rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("message", "<font color=red>U bent nog niet ingelogd</font>");
            rd.include(request, response);
        } else if (naam.isEmpty()||datum.isEmpty()) {
            rd = request.getRequestDispatcher("/blogger/myaccount.jsp");
            request.setAttribute("message", "<font color=red>Vul alle velden in aub !</font>");
            rd.include(request, response);
        } else {
            service.addDeadlineForKlas(naam, beschrijving, URI, datum, beoordeling, klas);
            rd = request.getRequestDispatcher("/blogger/myaccount.jsp");
            rd.forward(request, response);
        }
    }
}

