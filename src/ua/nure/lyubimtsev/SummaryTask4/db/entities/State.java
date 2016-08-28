package ua.nure.lyubimtsev.SummaryTask4.db.entities;


/**
 * State entity.
 *
 * @author V.Lyubimtsev
 */
public class State  extends Entity {

    private String name;

    public State(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "PatientState{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
