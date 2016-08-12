package ua.nure.lyubimtsev.SummaryTask4.web.command;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Hash;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        Redirect redirect = new Redirect();
        redirect.setForwardingType(ForwardingType.FORWARD);

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword(login, Hash.md5Custom(password));

        session.setAttribute("states", factory.getStateDAO().getStates());

        if (admin == null) {

            Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword(login, Hash.md5Custom(password));

            if (doctor == null) {
                request.setAttribute("loginResult", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                doctor.setPatients(factory.getPatientDAO().getPatientsByDoctor(doctor));
                session.setAttribute("doctor", doctor);
                session.setAttribute("patients", doctor.getPatients());
                redirect.setURL(Path.PAGE_PATIENTS_PAGE);
            }
        } else {

            List<Doctor> doctors = factory.getDoctorDAO().getDoctors();
            for (Doctor doctor : doctors){
                doctor.setPatients(factory.getPatientDAO().getPatientsByDoctor(doctor));
            }

            admin.setDoctors(doctors);
            session.setAttribute("admin", admin);
            redirect.setURL(Path.PAGE_ADMIN_PAGE);
        }


        return redirect;
    }
}
