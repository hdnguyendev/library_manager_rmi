import java.io.Serializable;

public class Author implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    public Author(Object[] data) {
        this.id = (int) data[0];
        this.name = (String) data[1];
    }

    public Author(int id, String name) {
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

    @Override
    public String toString() {
        return this.name;
    }
}