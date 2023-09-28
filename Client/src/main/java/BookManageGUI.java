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
        showTableBorrowed();

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
    public void showTableBorrowed(){

        try {
            DefaultTableModel model = controller.listBorrowed();
            tableBorrow.setModel(model);

            tableBooks.setDefaultEditor(Object.class, null);
            scrollPane2.setViewportView(tableBorrow);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableBorrow.setRowHeight(25);
        TableColumn indexColumn = tableBorrow.getColumnModel().getColumn(0);
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

    private void btModify(ActionEvent e) throws SQLException, RemoteException {
        int selectRow = tableBooks.getSelectedRow();
        tableBooks.setModel(tableBooks.getModel());
        int id = Integer.parseInt(tableBooks.getModel().getValueAt(selectRow,0).toString());
        String titleBook = tfTitle.getText();
        String authorBook = tfAuthor.getText();
        String descBook = tfDes.getText();
        boolean status = cbStatus.getSelectedItem().toString().equals("Available");
        Book modifyBook = new Book(id,titleBook,authorBook,descBook,status);
        Response response = controller.book_updateBook(modifyBook);
        if (response.getStatus() == 200) {
            updateTableBooks();
            JOptionPane.showMessageDialog(this, response.getData(), "Notification", JOptionPane.INFORMATION_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(this, response.getData(), "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void tableBooksMouseClicked(MouseEvent e) {
        int selectedRow = tableBooks.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy thông tin từ hàng dữ liệu được chọn
            String title = (String) tableBooks.getValueAt(selectedRow, 1);
            String author = (String) tableBooks.getValueAt(selectedRow, 2);
            String desc = (String) tableBooks.getValueAt(selectedRow, 3);
            String available = (String) tableBooks.getValueAt(selectedRow, 4);
            tfTitle.setText(title);
            tfAuthor.setText(author);
            tfDes.setText(desc);
            cbStatus.setSelectedItem(available);
        }
    }

    private void btDelete(ActionEvent e) throws SQLException, RemoteException {
        int selectedRow = tableBooks.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy thông tin từ hàng dữ liệu được chọn
            int Id = (int) tableBooks.getValueAt(selectedRow, 0);
            Book deleteBook = new Book(Id);
            Response response = controller.book_deleteBook(deleteBook);
            if (response.getStatus() == 200) {
                updateTableBooks();
                JOptionPane.showMessageDialog(this, response.getData(), "Notification", JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(this, response.getData(), "Notification", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private void tableBooksMouseEntered(MouseEvent e) throws MalformedURLException, SQLException, NotBoundException, RemoteException {
       
    }

    private void logout(ActionEvent e) throws MalformedURLException, NotBoundException, RemoteException {
        dispose();
        new LoginGUI().setVisible(true);
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - hdnguyendev
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
        panel2 = new JPanel();
        button3 = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        label5 = new JLabel();
        label6 = new JLabel();
        button4 = new JButton();
        panel7 = new JPanel();
        scrollPane1 = new JScrollPane();
        tableBooks = new JTable();
        panel5 = new JPanel();
        panel1 = new JPanel();
        scrollPane2 = new JScrollPane();
        tableBorrow = new JTable();
        label7 = new JLabel();
        label8 = new JLabel();
        panel4 = new JPanel();
        panel8 = new JPanel();
        button5 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {

            //======== panel3 ========
            {
                panel3.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
                javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax
                . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
                .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,panel3. getBorder( )) ); panel3. addPropertyChangeListener (new java. beans.
                PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .
                equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                panel3.setLayout(null);

                //======== panel6 ========
                {
                    panel6.setLayout(null);

                    //---- label1 ----
                    label1.setText("Title");
                    label1.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    panel6.add(label1);
                    label1.setBounds(25, 95, 55, 25);

                    //---- tfTitle ----
                    tfTitle.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    panel6.add(tfTitle);
                    tfTitle.setBounds(115, 90, 310, 35);

                    //---- label2 ----
                    label2.setText("Author");
                    label2.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    panel6.add(label2);
                    label2.setBounds(25, 140, 55, 25);

                    //---- tfAuthor ----
                    tfAuthor.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    panel6.add(tfAuthor);
                    tfAuthor.setBounds(115, 140, 310, 35);

                    //---- label3 ----
                    label3.setText("Description ");
                    label3.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    panel6.add(label3);
                    label3.setBounds(25, 195, 90, 25);

                    //---- tfDes ----
                    tfDes.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    panel6.add(tfDes);
                    tfDes.setBounds(115, 195, 310, 85);

                    //---- label4 ----
                    label4.setText("Status");
                    label4.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    panel6.add(label4);
                    label4.setBounds(25, 300, 90, 25);

                    //---- cbStatus ----
                    cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Available",
                        "Unavailable"
                    }));
                    cbStatus.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    panel6.add(cbStatus);
                    cbStatus.setBounds(115, 300, 305, 40);

                    //======== panel2 ========
                    {
                        panel2.setLayout(new FlowLayout());

                        //---- button3 ----
                        button3.setText("Modify");
                        button3.setIcon(new ImageIcon(getClass().getResource("/images/edit.png")));
                        button3.setFont(new Font("Montserrat Black", Font.PLAIN, 12));
                        button3.addActionListener(e -> {try {
btModify(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}});
                        panel2.add(button3);

                        //---- button1 ----
                        button1.setText("Apply");
                        button1.setIcon(new ImageIcon(getClass().getResource("/images/checked.png")));
                        button1.setFont(new Font("Montserrat Black", Font.PLAIN, 12));
                        button1.addActionListener(e -> {try {
btApply(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}});
                        panel2.add(button1);

                        //---- button2 ----
                        button2.setText("Clear");
                        button2.setIcon(new ImageIcon(getClass().getResource("/images/cleaning.png")));
                        button2.setFont(new Font("Montserrat Black", Font.PLAIN, 12));
                        button2.addActionListener(e -> btClear(e));
                        panel2.add(button2);
                    }
                    panel6.add(panel2);
                    panel2.setBounds(new Rectangle(new Point(85, 375), panel2.getPreferredSize()));

                    //---- label5 ----
                    label5.setIcon(new ImageIcon(getClass().getResource("/images/online-library.png")));
                    panel6.add(label5);
                    label5.setBounds(new Rectangle(new Point(35, 15), label5.getPreferredSize()));

                    //---- label6 ----
                    label6.setText("eLibrary VKU");
                    label6.setFont(new Font("Montserrat Black", Font.PLAIN, 24));
                    panel6.add(label6);
                    label6.setBounds(135, 35, 245, label6.getPreferredSize().height);

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
                panel6.setBounds(5, 20, 470, 435);

                //---- button4 ----
                button4.setIcon(new ImageIcon(getClass().getResource("/images/bin.png")));
                button4.addActionListener(e -> {try {
btDelete(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}});
                panel3.add(button4);
                button4.setBounds(1035, 0, 55, 30);

                //======== panel7 ========
                {
                    panel7.setLayout(new BorderLayout());
                }
                panel3.add(panel7);
                panel7.setBounds(385, 15, panel7.getPreferredSize().width, 470);

                //======== scrollPane1 ========
                {

                    //---- tableBooks ----
                    tableBooks.setFont(tableBooks.getFont().deriveFont(tableBooks.getFont().getSize() + 2f));
                    tableBooks.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            tableBooksMouseClicked(e);
                        }
                    });
                    scrollPane1.setViewportView(tableBooks);
                }
                panel3.add(scrollPane1);
                scrollPane1.setBounds(435, 30, 620, 445);

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

                //======== panel1 ========
                {
                    panel1.setLayout(null);

                    //======== scrollPane2 ========
                    {

                        //---- tableBorrow ----
                        tableBorrow.setFont(tableBorrow.getFont().deriveFont(tableBorrow.getFont().getSize() + 2f));
                        scrollPane2.setViewportView(tableBorrow);
                    }
                    panel1.add(scrollPane2);
                    scrollPane2.setBounds(0, 5, 810, 440);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel1.getComponentCount(); i++) {
                            Rectangle bounds = panel1.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel1.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel1.setMinimumSize(preferredSize);
                        panel1.setPreferredSize(preferredSize);
                    }
                }
                panel5.add(panel1);
                panel1.setBounds(20, 15, 840, 445);

                //---- label7 ----
                label7.setIcon(new ImageIcon(getClass().getResource("/images/library.png")));
                panel5.add(label7);
                label7.setBounds(new Rectangle(new Point(945, 25), label7.getPreferredSize()));

                //---- label8 ----
                label8.setText("Management Borrow Book");
                label8.setFont(label8.getFont().deriveFont(label8.getFont().getStyle() | Font.BOLD, label8.getFont().getSize() + 5f));
                panel5.add(label8);
                label8.setBounds(860, 95, 265, 55);

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
            tabbedPane1.addTab("Management Borrow", panel5);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);

        //======== panel4 ========
        {
            panel4.setLayout(new BorderLayout());

            //======== panel8 ========
            {
                panel8.setLayout(new BorderLayout());

                //---- button5 ----
                button5.setText("Log out");
                button5.addActionListener(e -> {try {
logout(e);} catch (MalformedURLException ex) {
    throw new RuntimeException(ex);
} catch (NotBoundException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}});
                panel8.add(button5, BorderLayout.CENTER);
            }
            panel4.add(panel8, BorderLayout.EAST);
        }
        contentPane.add(panel4, BorderLayout.NORTH);
        setSize(1140, 565);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - hdnguyendev
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
    private JPanel panel2;
    private JButton button3;
    private JButton button1;
    private JButton button2;
    private JLabel label5;
    private JLabel label6;
    private JButton button4;
    private JPanel panel7;
    private JScrollPane scrollPane1;
    private JTable tableBooks;
    private JPanel panel5;
    private JPanel panel1;
    private JScrollPane scrollPane2;
    private JTable tableBorrow;
    private JLabel label7;
    private JLabel label8;
    private JPanel panel4;
    private JPanel panel8;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
