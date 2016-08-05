package ua.nure.lyubimtsev.summarytask4.util;

import ua.nure.lyubimtsev.summarytask4.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.entities.Patient;

import java.util.Comparator;

public class Sorter {

    public static final Comparator<Patient> SORT_PATIENTS_ALPHABETICALLY = new Comparator<Patient>() {
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static final Comparator<Patient> SORT_PATIENTS_BY_BIRTH_DATE = new Comparator<Patient>() {
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getBirthDate().compareTo(o2.getBirthDate());
        }
    };

    public static final Comparator<Doctor> SORT_DOCTORS_ALPHABETICALLY = new Comparator<Doctor>() {
        @Override
        public int compare(Doctor o1, Doctor o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static final Comparator<Doctor> SORT_DOCTORS_BY_CATEGORY = new Comparator<Doctor>() {
        @Override
        public int compare(Doctor o1, Doctor o2) {
            return o1.getCategory().getName().compareTo(o2.getCategory().getName());
        }
    };

    public static final Comparator<Doctor> SORT_DOCTORS_BY_NUMBER_OF_PATIENTS = new Comparator<Doctor>() {
        @Override
        public int compare(Doctor o1, Doctor o2) {
            return o1.getPatients().size() - o2.getPatients().size();
        }
    };

}
