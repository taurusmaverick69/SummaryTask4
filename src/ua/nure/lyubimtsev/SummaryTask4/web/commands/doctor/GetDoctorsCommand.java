package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.PatientDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class GetDoctorsCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("user");

        List<Doctor> allDoctors = factory.getDoctorDAO().getAllDoctors();
        PatientDAO patientDAO = factory.getPatientDAO();
        admin.setDoctors(allDoctors);
        for (Doctor doctor : admin.getDoctors()) {
            doctor.setPatients(patientDAO.getPatientsByDoctorId(doctor.getId()));
        }

        List<Category> categories = factory.getCategoryDAO().getCategories();
        session.setAttribute("categories", categories);
        for (Category category : categories) {
            session.setAttribute(category.getName(), admin.getDoctorsByCategory(category));
        }

        Category all = new Category(0, "all");
        if (!categories.contains(all)) {
            categories.add(0, all);
        }

        session.setAttribute("all", admin.getDoctors());
        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
