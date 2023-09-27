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


    public Response user_login(String username, String pass) throws RemoteException, SQLException {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, pass);
        rst = pst.executeQuery();
        boolean isValidUser = rst.next();
        User user = null;

        if (isValidUser) {
            int id = rst.getInt("id");
            String fullName = rst.getString("fullname");
            String lastAccessedServer = rst.getString("lastAccessedServer");
            Time lastAccessedDateTime = rst.getTime("lastAccessedDateTime");
            user = new User(id, username, pass, fullName, lastAccessedServer, lastAccessedDateTime);

            String updateQuery = "UPDATE user SET lastAccessedServer = ?, lastAccessedDateTime = ? WHERE username = ?";
            pst = conn.prepareStatement(updateQuery);
            pst.setString(1, ipAddress);
            pst.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            pst.setString(3, username);
            pst.executeUpdate();
            pst.close();
            return new Response(200, user);
        }

        return new Response(100, null);
    }

    @Override
    public Response user_borrowBook(int user_id, int book_id) throws RemoteException, SQLException {
        PreparedStatement stmt = null;
        try {
            // Kết nối tới cơ sở dữ liệu

            // Kiểm tra xem sách có sẵn để mượn không
            String checkAvailabilityQuery = "SELECT isAvailable FROM Book WHERE id = ?";

            stmt = conn.prepareStatement(checkAvailabilityQuery);
            stmt.setInt(1, book_id);
            rst = stmt.executeQuery();

            if (rst.next()) {
                boolean available = rst.getBoolean("isAvailable");

                if (available) {
                    // Cập nhật trạng thái sách đã được mượn
                    String borrowBookQuery = "UPDATE Book SET isAvailable = FALSE WHERE id = ?";
                    stmt = conn.prepareStatement(borrowBookQuery);
                    stmt.setInt(1, book_id);
                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        // Thêm thông tin mượn sách vào bảng BorrowedBook
                        String borrowInfoQuery = "INSERT INTO BorrowedBook (userId, bookId, borrowDate) VALUES (?, ?, CURDATE())";
                        stmt = conn.prepareStatement(borrowInfoQuery);
                        stmt.setInt(1, user_id);
                        stmt.setInt(2, book_id);
                        int rowsInserted = stmt.executeUpdate();

                        if (rowsInserted > 0) {
                            return new Response(200, "Book borrowed successfully.");
                        } else {
                            return new Response(100, "Failed to insert borrowing information.");
                        }
                    } else {
                        return new Response(100, "Failed to update book availability.");
                    }
                } else {
                    return new Response(100, "The book is not available for borrowing.");
                }
            } else {
                return new Response(100, "Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(100, "An error occurred while borrowing the book.");
    }

}

    @Override
    public DefaultTableModel list_books() throws RemoteException, SQLException {
        Vector vTitle = new Vector();
        Vector vData = new Vector();
        try {
            rst = stm.executeQuery("SELECT id, title, author, isAvailable FROM Book");
            vTitle.clear();
            vData.clear();

            String[] title = new String[]{
                    "Book ID", "Title", "Author", "Status"
            };
            for (int i = 0; i < title.length; i++) {
                vTitle.add(title[i]);
            }

            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getString("title"));
                row.add(rst.getString("author"));
                row.add(rst.getBoolean("isAvailable") ? "Available" : "Unavailable");
                vData.add(row);
            }
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(vData, vTitle);
    }
}
