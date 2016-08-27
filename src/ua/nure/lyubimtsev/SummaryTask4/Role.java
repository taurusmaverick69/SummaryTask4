package ua.nure.lyubimtsev.SummaryTask4;

public enum Role {

    DOCTOR("Doctor"), ADMIN("Admin");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
