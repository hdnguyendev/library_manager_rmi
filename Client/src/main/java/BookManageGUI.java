import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
/*
 * Created by JFormDesigner on Wed Sep 27 15:27:53 GMT+07:00 2023
 */


/**
 * @author ADMIN
 */
public class BookManageGUI extends JFrame {
    ManagerController controller = new ManagerController();

    public BookManageGUI() throws MalformedURLException, NotBoundException, RemoteException {
        initComponents();
        updateTableBooks();


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void updateTableBooks() {

        try {
            DefaultTableModel model = controller.getDataTableBooks();
            tableBooks.setModel(model);

            tableBooks.setDefaultEditor(Object.class, null);
            scrollPane1.setViewportView(tableBooks);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableBooks.setRowHeight(25);
        TableColumn indexColumn = tableBooks.getColumnModel().getColumn(0);
        indexColumn.setPreferredWidth(20); // Đặt độ rộng cố định

    }

    private void btApply(ActionEvent e) throws SQLException, RemoteException {
        String title = tfTitle.getText();
        String author = tfAuthor.getText();
        String desc = tfDes.getText();
        boolean status = cbStatus.getSelectedItem().toString().equals("Available");
        Book saveBook = new Book(title, author, desc, status);
        Response response = controller.book_addBook(saveBook);
        if (response.getStatus() == 200) {
            updateTableBooks();
            JOptionPane.showMessageDialog(this, response.getData(), "Notification", JOptionPane.INFORMATION_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(this, response.getData(), "Notification", JOptionPane.INFORMATION_MESSAGE);
        }


        System.out.println(response.getData());
    }

    private void btClear(ActionEvent e) {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfDes.setText("");
    }

    private void btModify(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - CassanoQuan
        tabbedPane1 = new JTabbedPane();
        panel3 = new JPanel();
        panel6 = new JPanel();
        label1 = new JLabel();
        tfTitle = new JTextField();
        label2 = new JLabel();
        tfAuthor = new JTextField();
        label3 = new JLabel();
        tfDes = new JTextField();
        label4 = new JLabel();
        cbStatus = new JComboBox<>();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        panel7 = new JPanel();
        scrollPane1 = new JScrollPane();
        tableBooks = new JTable();
        panel5 = new JPanel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {

            //======== panel3 ========
            {
                panel3.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
                        . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder
                        . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
                        awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel3. getBorder () ) )
                ; panel3. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
                panel3.setLayout(null);

                //======== panel6 ========
                {
                    panel6.setLayout(null);

                    //---- label1 ----
                    label1.setText("Title");
                    panel6.add(label1);
                    label1.setBounds(25, 25, 55, 25);
                    panel6.add(tfTitle);
                    tfTitle.setBounds(115, 20, 210, 35);

                    //---- label2 ----
                    label2.setText("Author");
                    panel6.add(label2);
                    label2.setBounds(25, 70, 55, 25);
                    panel6.add(tfAuthor);
                    tfAuthor.setBounds(115, 70, 210, 30);

                    //---- label3 ----
                    label3.setText("Description ");
                    panel6.add(label3);
                    label3.setBounds(25, 125, 90, 25);
                    panel6.add(tfDes);
                    tfDes.setBounds(115, 125, 210, 30);

                    //---- label4 ----
                    label4.setText("Status");
                    panel6.add(label4);
                    label4.setBounds(25, 175, 90, 25);

                    //---- cbStatus ----
                    cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {
                            "Available",
                            "Unavailable"
                    }));
                    panel6.add(cbStatus);
                    cbStatus.setBounds(115, 175, 210, cbStatus.getPreferredSize().height);

                    //---- button1 ----
                    button1.setText("Apply");
                    button1.addActionListener(e -> {try {
                        btApply(e);} catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }});
                    panel6.add(button1);
                    button1.setBounds(new Rectangle(new Point(225, 235), button1.getPreferredSize()));

                    //---- button2 ----
                    button2.setText("Clear");
                    button2.addActionListener(e -> btClear(e));
                    panel6.add(button2);
                    button2.setBounds(new Rectangle(new Point(130, 235), button2.getPreferredSize()));

                    //---- button3 ----
                    button3.setText("Modify");
                    button3.addActionListener(e -> btModify(e));
                    panel6.add(button3);
                    button3.setBounds(new Rectangle(new Point(25, 235), button3.getPreferredSize()));

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel6.getComponentCount(); i++) {
                            Rectangle bounds = panel6.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel6.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel6.setMinimumSize(preferredSize);
                        panel6.setPreferredSize(preferredSize);
                    }
                }
                panel3.add(panel6);
                panel6.setBounds(20, 20, 350, 435);

                //======== panel7 ========
                {
                    panel7.setLayout(null);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(tableBooks);
                    }
                    panel7.add(scrollPane1);
                    scrollPane1.setBounds(0, 0, 580, 435);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel7.getComponentCount(); i++) {
                            Rectangle bounds = panel7.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel7.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel7.setMinimumSize(preferredSize);
                        panel7.setPreferredSize(preferredSize);
                    }
                }
                panel3.add(panel7);
                panel7.setBounds(new Rectangle(new Point(385, 15), panel7.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel3.getComponentCount(); i++) {
                        Rectangle bounds = panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel3.setMinimumSize(preferredSize);
                    panel3.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("Management Book", panel3);

            //======== panel5 ========
            {
                panel5.setLayout(null);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel5.getComponentCount(); i++) {
                        Rectangle bounds = panel5.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel5.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel5.setMinimumSize(preferredSize);
                    panel5.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("text", panel5);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(5, 5, 970, 500);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - CassanoQuan
    private JTabbedPane tabbedPane1;
    private JPanel panel3;
    private JPanel panel6;
    private JLabel label1;
    private JTextField tfTitle;
    private JLabel label2;
    private JTextField tfAuthor;
    private JLabel label3;
    private JTextField tfDes;
    private JLabel label4;
    private JComboBox<String> cbStatus;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel panel7;
    private JScrollPane scrollPane1;
    private JTable tableBooks;
    private JPanel panel5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
