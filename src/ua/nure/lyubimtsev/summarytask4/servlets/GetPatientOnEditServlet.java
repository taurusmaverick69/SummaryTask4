package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.entities.Category;
import ua.nure.lyubimtsev.summarytask4.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.entities.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "GetPatientOnEditServlet", urlPatterns = "/getPatientOnEditServlet")
public class GetPatientOnEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        List<Doctor> doctors = ((Admin) session.getAttribute("admin")).getDoctors();

        Doctor doctorById = doctors
                .stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        List<Patient> patients = doctorById.getPatients();

        List<Category> categories = DAOFactory.getMySQLDAOFactory().getCategoryDAO().getCategories();

        session.setAttribute("doctor", doctorById);
        session.setAttribute("categories", categories);

        request.getRequestDispatcher("editDoctor.jsp").forward(request, response);

    }
}
