package ua.nure.lyubimtsev.summarytask4.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisplayInsertDoctorServlet", urlPatterns = "/displayInsertDoctorServlet")
public class DisplayInsertDoctorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean success = Boolean.parseBoolean("success");

        request.setAttribute("result", success ? "Employee Successfully Inserted" : "Employee Not Inserted");

        request.getRequestDispatcher("doctors.jsp").forward(request, response);
    }
}
