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
    private String naam, beoordeling, beschrijving, URI;
    private int ID;
    private model.DeadlineDAO dDAO;
    private model.Deadline deadline;
    private java.sql.Date sqlDate;

    public void init() throws ServletException{
        dDAO = new DeadlineDAO();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        User userSession = (User) session.getAttribute("loggedUser");

        if (request.getParameter("makeDeadline")!=null) {
            naam = request.getParameter("naam");
            beschrijving = request.getParameter("beschrijving");
            URI = request.getParameter("URI");


            String startDateStr = request.getParameter("datum");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date datum = null;

            while (datum == null) {
                try {
                    datum = sdf.parse(startDateStr);
                } catch (ParseException e) {
                    System.out.println("Voer een valide datum in aub (yyyy-MM-dd)");
                }
            }
            sqlDate = new java.sql.Date(datum.getTime());

            beoordeling = request.getParameter("beoordeling");
            beschrijving = request.getParameter("beschrijving");

            if (!beoordeling.equals("")){
                if(Integer.parseInt(beoordeling) > 10  || Integer.parseInt(beoordeling) <= 0){
                    request.setAttribute("message", "<font color=red>Vul alle verplichte velden in aub en een beoordeling Cijfer 1-10!</font>");
                }
                else{
                    request.setAttribute("message", "");
                }
            }

            if (userSession == null) {
                rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("message", "<font color=red>U bent nog niet ingelogd</font>");
                rd.forward(request, response);
            } else if (naam.equals("") || sqlDate.equals(""))  {
                request.setAttribute("message", "<font color=red>Vul alle verplichte velden in aub !</font>");
                rd = request.getRequestDispatcher("/deadline/mydeadlines.jsp");
                rd.forward(request, response);
            } else {
                Deadline deadLine = new Deadline(naam, sqlDate, userSession.getK());
                if (!URI.isEmpty()) {
                    deadLine.setURI(URI);
                }
                if (!beschrijving.isEmpty()) {
                    deadLine.setBeschrijving(beschrijving);
                }
                if (beoordeling != null) {
                    deadLine.setBeoordeling(beoordeling);
                }
                ID = dDAO.addDeadline(deadLine);
                deadLine.setID(ID);

                rd = request.getRequestDispatcher("/deadline/mydeadlines.jsp");
                rd.forward(request, response);
            }
        } else if (request.getParameter("updateDeadline")!=null) {
            String IDParameter = request.getParameter("updateDeadline");
            ID = Integer.parseInt(IDParameter);

            naam = request.getParameter("naamUpdate"+ID);
            beschrijving = request.getParameter("beschrijvingUpdate"+ID);
            URI = request.getParameter("URIUpdate"+ID);

            String startDateStr = request.getParameter("datumUpdate"+ID);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date datum = null;

            while (datum == null) {
                try {
                    datum = sdf.parse(startDateStr);
                } catch (ParseException e) {
                    System.out.println("Voer een valide datum in aub (yyyy-MM-dd)");
                }
            }
            sqlDate = new java.sql.Date(datum.getTime());

            beoordeling = request.getParameter("beoordelingUpdate"+ID);

            if (!beoordeling.equals("")){
                if(Integer.parseInt(beoordeling) > 10  || Integer.parseInt(beoordeling) <= 0){
                    request.setAttribute("message", "<font color=red>Vul alle verplichte velden in aub en een beoordeling Cijfer 1-10!</font>");
                }
                else{
                    request.setAttribute("message", "");
                }
            }

            if (userSession == null) {
                rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("message", "<font color=red>U bent nog niet ingelogd</font>");
                rd.forward(request, response);
            } else {
                Deadline deadLine = new Deadline(naam, sqlDate, userSession.getK());
                if (!URI.isEmpty()) {
                    deadLine.setURI(URI);
                }
                if (!beschrijving.isEmpty()) {
                    deadLine.setBeschrijving(beschrijving);
                }
                if (beoordeling != null) {
                    deadLine.setBeoordeling(beoordeling);
                }
                if (dDAO.findByID(ID)) {
                    deadLine.setID(ID);
                    dDAO.updateDeadline(deadLine);
                }
                rd = request.getRequestDispatcher("/deadline/mydeadlines.jsp");
                rd.forward(request, response);
            }
        }
        else if (request.getParameter("deleteDeadline")!=null) {
            String IDParameter = request.getParameter("deleteDeadline");
            ID = Integer.parseInt(IDParameter);

            naam = request.getParameter("naamUpdate"+ID);
            beschrijving = request.getParameter("beschrijvingUpdate"+ID);
            URI = request.getParameter("URIUpdate"+ID);

            String startDateStr = request.getParameter("datumUpdate"+ID);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date datum = null;

            while (datum == null) {
                try {
                    datum = sdf.parse(startDateStr);
                } catch (ParseException e) {
                    System.out.println("Voer een valide datum in aub (yyyy-MM-dd)");
                }
            }
            sqlDate = new java.sql.Date(datum.getTime());

            beoordeling = request.getParameter("beoordelingUpdate"+ID);

            if (userSession == null) {
                rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("message", "<font color=red>U bent nog niet ingelogd</font>");
                rd.forward(request, response);
            } else {
                Deadline deadLine = new Deadline(naam, sqlDate, userSession.getK());
                if (!URI.isEmpty()) {
                    deadLine.setURI(URI);
                }
                if (!beschrijving.isEmpty()) {
                    deadLine.setBeschrijving(beschrijving);
                }
                if (beoordeling != null) {
                    deadLine.setBeoordeling(beoordeling);
                }
                if (dDAO.findByID(ID)) {
                    deadLine.setID(ID);
                    dDAO.delete(deadLine);
                }
                rd = request.getRequestDispatcher("/deadline/mydeadlines.jsp");
                rd.forward(request, response);
            }
        }
    }
}