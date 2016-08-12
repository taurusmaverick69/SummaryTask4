package ua.nure.lyubimtsev.SummaryTask4.db.entities;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private int id;
    private String login;
    private String password;
    private String name;
    private Category category;
    private int admin_id;
    private List<Patient> patients;

    public Doctor() {
    }

    public Doctor(int id, String login, String password, String name, Category category) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.category = category;
        this.patients = new ArrayList<>();
    }

    public Doctor(String login, String password, String name, Category category, int admin_id) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.category = category;
        this.admin_id = admin_id;
        this.patients = new ArrayList<>();
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", patients=" + patients +
                '}';
    }




}
