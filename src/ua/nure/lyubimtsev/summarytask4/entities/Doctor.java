package ua.nure.lyubimtsev.summarytask4.entities;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private int id;
    private String login;
    private String password;
    private String name;
    private String category;
    private List<Patient> patients;

    public Doctor() {
    }

    public Doctor(int id, String login, String password, String name, String category) {
        patients = new ArrayList<>();
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.category = category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
