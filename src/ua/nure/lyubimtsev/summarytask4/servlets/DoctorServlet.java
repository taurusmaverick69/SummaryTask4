package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.entities.Admin;
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
        String category = request.getParameter("category");
        HttpSession session = request.getSession();

        Object user = session.getAttribute("admin");


        System.out.println(user.getClass());

        System.out.println(user instanceof Patient);



        System.out.println("DoctorServlet.processRequest");


        List<Doctor> doctors = ((Admin) session.getAttribute("admin")).getDoctors();

        switch (category) {
            case "all":
                request.setAttribute("doctorsByCategory", doctors);
                request.getRequestDispatcher("doctors.jsp").forward(request, response);
                break;
            case "patients":
                List<Patient> patients = new ArrayList<>();
                for (Doctor doctor : doctors) {
                    patients.addAll(doctor.getPatients());
                }

                request.setAttribute("patients", patients);
                request.getRequestDispatcher("patients.jsp").forward(request, response);
                break;
            default:
                List<Doctor> doctorsByCategory = doctors
                        .stream()
                        .filter(doctor -> doctor.getCategory().getName().equals(category))
                        .collect(Collectors.toList());

                request.setAttribute("doctorsByCategory", doctorsByCategory);
                request.getRequestDispatcher("doctors.jsp").forward(request, response);
                break;
        }
    }
}
