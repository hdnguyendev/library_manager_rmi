import javax.swing.table.DefaultTableModel;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
    Vector clientList;


    public LibraryImpl() throws SQLException, RemoteException, UnknownHostException {
        super();
        clientList = new Vector();
    }

    public synchronized void registerForCallback(
            ClientInterface callbackClientObject)
            throws java.rmi.RemoteException {
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.addElement(callbackClientObject);
            System.out.println("Registered new client ");
        } // end if
    }

    // This remote method allows an object client to
// cancel its registration for callback
// @param id is an ID for the client; to be used by
// the server to uniquely identify the registered client.
    public synchronized void unregisterForCallback(
            ClientInterface callbackClientObject)
            throws java.rmi.RemoteException {
        if (clientList.removeElement(callbackClientObject)) {
            System.out.println("Unregistered client ");
        } else {
            System.out.println(
                    "unregister: client wasn't registered.");
        }
    }

    private synchronized void doCallbacks(NOTIFY notify) throws java.rmi.RemoteException {
        // make callback to each registered client
        System.out.println(
                "**************************************\n"
                        + "Callbacks initiated ---");
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println("doing " + (i + 1) + "-th callback\n");
            // convert the vector object to a callback object
            ClientInterface nextClient =
                    (ClientInterface) clientList.elementAt(i);
            // invoke the callback method
            nextClient.notify(notify);
            System.out.println("SERVER send CLIENT: " + notify.toString());
        }// end for
        System.out.println("********************************\n" +
                "Server completed callbacks ---");
    } // doCallbacks

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
    public Response getPublished() throws RemoteException {
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT p.id, p.name " +
                    "FROM published p ";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Published ID", "Published Name"
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
                row.add(rst.getString("title"));
                row.add(rst.getInt("year_published"));
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
    public Response getHolds() throws RemoteException {
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT h.id, pa.email, CONCAT(pa.first_name, ' ', pa.last_name) as fullname, b.title , h.start_time, h.end_time " +
                    "FROM hold as h " +
                    "INNER JOIN patron_account as pa ON pa.id = h.patron_id " +
                    "INNER JOIN book_copy as bc ON bc.id = h.book_copy_id " +
                    "INNER JOIN book as b ON b.id = bc.book_id";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Hold ID", "Patron Email", "Patron Name", "Book Title", "Time start", "Time end"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getString("email"));
                row.add(rst.getString("fullname"));
                row.add(rst.getString("title"));
                row.add(rst.getTimestamp("start_time"));
                row.add(rst.getTimestamp("end_time"));
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
    public Response getCheckouts() throws RemoteException {
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT c.id,c.is_returned, pa.email, CONCAT(pa.first_name, ' ', pa.last_name) as fullname, b.title , c.start_time, c.end_time " +
                    "FROM checkout as c " +
                    "INNER JOIN patron_account as pa ON pa.id = c.patron_id " +
                    "INNER JOIN book_copy as bc ON bc.id = c.book_copy_id " +
                    "INNER JOIN book as b ON b.id = bc.book_id";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Checkout ID", "Is Returned", "Patron Email", "Patron Name", "Book Title", "Time start", "Time end"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getBoolean("is_returned") ? "Yes" : "No");
                row.add(rst.getString("email"));
                row.add(rst.getString("fullname"));
                row.add(rst.getString("title"));
                row.add(rst.getTimestamp("start_time"));
                row.add(rst.getTimestamp("end_time"));
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
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT n.id, n.sent_at, n.message, pa.email " +
                    "FROM notification as n " +
                    "INNER JOIN patron_account as pa ON pa.id = n.patron_id ";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Notification ID", "Sent At", "Message", "Patron Email"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getTimestamp("sent_at"));
                row.add(rst.getString("message"));
                row.add(rst.getString("email"));
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
    public Response getPatrons() throws RemoteException {
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT * " +
                    "FROM patron_account p ";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Patron ID", "First Name", "Last Name", "Email", "Status"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getInt("id"));
                row.add(rst.getString("first_name"));
                row.add(rst.getString("last_name"));
                row.add(rst.getString("email"));
                row.add(rst.getBoolean("status") ? "Available" : "Unavailable");
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
    public Response getHistory() throws RemoteException {
        try {
            vTitle.clear();
            vData.clear();

            String query = "SELECT * " +
                    "FROM db_log ORDER BY id DESC ";

            rst = stm.executeQuery(query);

            String[] title = new String[]{
                    "Table Name", "Action", "Time"
            };
            Collections.addAll(vTitle, title);
            while (rst.next()) {
                Vector row = new Vector();

                row.add(rst.getString("table_name"));
                row.add(rst.getString("action"));
                row.add(rst.getTime("timestamp"));
                vData.add(row);
            }
            rst.close();
            return new Response(200, new DefaultTableModel(vData, vTitle));
        } catch (SQLException e) {
            System.out.println("!!!---Error: " + e);
            return new Response(100, null);

        }
    }

    // CRUD - Book
    @Override
    public Response createBook(Book book, int author_id, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");
            }
            if (!isCallFromSever) {
                server2().createBook(book, author_id, true);
            }

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

            doCallbacks(NOTIFY.UPDATE_BOOK);

            return new Response(200, "Created new book successfully!");
        } catch (Exception e) {
            return new Response(100, e.getMessage());
        }
    }

    @Override
    public Response getBook(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateBook(Book book, int author_id, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().updateBook(book, author_id, true);
            }

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
            insertBookAuthorStatement.setInt(2, book.getId());
            insertBookAuthorStatement.executeUpdate();
            doCallbacks(NOTIFY.UPDATE_BOOK);
            return new Response(200, "Updated book successfully!");
        } catch (Exception e) {
            return new Response(100, e.getMessage());
        }
    }

    @Override
    public Response deleteBook(int id, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().deleteBook(id, true);
            }

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
                doCallbacks(NOTIFY.UPDATE_BOOK);
                return new Response(200, "Deleted Book Successfully!");

            } else {
                return new Response(100, "Not find book_id " + id);

            }


        } catch (SQLException e) {
            return new Response(100, e.getMessage());
        }
    }

    // CRUD Author
    @Override
    public Response createAuthor(Author author, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().createAuthor(author, true);
            }

            String insertAuthorQuery = "INSERT INTO author (name) VALUES (?)";
            PreparedStatement insertAuthorStatement = conn.prepareStatement(insertAuthorQuery, Statement.RETURN_GENERATED_KEYS);
            insertAuthorStatement.setString(1, author.getName());
            insertAuthorStatement.executeUpdate();
            doCallbacks(NOTIFY.UPDATE_AUTHOR);
            return new Response(200, "Created new author successfully!");
        } catch (Exception e) {
            return new Response(100, e.getMessage());
        }
    }

    @Override
    public Response getAuthor(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateAuthor(Author author, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().updateAuthor(author, true);
            }

            String updateAuthorQuery = "UPDATE author SET name = ? WHERE id = ?";
            PreparedStatement updateAuthorStatement = conn.prepareStatement(updateAuthorQuery);
            updateAuthorStatement.setString(1, author.getName());
            updateAuthorStatement.setInt(2, author.getId());
            updateAuthorStatement.executeUpdate();
            doCallbacks(NOTIFY.UPDATE_AUTHOR);
            return new Response(200, "Updated author successfully!");
        } catch (Exception e) {
            return new Response(100, e.getMessage());
        }
    }

    @Override
    public Response deleteAuthor(int id, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().deleteAuthor(id, true);
            }

            String deleteAuthorQuery = "DELETE FROM author WHERE id = ?";
            PreparedStatement deleteAuthorStatement = conn.prepareStatement(deleteAuthorQuery);
            deleteAuthorStatement.setInt(1, id);
            int rowsAffected = deleteAuthorStatement.executeUpdate();

            if (rowsAffected > 0) {
                doCallbacks(NOTIFY.UPDATE_AUTHOR);
                return new Response(200, "Deleted author Successfully!");

            } else {
                return new Response(100, "Not find author_id " + id);

            }


        } catch (SQLException e) {
            return new Response(100, e.getMessage());
        }
    }

    // CRUD Category
    @Override
    public Response createCategory(Category category, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().createCategory(category, true);
            }

            String createCategoryQuery = "INSERT INTO category(name) VALUE (?)";
            PreparedStatement createCategoryStatement = conn.prepareStatement(createCategoryQuery);
            createCategoryStatement.setString(1, category.getName());
            createCategoryStatement.executeUpdate();
            doCallbacks(NOTIFY.UPDATE_CATEGORY);
            return new Response(200, "Created new Category successfully!");
        } catch (Exception e) {
            return new Response(100, e.getMessage());
        }
    }

    @Override
    public Response getCategory(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateCategory(Category category, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().updateCategory(category, true);
            }

            String updateCategoryQuery = "UPDATE category SET name = ? WHERE id = ?";
            PreparedStatement updateCategoryStatement = conn.prepareStatement(updateCategoryQuery);
            updateCategoryStatement.setString(1, category.getName());
            updateCategoryStatement.setInt(2, category.getId());
            updateCategoryStatement.executeUpdate();
            doCallbacks(NOTIFY.UPDATE_CATEGORY);
            return new Response(200, "Updated Category successfully!");
        } catch (Exception e) {
            return new Response(100, e.getMessage());
        }
    }

    @Override
    public Response deleteCategory(int id, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().deleteCategory(id, true);
            }

            String deleteCategoryQuery = "DELETE FROM category WHERE id = ?";
            PreparedStatement deleteCategoryStatement = conn.prepareStatement(deleteCategoryQuery);
            deleteCategoryStatement.setInt(1, id);
            int rowsAffected = deleteCategoryStatement.executeUpdate();

            if (rowsAffected > 0) {
                doCallbacks(NOTIFY.UPDATE_CATEGORY);
                return new Response(200, "Deleted category Successfully!");

            } else {
                return new Response(100, "Not find author_id " + id);

            }


        } catch (SQLException e) {
            return new Response(100, e.getMessage());
        }
    }

    // CRUD Published
    @Override
    public Response createPublished(Published published, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response getPublished(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updatePublished(Published published, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response deletePublished(int id, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    // CRUD Patron
    @Override
    public Response createPatron(Patron patron, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response getPatron(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updatePatron(Patron patron, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response deletePatron(int id, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    // CRUD BookCopy
    @Override
    public Response createBookCopy(BookCopy bookCopy, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response getBookCopy(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateBookCopy(BookCopy bookCopy, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response deleteBookCopy(int id, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    // CRUD Hold
    @Override
    public Response createHold(Hold hold, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response getHold(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateHold(Hold hold, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response deleteHold(int id, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    // CRUD Checkout
    @Override
    public Response createCheckout(Checkout checkout, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response getCheckout(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateCheckout(Checkout checkout, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response deleteCheckout(int id, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    // CRUD Notification
    @Override
    public Response createNotification(Notification notification, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response getNotification(int id) throws RemoteException {
        return null;
    }

    @Override
    public Response updateNotification(Notification notification, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    @Override
    public Response deleteNotification(int id, boolean isCallFromSever) throws RemoteException {
        return null;
    }

    // CRUD - Log
    @Override
    public int createLog(Log log, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().createLog(log, true);
            }

            String createLogQuery = "INSERT INTO log (ip, username, table_name, col_id, time_start) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement createLogStatement = conn.prepareStatement(createLogQuery, Statement.RETURN_GENERATED_KEYS);
            createLogStatement.setString(1, log.getIp());
            createLogStatement.setString(2, log.getUsername());
            createLogStatement.setString(3, log.getTable_name());
            createLogStatement.setInt(4, log.getCol_id());
            createLogStatement.setTimestamp(5, log.getTime_start());
            createLogStatement.executeUpdate();

            ResultSet generatedKeys = createLogStatement.getGeneratedKeys();
            int log_id;
            if (generatedKeys.next()) {
                log_id = generatedKeys.getInt(1);
                System.out.println(log_id);
                return log_id;
            }
            return -1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateLog(Log log, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().updateLog(log, true);
            }

            String updateLogQuery = "UPDATE log SET ip = ? , username = ?, table_name = ? , col_id = ?, time_start = ?  WHERE id = ?";
            PreparedStatement updateLogStatement = conn.prepareStatement(updateLogQuery);
            updateLogStatement.setString(1, log.getIp());
            updateLogStatement.setString(2, log.getUsername());
            updateLogStatement.setString(3, log.getTable_name());
            updateLogStatement.setInt(4, log.getCol_id());
            updateLogStatement.setTimestamp(5, log.getTime_start());
            updateLogStatement.setInt(6, log.getId());
            updateLogStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteLog(int id, boolean isCallFromSever) throws RemoteException {
        try {
            if (server2().equals(null)) {
                System.out.println("SERVER2 ISN'T RUNNING !!!");

            }
            if (!isCallFromSever) {
                server2().deleteLog(id, true);
            }

            // Xóa các bản sao sách từ bảng book_copy
            String deleteLogQuery = "DELETE FROM log WHERE id = ?";
            PreparedStatement deleteLogStatement = conn.prepareStatement(deleteLogQuery);
            deleteLogStatement.setInt(1, id);
            deleteLogStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean checkLog(String table_name, int col_id) throws RemoteException {
        try {
            String checkLogQuery = "SELECT COUNT(*) FROM log WHERE table_name = ? AND col_id = ?";
            PreparedStatement checkLogStatement = conn.prepareStatement(checkLogQuery);
            checkLogStatement.setString(1, table_name);
            checkLogStatement.setInt(2, col_id);
            ResultSet resultSet = checkLogStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // synchronized server - server
    public LibraryRemote server2() throws RemoteException {
        try {
            if (Config.IP_SERVER_2.equals("localhost")) {
                System.out.println("SERVER: server2 don't running");
                return null;
            }
            return (LibraryRemote) Naming.lookup("rmi://" + Config.IP_SERVER_2 + ":" + Config.PORT_SERVER_2 + "/api");
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
