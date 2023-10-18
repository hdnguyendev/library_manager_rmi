import javax.swing.table.DefaultTableModel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.Collections;
import java.util.Vector;

public class LibraryImpl extends UnicastRemoteObject implements LibraryRemote {
    InetAddress localhost = InetAddress.getLocalHost();
    String ipAddress = localhost.getHostAddress();
    Connection conn = new Connect().getConnect();
    Statement stm = conn.createStatement();
    PreparedStatement pst;
    ResultSet rst;
    ResultSetMetaData rstmeta;
    Vector vTitle = new Vector();
    Vector vData = new Vector();

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
    public Response user_logout(String username) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public Response user_borrowBook(int user_id, int book_id) throws RemoteException, SQLException {
        try {
            // Kết nối tới cơ sở dữ liệu

            // Kiểm tra xem sách có sẵn để mượn không
            String checkAvailabilityQuery = "SELECT isAvailable FROM Book WHERE id = ?";

            pst = conn.prepareStatement(checkAvailabilityQuery);
            pst.setInt(1, book_id);
            rst = pst.executeQuery();

            if (rst.next()) {
                boolean available = rst.getBoolean("isAvailable");

                if (available) {
                    // Cập nhật trạng thái sách đã được mượn
                    String borrowBookQuery = "UPDATE Book SET isAvailable = FALSE WHERE id = ?";
                    pst = conn.prepareStatement(borrowBookQuery);
                    pst.setInt(1, book_id);
                    int rowsUpdated = pst.executeUpdate();

                    if (rowsUpdated > 0) {
                        // Thêm thông tin mượn sách vào bảng BorrowedBook
                        String borrowInfoQuery = "INSERT INTO BorrowedBook (userId, bookId, borrowDate) VALUES (?, ?, CURDATE())";
                        pst = conn.prepareStatement(borrowInfoQuery);
                        pst.setInt(1, user_id);
                        pst.setInt(2, book_id);
                        int rowsInserted = pst.executeUpdate();

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
    public Response book_addBook(Book book) throws RemoteException {
//        try {
//            String addBook_query = "INSERT INTO book (id, title, author, description, isAvailable) VALUES (?, ?, ?, ?, ?)";
//            pst = conn.prepareStatement(addBook_query);
//            pst.setInt(1, book.getId());
//            pst.setString(2, book.getTitle());
//            pst.setString(3, book.getAuthor());
//            pst.setString(4, book.getDescription());
//            pst.setBoolean(5, book.isAvailable());
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return new Response(200, "Insert data successfully!");

    }

    @Override
    public Response book_updateBook(Book book) throws RemoteException {
//        try {
//            String updateQuery = "UPDATE book SET title = ?, author = ?, description = ?, isAvailable= ? WHERE id = ?";
//            pst = conn.prepareStatement(updateQuery);
//            pst.setString(1, book.getTitle());
//            pst.setString(2, book.getAuthor());
//            pst.setString(3, book.getDescription());
//            pst.setBoolean(4, book.isAvailable());
//            pst.setInt(5, book.getId());
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return new Response(200, "Update data successfully!");
    }

    @Override
    public Response book_deleteBook(Book book) throws RemoteException {
        try {
            String deleteQuery = "DELETE FROM book WHERE id = ?";
            pst = conn.prepareStatement(deleteQuery);
            pst.setInt(1, book.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Response(200, "Delete data successfully!");
    }

    @Override
    public DefaultTableModel listBorrowed() throws RemoteException {
        Vector vTitle = new Vector();
        Vector vData = new Vector();
        try {
            String listBorrowed = "SELECT b.id, u.username, bk.title, b.borrowDate, b.returnDate " +
                    "FROM borrowedbook b " +
                    "INNER JOIN user u ON b.userId = u.id " +
                    "INNER JOIN book bk ON b.bookId = bk.id";
            rst = stm.executeQuery(listBorrowed);
            vTitle.clear();
            vData.clear();
            String[] title = new String[]{
                    "ID", "Username", "Book Title", "Borrowed Date", "Return Date"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getString("username"));
                row.add(rst.getString("title"));
                row.add(rst.getString("borrowDate"));
                row.add(rst.getString("returnDate"));
                vData.add(row);
            }
            rst.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new DefaultTableModel(vData, vTitle);

    }


    @Override
    public DefaultTableModel list_books() throws RemoteException, SQLException {
        Vector vTitle = new Vector();
        Vector vData = new Vector();
        try {
            rst = stm.executeQuery("SELECT id, title, author, description, isAvailable FROM Book");
            vTitle.clear();
            vData.clear();

            String[] title = new String[]{
                    "Book ID", "Title", "Author", "Description", "Status"
            };
            Collections.addAll(vTitle, title);

            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getString("title"));
                row.add(rst.getString("author"));
                row.add(rst.getString("description"));
                row.add(rst.getBoolean("isAvailable") ? "Available" : "Unavailable");
                vData.add(row);
            }
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(vData, vTitle);
    }

    // Manage
    @Override
    public Response getBooks() throws RemoteException {

        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT book.id, book.title, category.name AS category, author.name AS author " +
                    "FROM book " +
                    "INNER JOIN book_author ON book.id = book_author.book_id " +
                    "INNER JOIN author ON book_author.author_id = author.id " +
                    "INNER JOIN category ON book.category_id = category.id order by book.id";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Book ID", "Book Title", "Category", "Author"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getString("title"));
                row.add(rst.getString("category"));
                row.add(rst.getString("author"));
                vData.add(row);
            }
            rst.close();
            return new Response(200, new DefaultTableModel(vData, vTitle));
        } catch (SQLException e) {
            System.out.println("!!!---Error: " + e);
            return new Response(100, null);

        }
    }

    @Override
    public Response getAuthors() throws RemoteException {
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT a.id, a.name " +
                    "FROM author a ";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Author ID", "Author Name"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getString("name"));
                vData.add(row);
            }
            rst.close();
            return new Response(200, new DefaultTableModel(vData, vTitle));
        } catch (SQLException e) {
            System.out.println("!!!---Error: " + e);
            return new Response(100, null);

        }
    }

    @Override
    public Response getBooksCopy() throws RemoteException {
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT bc.id, b.title, bc.year_published, p.name " +
                    "FROM book_copy bc " +
                    "INNER JOIN book b ON bc.book_id = b.id " +
                    "INNER JOIN published p ON bc.published_id = p.id ";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Book Copy ID", "Book Title", "Year Public", "Published Name"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getString("name"));
                vData.add(row);
            }
            rst.close();
            return new Response(200, new DefaultTableModel(vData, vTitle));
        } catch (SQLException e) {
            System.out.println("!!!---Error: " + e);
            return new Response(100, e.toString());

        }
    }

    @Override
    public Response getCategories() throws RemoteException {
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT c.id, c.name " +
                    "FROM category c ";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Category ID", "Category Name"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();
                row.add(rst.getInt("id"));
                row.add(rst.getString("name"));
                vData.add(row);
            }
            rst.close();
            return new Response(200, new DefaultTableModel(vData, vTitle));
        } catch (SQLException e) {
            System.out.println("!!!---Error: " + e);
            return new Response(100, null);

        }
    }

    @Override
    public Response getNotifications() throws RemoteException {
        return null;
    }

    @Override
    public Response getPublished() throws RemoteException {
        return null;
    }

    @Override
    public Response getHolds() throws RemoteException {
        return null;
    }

    @Override
    public Response getCheckouts() throws RemoteException {
        return null;
    }

    // CRUD - Book
    @Override
    public Response createBook(Book book, int author_id) throws RemoteException {
        try {
            // Thêm sách mới vào bảng book
            String insertBookQuery = "INSERT INTO book (title, category_id) VALUES (?, ?)";
            PreparedStatement insertBookStatement = conn.prepareStatement(insertBookQuery, Statement.RETURN_GENERATED_KEYS);
            insertBookStatement.setString(1, book.getTitle());
            insertBookStatement.setInt(2, book.getCategory_id());
            insertBookStatement.executeUpdate();

            // Lấy giá trị id của sách vừa được thêm vào
            ResultSet generatedKeys = insertBookStatement.getGeneratedKeys();
            int bookId;
            if (generatedKeys.next()) {
                bookId = generatedKeys.getInt(1);
            } else {
                return new Response(100, "Failed to get the generated book ID.");
            }

            // Thêm thông tin tác giả vào bảng book_author
            String insertBookAuthorQuery = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";
            PreparedStatement insertBookAuthorStatement = conn.prepareStatement(insertBookAuthorQuery);
            insertBookAuthorStatement.setInt(1, bookId);
            insertBookAuthorStatement.setInt(2, author_id);
            insertBookAuthorStatement.executeUpdate();

            return new Response(200, "Created book successfully!");
        }
        catch (Exception e){
            return new Response(100, e.getMessage());
        }
    }

    @Override
    public Response getBook(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateBook(Book book,int author_id) throws RemoteException {
        try {
            // Cập nhật sách theo id
            String insertBookQuery = "UPDATE book SET title = ?, category_id = ? WHERE id = ?";
            PreparedStatement insertBookStatement = conn.prepareStatement(insertBookQuery, Statement.RETURN_GENERATED_KEYS);
            insertBookStatement.setString(1, book.getTitle());
            insertBookStatement.setInt(2, book.getCategory_id());
            insertBookStatement.setInt(3, book.getId());
            insertBookStatement.executeUpdate();


            String insertBookAuthorQuery = "UPDATE book_author SET author_id = ? WHERE book_id = ?";
            PreparedStatement insertBookAuthorStatement = conn.prepareStatement(insertBookAuthorQuery);
            insertBookAuthorStatement.setInt(1, author_id);
            insertBookAuthorStatement.setInt(2, book.getId() );
            insertBookAuthorStatement.executeUpdate();

            return new Response(200, "Updated book successfully!");
        }
        catch (Exception e){
            return new Response(100, e.getMessage());
        }
    }

    @Override
    public Response deleteBook(int id) throws RemoteException {
        try {
            // Xóa các bản sao sách từ bảng book_copy
            String deleteBookCopyQuery = "DELETE FROM book_copy WHERE book_id = ?";
            PreparedStatement deleteBookCopyStatement = conn.prepareStatement(deleteBookCopyQuery);
            deleteBookCopyStatement.setInt(1, id);
            deleteBookCopyStatement.executeUpdate();
            // Xóa dữ liệu tương ứng trong bảng book_author
            String deleteBookAuthorQuery = "DELETE FROM book_author WHERE book_id = ?";
            PreparedStatement deleteBookAuthorStatement = conn.prepareStatement(deleteBookAuthorQuery);
            deleteBookAuthorStatement.setInt(1, id);
            deleteBookAuthorStatement.executeUpdate();

            // Xóa sách từ bảng book
            String deleteBookQuery = "DELETE FROM book WHERE id = ?";
            PreparedStatement deleteBookStatement = conn.prepareStatement(deleteBookQuery);
            deleteBookStatement.setInt(1, id);
            int rowsAffected = deleteBookStatement.executeUpdate();

            if (rowsAffected > 0) {
                return new Response(200, "Deleted Book Successfully!" );

            } else {
                return new Response(100, "Not find book_id " + id);

            }


        } catch (SQLException e) {
            return new Response(100, e.getMessage());
        }
    }

    // CRUD Category
    @Override
    public Response createCategory(Category category) throws RemoteException {
        return null;
    }

    @Override
    public Response getCategory(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateCategory(Category category) throws RemoteException {
        return null;
    }

    @Override
    public Response deleteCategory(int id) throws RemoteException {
        return null;
    }
}
