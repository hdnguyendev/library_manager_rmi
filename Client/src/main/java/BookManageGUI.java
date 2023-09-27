import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Sep 27 15:27:53 GMT+07:00 2023
 */



/**
 * @author ADMIN
 */
public class BookManageGUI extends JFrame {
    ManagerController managerController = new ManagerController();

    public BookManageGUI() throws MalformedURLException, NotBoundException, RemoteException {
        initComponents();




        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void btApply(ActionEvent e) throws SQLException, RemoteException {
        String title = tfTitle.getText();
        String author =  tfAuthor.getText();
        String desc =  tfDes.getText();
        boolean status = cbStatus.getSelectedItem().toString().equals("Available");
        Book saveBook = new Book(title, author, desc, status);
        Response response = managerController.book_addBook(saveBook);
        if (response.getStatus() == 200) {

        }
        System.out.println(response.getData());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - CassanoQuan
        panel1 = new JPanel();
        label2 = new JLabel();
        tfTitle = new JTextField();
        label3 = new JLabel();
        tfAuthor = new JTextField();
        label4 = new JLabel();
        tfDes = new JTextField();
        label5 = new JLabel();
        cbStatus = new JComboBox<>();
        hSpacer1 = new JPanel(null);
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder ( 0
            , 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
            , new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,
            panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(new GridLayout(5, 2));

            //---- label2 ----
            label2.setText("Title");
            panel1.add(label2);
            panel1.add(tfTitle);

            //---- label3 ----
            label3.setText("Author");
            panel1.add(label3);
            panel1.add(tfAuthor);

            //---- label4 ----
            label4.setText("Description");
            panel1.add(label4);
            panel1.add(tfDes);

            //---- label5 ----
            label5.setText("Status");
            panel1.add(label5);

            //---- cbStatus ----
            cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {
                "Available",
                "Unavailable"
            }));
            panel1.add(cbStatus);
            panel1.add(hSpacer1);

            //---- button1 ----
            button1.setText("Apply");
            button1.addActionListener(e -> {try {
btApply(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}});
            panel1.add(button1);
        }
        contentPane.add(panel1, BorderLayout.WEST);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - CassanoQuan
    private JPanel panel1;
    private JLabel label2;
    private JTextField tfTitle;
    private JLabel label3;
    private JTextField tfAuthor;
    private JLabel label4;
    private JTextField tfDes;
    private JLabel label5;
    private JComboBox<String> cbStatus;
    private JPanel hSpacer1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
