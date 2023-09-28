import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ManagerController {
    LibraryRemote libraryRemote;

    public ManagerController() throws MalformedURLException, NotBoundException, RemoteException {
        libraryRemote = (LibraryRemote) Naming.lookup("rmi://" + LoginGUI.IP_SERVER +":1234/api");
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
    public Response book_updateBook(Book book)throws RemoteException,SQLException{
        Response response = libraryRemote.book_updateBook(book);
        return response;
    }
    public DefaultTableModel getDataTableBooks() throws SQLException, RemoteException {
        DefaultTableModel defaultTableModel = libraryRemote.list_books();
        return defaultTableModel;
    }
    public Response book_deleteBook(Book book)throws RemoteException,SQLException {
        Response response = libraryRemote.book_deleteBook(book);
        return response;
    }
    public DefaultTableModel listBorrowed()throws RemoteException,SQLException{
        DefaultTableModel defaultTableModel = libraryRemote.listBorrowed();
        return defaultTableModel;
    }
}
