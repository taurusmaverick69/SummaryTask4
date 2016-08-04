package ua.nure.lyubimtsev.summarytask4.entities;

public enum AppointmentType {

    PROCEDURE("procedure"), MEDICINE("medicine"), OPERATION("operation");

    private String name;

    AppointmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AppointmentType getByName(String name) {

        for (AppointmentType appointmentType : values())
            if (appointmentType.getName().equals(name))
                return appointmentType;

        return null;
    }
}
