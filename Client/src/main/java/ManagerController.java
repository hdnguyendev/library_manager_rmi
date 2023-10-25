import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ManagerController {
    LibraryRemote libraryRemote;

    public ManagerController() {
        try {
            libraryRemote = (LibraryRemote) Naming.lookup("rmi://" + Config.IP_SERVER + ":" + Config.PORT_SERVER + "/api");
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Error to Connect with Server", "Error", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Error to Connect with Server", "Error", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Error to Connect with Server", "Error", JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    // CRUD - Book
    public Response getBooksController() throws RemoteException {
        Response response = libraryRemote.getBooks();
        return response;

    }

    public Response createBookController(Book book, int author_id) throws RemoteException, SQLException {
        Response response = libraryRemote.createBook(book, author_id);
        return response;
    }

    public Response deleteBookController(int book_id) throws RemoteException {
        Response res = libraryRemote.deleteBook(book_id);
        return res;
    }

    public Response updateBookController(Book book, int authorId) throws RemoteException {
        Response response = libraryRemote.updateBook(book, authorId);
        return response;
    }

    //
    public int createLog(Log log) throws RemoteException {
        return libraryRemote.createLog(log);
    }

    public void updateLog(Log log) throws RemoteException {
        libraryRemote.updateLog(log);
    }

    public void deleteLog(int id) throws RemoteException {
        libraryRemote.deleteLog(id);
    }

    public boolean checkLog(String table_name, int col_id) throws RemoteException {
        return libraryRemote.checkLog(table_name, col_id);
    }

    //
    private static List<Category> getTableDataCategory(DefaultTableModel model) {
        List<Category> dataList = new ArrayList<>();

        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            Object[] rowData = new Object[columnCount];
            for (int column = 0; column < columnCount; column++) {
                rowData[column] = model.getValueAt(row, column);
            }
            Category category = new Category(rowData);
            dataList.add(category);
        }

        return dataList;
    }

    private static List<Author> getTableDataAuthor(DefaultTableModel model) {
        List<Author> dataList = new ArrayList<>();

        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            Object[] rowData = new Object[columnCount];
            for (int column = 0; column < columnCount; column++) {
                rowData[column] = model.getValueAt(row, column);
            }
            Author author = new Author(rowData);
            dataList.add(author);
        }

        return dataList;
    }

    public Response getDataComboBoxCategories() throws RemoteException {
        Response response = libraryRemote.getCategories();
        if (response.getStatus() == 100) {
            return response;
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) response.getData();
        List<Category> categoryList = getTableDataCategory(defaultTableModel);

        return new Response(200, categoryList);

    }

    public Response getDataComboBoxAuthors() throws RemoteException {
        Response response = libraryRemote.getAuthors();
        if (response.getStatus() == 100) {
            return response;
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) response.getData();
        List<Author> authorsList = getTableDataAuthor(defaultTableModel);

        return new Response(200, authorsList);

    }


}
