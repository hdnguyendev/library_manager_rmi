import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class LibraryController {
    LibraryRemote libraryRemote;

    public LibraryController() throws MalformedURLException, NotBoundException, RemoteException {
        libraryRemote = (LibraryRemote) Naming.lookup("rmi://localhost:1234/api");
    }

    public DefaultTableModel getDataTableBooks() throws SQLException, RemoteException {
        DefaultTableModel defaultTableModel = libraryRemote.list_books();
        return defaultTableModel;
    }

}
