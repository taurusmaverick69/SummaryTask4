package ua.nure.lyubimtsev.summarytask4.servlets;

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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "NewEditDoctorServlet", urlPatterns = "/NewEditDoctorServlet")
public class NewEditDoctorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Doctor newDoctor = new Doctor(
                id,
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("category"));

        if (DAOFactory.getMySQLDAOFactory().getDoctorDAO().updateDoctor(newDoctor) == 1) {
            HttpSession session = request.getSession();
            List<Doctor> doctors = ((Admin) session.getAttribute("admin")).getDoctors();

            Doctor doctorById = doctors
                    .stream()
                    .filter(doctor -> doctor.getId() == id)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

            doctors.set(doctors.indexOf(doctorById), newDoctor);
            request.getRequestDispatcher("doctors").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
