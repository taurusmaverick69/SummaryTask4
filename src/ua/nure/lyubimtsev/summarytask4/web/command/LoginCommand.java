package ua.nure.lyubimtsev.summarytask4.web.command;

import ua.nure.lyubimtsev.summarytask4.ForwardingType;
import ua.nure.lyubimtsev.summarytask4.Path;
import ua.nure.lyubimtsev.summarytask4.Redirect;
import ua.nure.lyubimtsev.summarytask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.db.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.db.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.exception.AppException;
import ua.nure.lyubimtsev.summarytask4.web.command.Command;

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
public class LoginCommand extends Command {

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



    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        Redirect redirect = new Redirect();
        redirect.setForwardingType(ForwardingType.FORWARD);

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword(login, md5Custom(password));

        if (admin == null) {

            Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword(login, md5Custom(password));
            if (doctor == null) {
                request.setAttribute("loginResult", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                session.setAttribute("patients", doctor.getPatients());
                session.setAttribute("user", doctor);
                redirect.setURL(Path.PAGE_PATIENTS_PAGE);
            }

        } else {
            session.setAttribute("user", admin);
            session.setAttribute("userId", admin.getId());
            redirect.setURL(Path.PAGE_ADMIN_PAGE);
        }

        return redirect;
    }
}
