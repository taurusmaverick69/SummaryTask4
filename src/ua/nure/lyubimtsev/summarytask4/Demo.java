package ua.nure.lyubimtsev.summarytask4;

import ua.nure.lyubimtsev.summarytask4.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.util.MD5Hash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Demo", urlPatterns = "/demo")
public class Demo extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Admin admin = DAOFactory.getMySQLDAOFactory().getAdminDAO().getAdminByLoginAndPassword("admin", MD5Hash.md5Custom("admin"));


        System.out.println("admin = " + admin);
        System.out.println(admin.getDoctors());
        System.out.println(admin.getPatients());

    }
}
