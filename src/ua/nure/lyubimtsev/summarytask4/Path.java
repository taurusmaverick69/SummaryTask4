package ua.nure.lyubimtsev.SummaryTask4;

public class Path {

    private final static String SEND_REDIRECT = "sendRedirect";
    private final static String FORWARD = "forward";

    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_RESULT_PAGE = "/WEB-INF/jsp/result.jsp";

    public static final String PAGE_ADMIN_PAGE = "/WEB-INF/jsp/admin_page.jsp";

    public static final String PAGE_DOCTORS_PAGE = "/WEB-INF/jsp/doctors.jsp";
    public static final String PAGE_INSERT_DOCTOR_PAGE = "/WEB-INF/jsp/insert_doctor.jsp";
    public static final String PAGE_UPDATE_DOCTOR_PAGE = "/WEB-INF/jsp/update_doctor.jsp";

    public static final String PAGE_PATIENTS_PAGE = "/WEB-INF/jsp/patients.jsp";
    public static final String PAGE_INSERT_PATIENT_PAGE = "/WEB-INF/jsp/insert_patient.jsp";
    public static final String PAGE_UPDATE_PATIENT_PAGE = "/WEB-INF/jsp/update_patient.jsp";


    public static final String PAGE_LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
    public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";

    // commands
    public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";


}
