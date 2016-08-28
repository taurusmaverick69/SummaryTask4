package ua.nure.lyubimtsev.SummaryTask4.db.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Admin entity.
 *
 * @author V.Lyubimtsev
 */
public class Admin extends Entity {

    private String login;
    private String password;
    private String name;
    private List<Doctor> doctors;

    public Admin(int id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.doctors = new ArrayList<>();
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

    public Doctor getDoctorById(int id) {
        return doctors
                .stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));
    }

    public List<Doctor> getDoctorsByCategory(Category category) {
        return doctors
                .stream()
                .filter(doctor -> doctor.getCategory().getId() == category.getId())
                .collect(Collectors.toList());
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
