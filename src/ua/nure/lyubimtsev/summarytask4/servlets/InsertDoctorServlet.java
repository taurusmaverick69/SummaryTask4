package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.entities.Category;
import ua.nure.lyubimtsev.summarytask4.entities.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "InsertDoctorServlet", urlPatterns = "/insertDoctorServlet")
public class InsertDoctorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Category> categories = (List<Category>) session.getAttribute("categories");

        String categoryStr = request.getParameter("category");

        Category myCategory = categories
                .stream()
                .filter(category -> category.getName().equals(categoryStr))
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        Admin admin = (Admin) session.getAttribute("user");
        // Admin admin = (Admin) session.getAttribute("userId");


        Doctor doctor = new Doctor(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("name"),
                myCategory,
                admin
        );

        boolean success = DAOFactory.getMySQLDAOFactory().getDoctorDAO().insertDoctor(doctor) > 0;

        if (success) {
            admin.getDoctors().add(doctor);
        }

        response.sendRedirect("displayInsertDoctorServlet?success=" + success);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
