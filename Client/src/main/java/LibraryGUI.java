/*
 * Created by JFormDesigner on Tue Sep 26 21:07:09 ICT 2023
 */

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
import javax.swing.table.*;

/**
 * @author nguye
 */
public class LibraryGUI extends JFrame {
    User user;
    TableRowSorter sorter;
    LibraryController controller = new LibraryController();

    public LibraryGUI(User user) throws MalformedURLException, NotBoundException, RemoteException {
        this.user = user;
        if (user == null) {
            System.out.println("User has error data");
        }
        initComponents();
        updateTableBooks();


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class CenteredTableCellRenderer extends DefaultTableCellRenderer {
        public CenteredTableCellRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }
    }
    class CustomHeaderRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setBackground(Color.BLUE); // Đặt màu nền là màu xanh
            label.setForeground(Color.WHITE); // Đặt màu chữ là màu trắng
            label.setFont(label.getFont().deriveFont(Font.BOLD)); // Đặt chữ in đậm
            label.setHorizontalAlignment(JLabel.CENTER); // Căn giữa tiêu đề
            return label;
        }
    }
    class IconCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof String) {
                String check = (String) value;
                if (check.equals("Available")){
                    setIcon(new ImageIcon("/images/checked.png")); // Đường dẫn đến biểu tượng
                    setText("Available");
                } else{
                    setIcon(new ImageIcon("/images/warning.png"));
                    setText("Unavailable");
                }
            }

            setHorizontalAlignment(JLabel.CENTER); // Căn giữa biểu tượng
            return this;
        }
    }

    private void updateTableBooks() {

        try {
            DefaultTableModel model = controller.getDataTableBooks();
            tableBooks.setModel(model);
            sorter = new TableRowSorter<>(model);
            tableBooks.setRowSorter(sorter);
            tfSearch.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tfSearch.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tfSearch.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tfSearch.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });
            tableBooks.setDefaultEditor(Object.class, null);
            scrollPane1.setViewportView(tableBooks);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // modify table
        tableBooks.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());



        tableBooks.setRowHeight(25);
        TableColumn indexColumn = tableBooks.getColumnModel().getColumn(0);
        TableColumn statusColumn = tableBooks.getColumnModel().getColumn(4);
        indexColumn.setPreferredWidth(20); // Đặt độ rộng cố định
        indexColumn.setCellRenderer(new CenteredTableCellRenderer());
        statusColumn.setCellRenderer(new IconCellRenderer());
    }

    private void tableBooksMouseClicked(MouseEvent e) throws SQLException, RemoteException, MalformedURLException, NotBoundException {
        int selectedRow = tableBooks.getSelectedRow();

        if (selectedRow != -1) {
            // Lấy thông tin từ hàng dữ liệu được chọn
            int bookId = (int) tableBooks.getValueAt(selectedRow, 0);
            String title = (String) tableBooks.getValueAt(selectedRow, 1);
            String author = (String) tableBooks.getValueAt(selectedRow, 2);
            String available = (String) tableBooks.getValueAt(selectedRow, 3);
            Book book = new Book(bookId, title, author, available.equals("Available"));
            // Sử dụng thông tin sách được chọn
            System.out.println("Selected Book:");
            System.out.println("Book ID: " + bookId);
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Available: " + available);
            BookDetailGUI dialog = new BookDetailGUI(this, true, book, user);

            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateTableBooks();
                }
            });


        }
    }

    private void tableBooksMousePressed(MouseEvent e) throws MalformedURLException, SQLException, NotBoundException, RemoteException {
        int selectedRow = tableBooks.getSelectedRow();

        if (selectedRow != -1) {
            // Lấy thông tin từ hàng dữ liệu được chọn
            int bookId = (int) tableBooks.getValueAt(selectedRow, 0);
            String title = (String) tableBooks.getValueAt(selectedRow, 1);
            String author = (String) tableBooks.getValueAt(selectedRow, 2);
            String description = (String) tableBooks.getValueAt(selectedRow, 3);
            String available = (String) tableBooks.getValueAt(selectedRow, 4);
            Book book = new Book(bookId, title, author,description, available.equals("Available"));
            BookDetailGUI dialog = new BookDetailGUI(this, true, book, user);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateTableBooks();
                }
            });
        }
    }

    private void logout(ActionEvent e) throws MalformedURLException, NotBoundException, RemoteException {
        dispose();
        new LoginGUI().setVisible(true);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - hdnguyendev
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        label1 = new JLabel();
        tfSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tableBooks = new JTable();
        panel6 = new JPanel();
        panel5 = new JPanel();
        panel7 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle("eLibrary VKU");
        setIconImage(new ImageIcon(getClass().getResource("/images/stack-of-books.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
                EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
                . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
                java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
                { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
                throw new RuntimeException( ); }} );
                panel1.setLayout(new BorderLayout());

                //======== panel2 ========
                {
                    panel2.setLayout(new BorderLayout());

                    //======== panel3 ========
                    {
                        panel3.setLayout(new BorderLayout());
                    }
                    panel2.add(panel3, BorderLayout.WEST);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new FlowLayout());

                        //---- label1 ----
                        label1.setText("Search");
                        panel4.add(label1);

                        //---- tfSearch ----
                        tfSearch.setPreferredSize(new Dimension(200, 36));
                        panel4.add(tfSearch);
                    }
                    panel2.add(panel4, BorderLayout.NORTH);
                }
                panel1.add(panel2, BorderLayout.WEST);

                //======== scrollPane1 ========
                {
                    scrollPane1.setBorder(new TitledBorder("List Books"));

                    //---- tableBooks ----
                    tableBooks.setModel(new DefaultTableModel());
                    tableBooks.setFont(new Font("Montserrat", Font.PLAIN, 14));
                    tableBooks.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            try {
tableBooksMousePressed(e);} catch (MalformedURLException ex) {
    throw new RuntimeException(ex);
} catch (SQLException ex) {
    throw new RuntimeException(ex);
} catch (NotBoundException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}
                        }
                    });
                    scrollPane1.setViewportView(tableBooks);
                }
                panel1.add(scrollPane1, BorderLayout.CENTER);
            }
            tabbedPane1.addTab("Borrow Books", panel1);

            //======== panel6 ========
            {
                panel6.setLayout(new BorderLayout());
            }
            tabbedPane1.addTab("Profile", panel6);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);

        //======== panel5 ========
        {
            panel5.setLayout(new BorderLayout());

            //======== panel7 ========
            {
                panel7.setLayout(new BorderLayout());

                //---- label2 ----
                label2.setText("eLibrary VKU ");
                label2.setIcon(new ImageIcon(getClass().getResource("/images/stack-of-books.png")));
                label2.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
                panel7.add(label2, BorderLayout.CENTER);

                //---- label3 ----
                label3.setText("Hello, VKUer");
                label3.setFont(new Font("Montserrat", Font.PLAIN, 12));
                panel7.add(label3, BorderLayout.EAST);
            }
            panel5.add(panel7, BorderLayout.CENTER);

            //---- button1 ----
            button1.setText("Exit");
            button1.setFont(new Font("Montserrat", Font.BOLD, 12));
            button1.setIcon(new ImageIcon(getClass().getResource("/images/enter.png")));
            button1.addActionListener(e -> {try {
logout(e);} catch (MalformedURLException ex) {
    throw new RuntimeException(ex);
} catch (NotBoundException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}});
            panel5.add(button1, BorderLayout.EAST);
        }
        contentPane.add(panel5, BorderLayout.NORTH);
        setSize(1040, 445);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - hdnguyendev
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label1;
    private JTextField tfSearch;
    private JScrollPane scrollPane1;
    private JTable tableBooks;
    private JPanel panel6;
    private JPanel panel5;
    private JPanel panel7;
    private JLabel label2;
    private JLabel label3;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
