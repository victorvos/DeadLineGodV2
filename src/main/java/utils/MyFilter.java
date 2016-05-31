package utils;
import java.io.IOException; import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Eigenaar on 22-5-2016.
 */
public class MyFilter implements Filter {
    public void init(FilterConfig arg0) throws ServletException {
 /* Filter is being placed into service, do nothing. */
    }
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest r2 = (HttpServletRequest)req;
        if (r2.getSession().getAttribute("loggedUser") == null) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        } else {
            chain.doFilter(req, resp);
        }
    }
    public void destroy() {
 /* Filter is being taken out of service, do nothing. */
    }
}

