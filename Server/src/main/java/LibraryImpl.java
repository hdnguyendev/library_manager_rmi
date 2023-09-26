import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class LibraryImpl extends UnicastRemoteObject implements LibraryRemote {
    Connection conn = new Connect().getConnect();
    Statement stm = conn.createStatement();
    PreparedStatement pst;
    ResultSet rst;
    ResultSetMetaData rstmeta;

    public LibraryImpl() throws SQLException, RemoteException {
        super();
    }


    public boolean user_login(String username, String pass) throws RemoteException, SQLException {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, pass);
        ResultSet rs = pstmt.executeQuery();
        boolean isValidUser = rs.next();
        rs.close();
        pstmt.close();
        return isValidUser;
    }
}
