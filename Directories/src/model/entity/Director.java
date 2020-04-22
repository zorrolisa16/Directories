package model.entity;

/**
 * @author Polina Astashko
 */
public class Director {

    private int id;
    private String name;

    public Director(int id, String name) {
        setId(id);
        setName(name);
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
        return String.format("name: %s", name);
    }

}
