package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.Person;
import ua.nure.lyubimtsev.summarytask4.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.entities.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
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
        String category = request.getParameter("category");
        HttpSession session = request.getSession();
        List<Doctor> doctors = ((Admin) session.getAttribute("admin")).getDoctors();

        if (category.equals("all")) {
            request.setAttribute("doctorsByCategory", doctors);
            request.getRequestDispatcher("doctors.jsp").forward(request, response);

        } else {
            List<Doctor> doctorsByCategory = doctors
                    .stream()
                    .filter(doctor -> doctor.getCategory().equals(category))
                    .collect(Collectors.toList());

            request.setAttribute("doctorsByCategory", doctorsByCategory);
            request.getRequestDispatcher("doctors.jsp").forward(request, response);
        }
    }
}
