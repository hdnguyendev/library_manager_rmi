import javax.swing.table.DefaultTableModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;

public interface LibraryRemote extends Remote {

    // Manage
    // Return DefaultTableModel
    public Response getBooks() throws RemoteException;
    public Response getAuthors() throws RemoteException;
    public Response getBooksCopy() throws RemoteException;
    public Response getCategories() throws RemoteException;
    public Response getNotifications() throws RemoteException;
    public Response getPublished() throws RemoteException;
    public Response getHolds() throws RemoteException;
    public Response getCheckouts() throws RemoteException;
    //

    // CRUD Book
    public Response createBook(Book book,int author_id) throws RemoteException;
    public Response getBook(int id) throws RemoteException;
    public Response updateBook(Book book, int author_id) throws RemoteException;
    public Response deleteBook(int id) throws RemoteException;
    //
    // CRUD Author
    public Response createAuthor(Author author) throws RemoteException;
    public Response getAuthor(int id) throws RemoteException;
    public Response updateAuthor(Author author) throws RemoteException;
    public Response deleteAuthor(int id) throws RemoteException;
    //
    // CRUD Category
    public Response createCategory(Category category) throws RemoteException;
    public Response getCategory(int id) throws RemoteException;
    public Response updateCategory(Category category) throws RemoteException;
    public Response deleteCategory(int id) throws RemoteException;
    //
    // CRUD Published
    public Response createPublished(Published published) throws RemoteException;
    public Response getPublished(int id) throws RemoteException;
    public Response updatePublished(Published published) throws RemoteException;
    public Response deletePublished(int id) throws RemoteException;
    //
    // Log - Block
    public int createLog(Log log) throws RemoteException;
    public void updateLog(Log log) throws RemoteException;
    public void deleteLog(int id) throws RemoteException;
    public boolean checkLog(String table_name, int col_id) throws RemoteException;

}
