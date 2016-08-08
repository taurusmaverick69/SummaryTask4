package ua.nure.lyubimtsev.summarytask4.db.entities;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private int id;
    private String login;
    private String password;
    private String name;
    private Category category;
    private Admin admin;
    private List<Patient> patients;

    public Doctor() {
    }

    public Doctor(int id, String login, String password, String name, Category category) {
        patients = new ArrayList<>();
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.category = category;
    }

    public Doctor(String login, String password, String name, Category category, Admin admin) {
        patients = new ArrayList<>();
        this.login = login;
        this.password = password;
        this.name = name;
        this.category = category;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public static Builder newBuilder() {
        return new Doctor().new Builder();
    }

    public class Builder {

        public Builder setId(int id) {
            Doctor.this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            Doctor.this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            Doctor.this.password = password;
            return this;
        }

        public Builder setName(String name) {
            Doctor.this.admin = admin;
            return this;
        }

        public Builder setCategory(Category category) {
            Doctor.this.category = category;
            return this;
        }

        public Builder setAdmin(Admin admin) {
            Doctor.this.admin = admin;
            return this;
        }

        public Doctor build() {
            return Doctor.this;
        }

    }


}
