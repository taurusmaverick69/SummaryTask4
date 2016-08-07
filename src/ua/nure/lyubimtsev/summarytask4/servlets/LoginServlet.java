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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword(login, md5Custom(password));

        if (admin == null) {

            Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword(login, md5Custom(password));
            if (doctor == null) {
                request.setAttribute("loginResult", "Invalid username or password");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                session.setAttribute("patients", doctor.getPatients());
                session.setAttribute("user", doctor);
                request.getRequestDispatcher("patients.jsp").forward(request, response);
            }

        } else {
            session.setAttribute("user", admin);
            session.setAttribute("userId", admin.getId());
            request.getRequestDispatcher("adminPage.jsp").forward(request, response);
        }
    }

}
