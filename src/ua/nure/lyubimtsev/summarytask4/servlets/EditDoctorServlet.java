package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.dao.entitydao.DoctorDAO;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebServlet(name = "EditDoctorServlet", urlPatterns = "/edit")
public class EditDoctorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        long l = System.nanoTime();

        System.out.println(DAOFactory.getMySQLDAOFactory().getDoctorDAO().getDoctorsById(id));
        System.out.println("From DB: " + (System.nanoTime() - l));

        long k = System.nanoTime();
        HttpSession session = request.getSession();
        List<Doctor> doctors = ((Admin) session.getAttribute("admin")).getDoctors();

        Doctor myDoctor = doctors
                .stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        System.out.println(myDoctor);
        System.out.println("Filter collection: " + (System.nanoTime() - k));


        request.setAttribute("doctor", myDoctor);
        request.getRequestDispatcher("editDoctor.jsp").forward(request, response);


    }


}
