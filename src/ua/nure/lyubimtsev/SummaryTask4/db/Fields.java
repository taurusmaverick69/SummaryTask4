package ua.nure.lyubimtsev.SummaryTask4.db;


/**
 * Holder for fields names of DB tables.
 *
 * @author V.Lyubimtsev
 */
public class Fields {

    public static final String ENTITY_ID = "id";
    public static final String NAME = "name";

    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";

    public static final String ADMIN_ID_FK = "admin_id";
    public static final String CATEGORY_ID_FK = "category_id";

    public static final String REGISTRATION_DATE = "registrationDate";



    public static final String TYPE_ID_FK = "type_id";
    public static final String MEDICAL_CARD_ID_FK = "medicalCard_id";
    public static final String DOCTOR_ID_FK = "doctor_id";
    public static final String DIAGNOSE = "diagnose";
    public static final String INFO = "info";
    public static final String DATE = "date";

    public static final String STATE_ID_FK = "state_id";
    public static final String ADDRESS = "address";
    public static final String BIRTH_DATE = "birthDate";

    public static final String PATIENT_ID_FK = "patient_id";


    public static final String DOCTOR_ID = "doctor.id";
    public static final String DOCTOR_LOGIN = "doctor.login";
    public static final String DOCTOR_PASSWORD = "doctor.password";
    public static final String DOCTOR_NAME = "doctor.name";
    public static final String CATEGORY_ID = "category.id";
    public static final String CATEGORY_NAME = "category.name";

}
