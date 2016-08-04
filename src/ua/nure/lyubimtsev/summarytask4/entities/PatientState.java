package ua.nure.lyubimtsev.summarytask4.entities;

public enum PatientState {

    HEALTHY("healthy"),
    ON_THE_LIST("on the list");

    private String name;

    PatientState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PatientState getByName(String name) {

        for (PatientState state : values())
            if (state.getName().equals(name))
                return state;

        return null;
    }
}
