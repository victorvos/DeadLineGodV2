package servlets;

import model.Deadline;
import model.Klas;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Eigenaar on 22-5-2016.
 */
public class DeadlineServlet extends HttpServlet{
    private String naam, beschrijving, URI, datum, beoordeling;
    private model.DeadlineDAO d;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        naam = request.getParameter("naam");
        beschrijving = request.getParameter("beschrijving");
        URI = request.getParameter("URI");
        datum = request.getParameter("datum");
        beoordeling = request.getParameter("beoordeling");
        beschrijving = request.getParameter("beschrijving");

        HttpSession session = request.getSession();

        RequestDispatcher rd = null;

        User userSession = (User) session.getAttribute("loggedUser");

        if (userSession == null) {
            rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("message", "<font color=red>U bent nog niet ingelogd</font>");
            rd.include(request, response);
        } else if (naam.isEmpty()||datum.isEmpty()) {
            rd = request.getRequestDispatcher("/deadline/"+ userSession.getK() + "/mydeadlines.jsp");
            request.setAttribute("message", "<font color=red>Vul alle velden in aub !</font>");
            rd.include(request, response);
        } else {
            Deadline deadLine = new Deadline(naam, datum, userSession.getK());
            if(!URI.isEmpty()){
                deadLine.setURI(URI);
            }
            if(!beschrijving.isEmpty()){
                deadLine.setURI(beschrijving);
            }
            if(!beoordeling.isEmpty()){
                deadLine.setURI(beoordeling);
            }
            d.addDeadline(deadLine);
            rd = request.getRequestDispatcher("/deadline/"+ userSession.getK() + "/mydeadlines.jsp");
            rd.forward(request, response);
        }
    }
}

