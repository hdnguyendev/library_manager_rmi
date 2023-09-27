import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/*
 * Created by JFormDesigner on Wed Sep 27 09:29:46 ICT 2023
 */

/**
 * @author nguye
 */
public class BookDetailGUI extends JDialog {
    Book book = null;
    User user = null;
    LibraryController controller = new LibraryController();
    public BookDetailGUI(JFrame owner, boolean ok, Book book, User user) throws MalformedURLException, NotBoundException, RemoteException, SQLException {
        super(owner,true);
        if (book != null && user != null) {
            this.book = book;
            this.user = user;
        }
        initComponents();
        initData();


        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initData() {
        assert book != null;
        lbTitle.setText(lbTitle.getText() + book.getTitle());
        lbAuthor.setText(lbAuthor.getText() + book.getAuthor());
        lbDes.setText(lbDes.getText() + "This is a book");
        lbStatus.setText(lbStatus.getText() + (book.isAvailable() ? "Available" : "Unavailable"));
    }

    private void btnBorrow(ActionEvent e) throws SQLException, RemoteException {
        Response response = controller.userBorrowBook(user.getId(), book.getId());
        if (response.getStatus() == 100) {
            JOptionPane.showMessageDialog(this, response.getData(),"Notification!", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getData(),"Notification!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancel(ActionEvent e) {
        dispose();
    }
    private void initComponents() throws SQLException{
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Hồ Đăng Nguyện
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        lbTitle = new JLabel();
        lbAuthor = new JLabel();
        lbDes = new JLabel();
        lbStatus = new JLabel();
        panel2 = new JPanel();
        label13 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Book Detail");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
            javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax
            .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
            .awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
            .Color.red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.
            PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".
            equals(e.getPropertyName()))throw new RuntimeException();}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout(4, 2, 2, 2));

                    //---- lbTitle ----
                    lbTitle.setText("Title : ");
                    lbTitle.setFont(new Font("Montserrat", Font.PLAIN, 13));
                    lbTitle.setHorizontalAlignment(SwingConstants.LEFT);
                    lbTitle.setIcon(new ImageIcon(getClass().getResource("/images/tag.png")));
                    panel1.add(lbTitle);

                    //---- lbAuthor ----
                    lbAuthor.setText("Author : ");
                    lbAuthor.setFont(new Font("Montserrat", Font.PLAIN, 13));
                    lbAuthor.setHorizontalAlignment(SwingConstants.LEFT);
                    lbAuthor.setIcon(new ImageIcon(getClass().getResource("/images/writer.png")));
                    panel1.add(lbAuthor);

                    //---- lbDes ----
                    lbDes.setText("Description : ");
                    lbDes.setFont(new Font("Montserrat", Font.PLAIN, 13));
                    lbDes.setHorizontalAlignment(SwingConstants.LEFT);
                    lbDes.setIcon(new ImageIcon(getClass().getResource("/images/content-writing.png")));
                    panel1.add(lbDes);

                    //---- lbStatus ----
                    lbStatus.setText("Status : ");
                    lbStatus.setFont(new Font("Montserrat", Font.PLAIN, 13));
                    lbStatus.setHorizontalAlignment(SwingConstants.LEFT);
                    lbStatus.setIcon(new ImageIcon(getClass().getResource("/images/search.png")));
                    panel1.add(lbStatus);
                }
                contentPanel.add(panel1, BorderLayout.CENTER);

                //======== panel2 ========
                {
                    panel2.setLayout(new CardLayout());

                    //---- label13 ----
                    label13.setText("Book Information");
                    label13.setIcon(new ImageIcon(getClass().getResource("/images/stack-of-books_64.png")));
                    label13.setFont(new Font("Montserrat ExtraBold", Font.PLAIN, 30));
                    label13.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label13, "card1");
                }
                contentPanel.add(panel2, BorderLayout.PAGE_START);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new FlowLayout());

                //---- okButton ----
                okButton.setText("Borrow");
                okButton.setIcon(new ImageIcon(getClass().getResource("/images/checked.png")));
                okButton.addActionListener(e -> {try {
btnBorrow(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}});
                buttonBar.add(okButton);

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancel(e));
                buttonBar.add(cancelButton);
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(405, 305);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Hồ Đăng Nguyện
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel lbTitle;
    private JLabel lbAuthor;
    private JLabel lbDes;
    private JLabel lbStatus;
    private JPanel panel2;
    private JLabel label13;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
