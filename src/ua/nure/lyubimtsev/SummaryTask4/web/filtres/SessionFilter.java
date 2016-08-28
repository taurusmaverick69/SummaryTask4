package ua.nure.lyubimtsev.SummaryTask4.web.filtres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "avoid-urls", value = "/login.jsp,/test2.jsp"))
public class SessionFilter implements Filter {

    private final static String MATERIALIZECSS = "/materializecss";
    private final static String LOGIN_COMMAND = "login";
    private ArrayList<String> urlList;

    public void init(FilterConfig config) throws ServletException {

        String urls = config.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<>();
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getServletPath();

        if (url.startsWith(MATERIALIZECSS) || urlList.contains(url) || LOGIN_COMMAND.equals(req.getParameter("command"))) {
            chain.doFilter(req, res);
        } else {
            response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
            Object userName = request.getSession().getAttribute("user");
            if (null == userName) {
                request.setAttribute("endSession", "Session has ended. Please, sign in.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            chain.doFilter(req, res);
        }
    }

    public void destroy() {
    }
}