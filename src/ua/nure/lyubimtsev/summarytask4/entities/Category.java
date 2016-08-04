package ua.nure.lyubimtsev.summarytask4.entities;

import java.io.Serializable;

public enum Category {

    PEDIATRICIAN("pediatrician"),
    TRAUMATOLOGIST("traumatologist"),
    SURGEON("surgeon"),
    NURSE("nurse");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category getByName(String name) {

        for (Category category : values())
            if (category.getName().equals(name))
                return category;

        return null;
    }



}
