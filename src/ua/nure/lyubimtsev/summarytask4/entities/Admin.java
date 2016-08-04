package ua.nure.lyubimtsev.summarytask4.entities;

import java.util.ArrayList;
import java.util.List;

public class Admin {

    private int id;
    private String login;
    private String password;
    private String name;
    private List<Doctor> doctors;

    public Admin() {
    }

    public Admin(int id, String login, String password, String name) {
        doctors = new ArrayList<>();
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
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

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", doctors=" + doctors +
                '}';
    }
}
