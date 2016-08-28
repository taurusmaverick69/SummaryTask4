package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

public interface MedicalCardDAO {

    int insertMedicalCard(MedicalCard medicalCard) throws DBException;

    MedicalCard getMedicalCardByPatientId(int patientId) throws DBException;

}
