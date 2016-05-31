package servlets;

import model.BlogService;
import model.ServiceProvider;
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
public class BlogPostServlet extends HttpServlet{
    private String onderwerp, text;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        onderwerp = request.getParameter("subject");
        text = request.getParameter("text");

        HttpSession session = request.getSession();

        RequestDispatcher rd = null;

        BlogService service = ServiceProvider.getBlogService();
        User userSession = (User) session.getAttribute("loggedUser");

//        User user = service.logingUser(userSession.getUsername(), userSession.getPassword());

        if (userSession == null) {
            rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("message", "<font color=red>U bent nog niet ingelogd</font>");
            rd.include(request, response);
        } else if (onderwerp.isEmpty()||text.isEmpty()) {
            rd = request.getRequestDispatcher("/blogger/myaccount.jsp");
            request.setAttribute("message", "<font color=red>Vul alle velden in aub !</font>");
            rd.include(request, response);
        } else {
            service.addBlogPostForUser(onderwerp, text, userSession);
            rd = request.getRequestDispatcher("/blogger/myaccount.jsp");
            rd.forward(request, response);
        }
    }
}

