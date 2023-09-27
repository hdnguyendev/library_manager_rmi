import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

public class RMIServer {
    public static void main(String[] args) {
        try {
            LibraryImpl server = new LibraryImpl();
            LocateRegistry.createRegistry(1234);

            // Đăng ký đối tượng này với rmiregistry
            Naming.bind("rmi://localhost:1234/api", server);

            System.out.println(">>>>>INFO: RMI Server started!!!!!!!!");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}
