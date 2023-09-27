import javax.swing.table.DefaultTableModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface LibraryRemote extends Remote {
    public boolean user_login(String username, String pass) throws RemoteException, SQLException;
    public DefaultTableModel list_books() throws RemoteException, SQLException;
}
