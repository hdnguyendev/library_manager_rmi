import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class UserController {
    LibraryRemote libraryRemote;

    public UserController() throws MalformedURLException, NotBoundException, RemoteException {
        libraryRemote = (LibraryRemote) Naming.lookup("rmi://localhost:1234/api");
    }

    public boolean login(String username, String pass) throws SQLException, RemoteException {
        if (libraryRemote.user_login(username, pass)) {
            JOptionPane.showMessageDialog(null, "Login successfully!!");
            return true;
        }
        JOptionPane.showMessageDialog(null, "Login failed!");
        return false;
    }

}
