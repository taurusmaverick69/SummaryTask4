package ua.nure.lyubimtsev.SummaryTask4;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "Demo", urlPatterns = "/demo")
public class Demo extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Role> roles = DAOFactory.getMySQLDAOFactory().getRoleDAO().getRoles();


        for (Role role : roles) {
            System.out.println(role);
        }

    }

    @Override
    public void init() throws ServletException {
        System.out.println("Demo.init");
    }
}
