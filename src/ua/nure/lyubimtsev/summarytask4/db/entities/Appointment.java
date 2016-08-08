package ua.nure.lyubimtsev.summarytask4.db.entities;

public class Appointment {

    private int id;
    private String diagnose;
    private AppointmentType type;
    private String info;
    private MedicalCard medicalCard;

    public Appointment() {
    }

    public Appointment(int id, String diagnose, AppointmentType type, String info, MedicalCard medicalCard) {
        this.id = id;
        this.diagnose = diagnose;
        this.type = type;
        this.info = info;
        this.medicalCard = medicalCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", diagnose='" + diagnose + '\'' +
                ", type=" + type +
                ", info='" + info + '\'' +
                ", medicalCard=" + medicalCard +
                '}';
    }
}


