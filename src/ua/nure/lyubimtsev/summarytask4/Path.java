package ua.nure.lyubimtsev.SummaryTask4;

public class Path {

    // pages
    public static final String LOGIN_PAGE = "/login.jsp";
    public static final String ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

    //doctors
    public static final String DOCTORS_PAGE = "/WEB-INF/jsp/doctor/doctors.jsp";
    public static final String INSERT_DOCTOR_PAGE = "/WEB-INF/jsp/doctor/insert_doctor.jsp";
    public static final String UPDATE_DOCTOR_PAGE = "/WEB-INF/jsp/doctor/update_doctor.jsp";

    //patients
    public static final String PATIENTS_PAGE = "/WEB-INF/jsp/patient/patients.jsp";
    public static final String INSERT_PATIENT_PAGE = "/WEB-INF/jsp/patient/insert_patient.jsp";
    public static final String UPDATE_PATIENT_PAGE = "/WEB-INF/jsp/patient/update_patient.jsp";
    public static final String UNASSIGNED_PATIENTS_PAGE = "/WEB-INF/jsp/patient/unassigned_patients.jsp";


    public static final String MEDICAL_CARD_PAGE = "/WEB-INF/jsp/appointment/medical_card.jsp";
    public static final String INSERT_APPOINTMENT_PAGE = "/WEB-INF/jsp/appointment/insert_appointment.jsp";
    public static final String UPDATE_APPOINTMENT_PAGE = "/WEB-INF/jsp/appointment/update_appointment.jsp";

    // commands
    public static final String GET_DOCTORS_COMMAND = "/controller?command=doctors";
    public static final String GET_PATIENTS_COMMAND = "/controller?command=patients";
    public static final String GET_APPOINTMENTS_COMMAND = "/controller?command=appointments";

    public static final String PRG_COMMAND = "controller?command=PRG";

}
