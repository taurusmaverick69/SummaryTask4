package ua.nure.lyubimtsev.SummaryTask4;

public enum Role {

    ADMIN("Admin"), DOCTOR("Doctor"), PATIENT("Patient");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
