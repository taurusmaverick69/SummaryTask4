package ua.nure.lyubimtsev.summarytask4.entities;

import java.util.Date;

public class Patient {

    private int id;
    private String name;
    private String address;
    private Date birthDate;
    private PatientState state;

    public Patient() {
    }

    public Patient(int id, String name, String address, Date birthDate, PatientState state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PatientState getState() {
        return state;
    }

    public void setState(PatientState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", state='" + state + '\'' +
                '}';
    }
}
