import java.io.Serializable;
import java.sql.Time;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String lastAccessedServer;
    private Time lastAccessedDateTime;

    public User() {
    }

    public User(int id, String username, String password, String fullName, String lastAccessedServer, Time lastAccessedDateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.lastAccessedServer = lastAccessedServer;
        this.lastAccessedDateTime = lastAccessedDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastAccessedServer() {
        return lastAccessedServer;
    }

    public void setLastAccessedServer(String lastAccessedServer) {
        this.lastAccessedServer = lastAccessedServer;
    }

    public Time getLastAccessedDateTime() {
        return lastAccessedDateTime;
    }

    public void setLastAccessedDateTime(Time lastAccessedDateTime) {
        this.lastAccessedDateTime = lastAccessedDateTime;
    }
}
