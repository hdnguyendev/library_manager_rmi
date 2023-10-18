import javax.swing.table.DefaultTableModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface LibraryRemote extends Remote {
    public Response user_login(String username, String pass) throws RemoteException, SQLException;
    public Response user_logout(String username) throws RemoteException, SQLException;
    public Response user_borrowBook(int user_id, int book_id) throws RemoteException, SQLException;
    public Response book_addBook(Book book) throws RemoteException,SQLException;
    public Response book_updateBook(Book book)throws RemoteException,SQLException;
    public Response book_deleteBook(Book book)throws RemoteException,SQLException;
    public DefaultTableModel listBorrowed()throws RemoteException,SQLException;
    public DefaultTableModel list_books() throws RemoteException, SQLException;


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
    // CRUD Category
    public Response createCategory(Category category) throws RemoteException;
    public Response getCategory(int id) throws RemoteException;
    public Response updateCategory(Category category) throws RemoteException;
    public Response deleteCategory(int id) throws RemoteException;
    //


}
