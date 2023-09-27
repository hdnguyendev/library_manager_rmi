import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ManagerController {
    LibraryRemote libraryRemote;

    public ManagerController() throws MalformedURLException, NotBoundException, RemoteException {
        libraryRemote = (LibraryRemote) Naming.lookup("rmi://localhost:1234/api");
    }

    public Response login(String username, String pass) throws SQLException, RemoteException {
        Response response = libraryRemote.user_login(username, pass);
        if (response.getStatus() == 200) {
            JOptionPane.showMessageDialog(null, "Login successfully!!");
            return response;
        }
        JOptionPane.showMessageDialog(null, "Login failed!");
        return response;
    }
    public Response book_addBook(Book book) throws RemoteException,SQLException {
        Response response = libraryRemote.book_addBook(book);
        return response;
    }
}
