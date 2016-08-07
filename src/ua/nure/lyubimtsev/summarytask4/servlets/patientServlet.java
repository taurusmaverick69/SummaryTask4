package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.entities.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "PatientServlet", urlPatterns = "/patients")
public class PatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Doctor> doctors = ((Admin) session.getAttribute("user")).getDoctors();
        List<Patient> patients = new ArrayList<>();

        int id = Integer.parseInt(request.getParameter("id"));

        if (id == 0) {
            for (Doctor doctor : doctors) {
                patients.addAll(doctor.getPatients());
            }
        } else {
            Doctor myDoctor = doctors
                    .stream()
                    .filter(doctor -> doctor.getId() == id)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

            patients = myDoctor.getPatients();
        }

        session.setAttribute("patients", patients);
        request.getRequestDispatcher("patients.jsp").forward(request, response);
    }
}
