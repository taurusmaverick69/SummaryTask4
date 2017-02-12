package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Insert doctor.
 *
 * @author Vladislav
 */
public class InsertDoctorCommand extends Command {

    private static final Logger LOG = Logger.getLogger(InsertDoctorCommand.class);

    private static final String DOCTOR_INSERT_SUCCESS = "doctor.add.success";
    private static final String DOCTOR_INSERT_FAILED = "doctor.add.failed";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        int categoryId = Integer.parseInt(request.getParameter("category"));
        LOG.trace("categoryId --> " + categoryId);

        List<Category> categories = (List<Category>) session.getAttribute("categories");
        Category categoryById = categories
                .stream()
                .filter(category -> category.getId() == categoryId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));
        LOG.trace("categoryById --> " + categoryById);


        Admin admin = (Admin) session.getAttribute("user");
        LOG.trace("admin --> " + admin);

        Doctor doctor = new Doctor(
                request.getParameter("login"),
                DigestUtils.md5Hex(request.getParameter("password")),
                request.getParameter("name"),
                categoryById,
                admin.getId()
        );

        DoctorDAO doctorDAO = factory.getDoctorDAO();

        boolean success = doctorDAO.insertDoctor(doctor) > 0;
        if (success) {
            admin.getDoctors().add(doctor);
        }
        session.setAttribute("result", success ? DOCTOR_INSERT_SUCCESS : DOCTOR_INSERT_FAILED);

        LOG.debug("Commands finished");
        return new Redirect(Path.GET_DOCTORS_COMMAND, ForwardingType.SEND_REDIRECT);
    }
}