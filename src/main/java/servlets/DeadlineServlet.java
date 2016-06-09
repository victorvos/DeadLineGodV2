package servlets;

import model.Deadline;
import model.DeadlineDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Eigenaar on 22-5-2016.
 */
public class DeadlineServlet extends HttpServlet{
    private String naam, beschrijving, URI, beoordeling;
    private model.DeadlineDAO d;

    public void init() throws ServletException{
        d = new DeadlineDAO();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        naam = request.getParameter("naam");
        beschrijving = request.getParameter("beschrijving");
        URI = request.getParameter("URI");

        String startDateStr = request.getParameter("datum");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date datum = null;
        try {
            datum = sdf.parse(startDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date sqlDate = new java.sql.Date(datum.getTime());

        beoordeling = request.getParameter("beoordeling");
        beschrijving = request.getParameter("beschrijving");

        HttpSession session = request.getSession();

        RequestDispatcher rd = null;

        User userSession = (User) session.getAttribute("loggedUser");

        if (userSession == null) {
            rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("message", "<font color=red>U bent nog niet ingelogd</font>");
            rd.include(request, response);
        } else if (naam.isEmpty()||datum == null) {
            rd = request.getRequestDispatcher("/deadline/mydeadlines.jsp");
            request.setAttribute("message", "<font color=red>Vul alle velden in aub !</font>");
            rd.include(request, response);
        } else {
            Deadline deadLine = new Deadline(naam, sqlDate, userSession.getK());
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
            rd = request.getRequestDispatcher("/deadline/mydeadlines.jsp");
            rd.forward(request, response);
        }
    }
}

