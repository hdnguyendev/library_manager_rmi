import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String author;
    private String description;
    private boolean isAvailable;

    public Book() {
    }

    public Book(int id) {
        this.id = id;
    }

    public Book(String title, String author, String description, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public Book(int id, String title, String author, String description, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public Book(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
