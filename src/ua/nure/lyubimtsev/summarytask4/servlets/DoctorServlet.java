package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.entities.Category;
import ua.nure.lyubimtsev.summarytask4.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.entities.Patient;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "DoctorServlet", urlPatterns = "/doctors")
public class DoctorServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String category = request.getParameter("category");
        List<Doctor> doctors = ((Admin) session.getAttribute("user")).getDoctors();

        if (category.equals("all")){
            session.setAttribute("doctorsByCategory", doctors);
        } else {
            List<Doctor> doctorsByCategory = doctors
                    .stream()
                    .filter(doctor -> doctor.getCategory().getName().equals(category))
                    .collect(Collectors.toList());
            session.setAttribute("doctorsByCategory", doctorsByCategory);
        }

        request.setAttribute("category", category);


        List<Category> categories = DAOFactory.getMySQLDAOFactory().getCategoryDAO().getCategories();
        session.setAttribute("categories", categories);
        request.getRequestDispatcher("doctors.jsp").forward(request, response);
    }
}
