import javax.swing.table.DefaultTableModel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.Vector;

public class LibraryImpl extends UnicastRemoteObject implements LibraryRemote {
    InetAddress localhost = InetAddress.getLocalHost();
    String ipAddress = localhost.getHostAddress();
    Connection conn = new Connect().getConnect();
    Statement stm = conn.createStatement();
    PreparedStatement pst;
    ResultSet rst;
    ResultSetMetaData rstmeta;

    public LibraryImpl() throws SQLException, RemoteException, UnknownHostException {
        super();
    }


    public boolean user_login(String username, String pass) throws RemoteException, SQLException {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, pass);
        ResultSet rs = pstmt.executeQuery();
        boolean isValidUser = rs.next();

        if (isValidUser) {
            String updateQuery = "UPDATE user SET LastAccessedServer = ?, LastAccessedDateTime = ? WHERE username = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setString(1,ipAddress);
            updateStmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            updateStmt.setString(3, username);
            updateStmt.executeUpdate();
            updateStmt.close();
        }

        rs.close();
        pstmt.close();

        return isValidUser;
    }

    @Override
    public DefaultTableModel list_books() throws RemoteException, SQLException {
        Vector vTitle = new Vector();
        Vector vData = new Vector();
        try {
            rst = stm.executeQuery("SELECT Id, Title, Author, Available FROM Book");
            vTitle.clear();
            vData.clear();

            String[] title = new String[]{
                    "Book ID", "Title", "Author", "Available", "Delete"
            };
            for (int i = 0; i < title.length; i++) {
                vTitle.add(title[i]);
            }

            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("Id"));
                row.add(rst.getString("Title"));
                row.add(rst.getString("Author"));
                row.add(rst.getBoolean("Available"));
                row.add("DELETE");

                vData.add(row);
            }
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(vData, vTitle);
    }
}
