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
        lbTitle.setText(book.getTitle());
        lbAuthor.setText(book.getAuthor());
        lbDes.setText("CC");
        lbStatus.setText(book.isAvailable() ? "✅Available" : "‼️Unavailable");
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
        label1 = new JLabel();
        lbTitle = new JLabel();
        label7 = new JLabel();
        lbAuthor = new JLabel();
        label4 = new JLabel();
        lbDes = new JLabel();
        label6 = new JLabel();
        lbStatus = new JLabel();
        panel2 = new JPanel();
        label13 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
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
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout(4, 2, 2, 2));

                    //---- label1 ----
                    label1.setText("Title");
                    panel1.add(label1);
                    panel1.add(lbTitle);

                    //---- label7 ----
                    label7.setText("Author");
                    panel1.add(label7);
                    panel1.add(lbAuthor);

                    //---- label4 ----
                    label4.setText("Description");
                    panel1.add(label4);
                    panel1.add(lbDes);

                    //---- label6 ----
                    label6.setText("Status");
                    panel1.add(label6);
                    panel1.add(lbStatus);
                }
                contentPanel.add(panel1, BorderLayout.WEST);

                //======== panel2 ========
                {
                    panel2.setLayout(new CardLayout());

                    //---- label13 ----
                    label13.setText("Book Information");
                    panel2.add(label13, "card1");
                }
                contentPanel.add(panel2, BorderLayout.NORTH);

                //======== scrollPane1 ========
                {

                    //---- textArea1 ----
                    textArea1.setText("Xin chao toi la nguyen thanh cam dau buoi re rach");
                    textArea1.setPreferredSize(new Dimension(365, 27));
                    scrollPane1.setViewportView(textArea1);
                }
                contentPanel.add(scrollPane1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Borrow");
                okButton.addActionListener(e1 -> {try {
btnBorrow(e1);} catch (SQLException e) {
    throw new RuntimeException(e);
} catch (RemoteException e) {
    throw new RuntimeException(e);
}});
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancel(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(875, 510);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Hồ Đăng Nguyện
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JLabel lbTitle;
    private JLabel label7;
    private JLabel lbAuthor;
    private JLabel label4;
    private JLabel lbDes;
    private JLabel label6;
    private JLabel lbStatus;
    private JPanel panel2;
    private JLabel label13;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
