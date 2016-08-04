package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.util.MD5Hash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword(login, MD5Hash.md5Custom(password));

        if (admin == null) {
            Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword(login, MD5Hash.md5Custom(password));

            if (doctor == null) {
                request.setAttribute("loginResult", "Invalid username or password");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("main.jsp").forward(request, response);
            }

        } else {
            session.setAttribute("admin", admin);
            request.getRequestDispatcher("adminPage.jsp").forward(request, response);
        }
    }

}
