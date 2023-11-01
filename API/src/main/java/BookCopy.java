import java.io.Serializable;

public class BookCopy implements Serializable {
    private static final long serialVersionUID =  8271963769266110398L;
    private int id;
    private int year_published;
    private int book_id;
    private int published_id;

    public BookCopy() {
    }

    public BookCopy(int id, int year_published, int book_id, int published_id) {
        this.id = id;
        this.year_published = year_published;
        this.book_id = book_id;
        this.published_id = published_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPublished_id() {
        return published_id;
    }

    public void setPublished_id(int published_id) {
        this.published_id = published_id;
    }
}
