package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;

import java.util.List;

public interface MedicalCardDAO {

    int insertMedicalCard(MedicalCard medicalCard);

    List<MedicalCard> getAllMedicalCards();

    MedicalCard getMedicalCardByPatientId(int patientId);

}
