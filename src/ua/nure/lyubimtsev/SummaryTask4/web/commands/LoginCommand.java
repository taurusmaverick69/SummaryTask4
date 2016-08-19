package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Hash;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.PatientDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        session.setAttribute("states", factory.getStateDAO().getStates());
        session.setAttribute("categories", factory.getCategoryDAO().getCategories());

        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword(login, Hash.md5Custom(password));
        PatientDAO patientDAO = factory.getPatientDAO();
        DoctorDAO doctorDAO = factory.getDoctorDAO();
        if (admin == null) {

            Doctor doctor = doctorDAO.getDoctorByLoginAndPassword(login, Hash.md5Custom(password));
            if (doctor == null) {
                request.setAttribute("loginResult", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                doctor.setPatients(patientDAO.getPatientsByDoctorId(doctor.getId()));
                session.setAttribute("user", doctor);
                redirect.setURL(Path.GET_PATIENTS_COMMAND);
            }

        } else {
            admin.setDoctors(doctorDAO.getAllDoctors());
            for (Doctor doctor : admin.getDoctors()) {
                doctor.setPatients(patientDAO.getPatientsByDoctorId(doctor.getId()));
            }
            admin.setPatients(patientDAO.getAllPatients());
            session.setAttribute("user", admin);
            redirect.setURL(Path.GET_DOCTORS_COMMAND);
        }

        return redirect;
    }
}