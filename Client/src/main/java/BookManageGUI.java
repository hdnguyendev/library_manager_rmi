import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
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
    TableRowSorter sorter;

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new BookManageGUI();

    }

    public BookManageGUI() throws MalformedURLException, NotBoundException, RemoteException {
        initComponents();
        // load data 
        showTableBook();
        showDataComboBoxCategory();
        showDataComboBoxAuthor();


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private synchronized void showTableBook() {
        try {
            Response response = controller.getBooksController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            sorter = new TableRowSorter<>((DefaultTableModel) response.getData());
            table_Book.setRowSorter(sorter);
            tf_search.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tf_search.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tf_search.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tf_search.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });

            table_Book.setModel((DefaultTableModel) response.getData());
            table_Book.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            table_Book.setRowHeight(30);
            table_Book.setDefaultEditor(Object.class, null);
            TableColumn indexColumn = table_Book.getColumnModel().getColumn(0);
            indexColumn.setCellRenderer(new CenteredTableCellRenderer());
            indexColumn.setMaxWidth(50);
            scrollPaneTableBook.setViewportView(table_Book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private synchronized void showDataComboBoxCategory() {
        try {
            Response response = controller.getDataComboBoxCategories();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            List<Category> categoryList = (List<Category>) response.getData();
            for (Category i :
                    categoryList) {
                cb_category.addItem(i);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private synchronized void showDataComboBoxAuthor() {
        try {
            Response response = controller.getDataComboBoxAuthors();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            List<Author> authorList = (List<Author>) response.getData();
            for (Author i : authorList) {
                cb_author.addItem(i);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void table_BookMousePressed(MouseEvent e) {
        int selectedRow = table_Book.getSelectedRow();
        tf_ID.setEditable(false);
        if (selectedRow != -1) {
            // Lấy thông tin từ hàng dữ liệu được chọn
            int bookId = (int) table_Book.getValueAt(selectedRow, 0);
            String title = (String) table_Book.getValueAt(selectedRow, 1);
            String category = (String) table_Book.getValueAt(selectedRow, 2);
            String author = (String) table_Book.getValueAt(selectedRow, 3);
            // Set vào tf
            tf_ID.setText(String.valueOf(bookId));
            tf_title.setText(title);

            ComboBoxModel<Category> cb_model_category = cb_category.getModel();
            for (int i = 0; i < cb_model_category.getSize(); i++) {
                if (cb_model_category.getElementAt(i).toString().equals(category)) {
                    cb_model_category.setSelectedItem(cb_model_category.getElementAt(i));
                    break;
                }
            }
            ComboBoxModel<Author> cb_model_author = cb_author.getModel();
            for (int i = 0; i < cb_model_author.getSize(); i++) {
                if (cb_model_author.getElementAt(i).toString().equals(author)) {
                    cb_model_author.setSelectedItem(cb_model_author.getElementAt(i));
                    break;
                }
            }
        }
    }

    // Book CRUD
    private void create_book(ActionEvent e) {
        Book book = new Book();
        String book_title = tf_title.getText();
        Category category = (Category) cb_category.getSelectedItem();
        int category_id = category.getId();
        Author author = (Author) cb_author.getSelectedItem();
        int author_id = author.getId();

        book.setTitle(book_title);
        book.setCategory_id(category_id);

        try {
            Response response = controller.createBookController(book, author_id);
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            } else {
                showTableBook();
                JOptionPane.showMessageDialog(this, response.getData());
            }

        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        reset_book(null);



    }

    private void reset_book(ActionEvent e) {
        tf_ID.setEditable(true);

        tf_ID.setText("");
        tf_title.setText("");
        showTableBook();

    }

    private void update_book(ActionEvent e) {

        Book book = new Book();
        int book_id = Integer.parseInt(tf_ID.getText());
        String book_title = tf_title.getText();
        Category category = (Category) cb_category.getSelectedItem();
        int category_id = category.getId();
        Author author = (Author) cb_author.getSelectedItem();
        int author_id = author.getId();

        book.setId(book_id);
        book.setTitle(book_title);
        book.setCategory_id(category_id);

        try {
            Response response = controller.updateBookController(book, author_id);
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            } else {
                showTableBook();
                JOptionPane.showMessageDialog(this, response.getData());
            }

        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
        reset_book(null);

    }

    private void delete_book(ActionEvent e) {

        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sách này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {


            int book_id = Integer.parseInt(tf_ID.getText());

            try {
                Response res = controller.deleteBookController(book_id);
                if (res.getStatus() == 100) {
                    JOptionPane.showMessageDialog(this, res.getData());
                } else {
                    showTableBook();
                    JOptionPane.showMessageDialog(this, res.getData());
                }
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        reset_book(null);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - hdnguyendev
        tabbedPane1 = new JTabbedPane();
        panel9 = new JPanel();
        scrollPaneTableBook = new JScrollPane();
        table_Book = new JTable();
        panel8 = new JPanel();
        label9 = new JLabel();
        hSpacer3 = new JPanel(null);
        tf_search = new JTextField();
        panel10 = new JPanel();
        button8 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        panel12 = new JPanel();
        panel14 = new JPanel();
        panel15 = new JPanel();
        label12 = new JLabel();
        tf_ID = new JTextField();
        panel16 = new JPanel();
        label13 = new JLabel();
        tf_title = new JTextField();
        panel17 = new JPanel();
        label14 = new JLabel();
        cb_category = new JComboBox();
        panel18 = new JPanel();
        label15 = new JLabel();
        cb_author = new JComboBox();
        hSpacer1 = new JPanel(null);
        panel1 = new JPanel();
        btn_create = new JButton();
        btn_update = new JButton();
        panel2 = new JPanel();
        btn_delete = new JButton();
        btn_reset = new JButton();
        hSpacer2 = new JPanel(null);
        hSpacer4 = new JPanel(null);
        hSpacer5 = new JPanel(null);
        panel4 = new JPanel();
        label10 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {

            //======== panel9 ========
            {
                panel9.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
                javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
                . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
                .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,panel9. getBorder( )) ); panel9. addPropertyChangeListener (new java. beans.
                PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
                equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                panel9.setLayout(null);

                //======== scrollPaneTableBook ========
                {

                    //---- table_Book ----
                    table_Book.setFont(new Font("Montserrat", Font.PLAIN, 12));
                    table_Book.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            table_BookMousePressed(e);
                        }
                    });
                    scrollPaneTableBook.setViewportView(table_Book);
                }
                panel9.add(scrollPaneTableBook);
                scrollPaneTableBook.setBounds(15, 50, 540, 380);

                //======== panel8 ========
                {
                    panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));

                    //---- label9 ----
                    label9.setText("Search");
                    label9.setFont(new Font("Montserrat", Font.PLAIN, 12));
                    panel8.add(label9);

                    //---- hSpacer3 ----
                    hSpacer3.setPreferredSize(new Dimension(20, 10));
                    panel8.add(hSpacer3);

                    //---- tf_search ----
                    tf_search.setFont(new Font("Montserrat", Font.PLAIN, 12));
                    tf_search.setPreferredSize(new Dimension(200, 36));
                    panel8.add(tf_search);
                }
                panel9.add(panel8);
                panel8.setBounds(15, 10, 540, 35);

                //======== panel10 ========
                {
                    panel10.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));

                    //---- button8 ----
                    button8.setText("Export Data");
                    button8.setFont(new Font("Montserrat", Font.BOLD, 12));
                    panel10.add(button8);

                    //---- button6 ----
                    button6.setText("Previous");
                    button6.setFont(new Font("Montserrat", Font.BOLD, 12));
                    panel10.add(button6);

                    //---- button7 ----
                    button7.setText("Next");
                    button7.setFont(new Font("Montserrat", Font.BOLD, 12));
                    panel10.add(button7);
                }
                panel9.add(panel10);
                panel10.setBounds(15, 435, 540, panel10.getPreferredSize().height);

                //======== panel12 ========
                {
                    panel12.setBorder(new TitledBorder(null, "Information", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("Montserrat Black", Font.BOLD, 16)));
                    panel12.setLayout(new BorderLayout());

                    //======== panel14 ========
                    {
                        panel14.setLayout(new GridLayout(10, 2, 5, 5));

                        //======== panel15 ========
                        {
                            panel15.setLayout(new BoxLayout(panel15, BoxLayout.X_AXIS));

                            //---- label12 ----
                            label12.setText("ID");
                            label12.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
                            label12.setPreferredSize(new Dimension(70, 20));
                            panel15.add(label12);

                            //---- tf_ID ----
                            tf_ID.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            tf_ID.setPreferredSize(new Dimension(200, 36));
                            panel15.add(tf_ID);
                        }
                        panel14.add(panel15);

                        //======== panel16 ========
                        {
                            panel16.setLayout(new BoxLayout(panel16, BoxLayout.X_AXIS));

                            //---- label13 ----
                            label13.setText("Title");
                            label13.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
                            label13.setPreferredSize(new Dimension(70, 20));
                            panel16.add(label13);

                            //---- tf_title ----
                            tf_title.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            tf_title.setPreferredSize(new Dimension(200, 36));
                            panel16.add(tf_title);
                        }
                        panel14.add(panel16);

                        //======== panel17 ========
                        {
                            panel17.setLayout(new BoxLayout(panel17, BoxLayout.X_AXIS));

                            //---- label14 ----
                            label14.setText("Category");
                            label14.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
                            label14.setPreferredSize(new Dimension(70, 20));
                            panel17.add(label14);
                            panel17.add(cb_category);
                        }
                        panel14.add(panel17);

                        //======== panel18 ========
                        {
                            panel18.setLayout(new BoxLayout(panel18, BoxLayout.X_AXIS));

                            //---- label15 ----
                            label15.setText("Author");
                            label15.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
                            label15.setPreferredSize(new Dimension(70, 20));
                            panel18.add(label15);
                            panel18.add(cb_author);
                        }
                        panel14.add(panel18);
                        panel14.add(hSpacer1);

                        //======== panel1 ========
                        {
                            panel1.setLayout(new GridLayout(1, 2));

                            //---- btn_create ----
                            btn_create.setText("Create");
                            btn_create.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            btn_create.addActionListener(e -> create_book(e));
                            panel1.add(btn_create);

                            //---- btn_update ----
                            btn_update.setText("Update");
                            btn_update.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            btn_update.addActionListener(e -> update_book(e));
                            panel1.add(btn_update);
                        }
                        panel14.add(panel1);

                        //======== panel2 ========
                        {
                            panel2.setLayout(new GridLayout());

                            //---- btn_delete ----
                            btn_delete.setText("Delete");
                            btn_delete.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            btn_delete.addActionListener(e -> delete_book(e));
                            panel2.add(btn_delete);

                            //---- btn_reset ----
                            btn_reset.setText("Reset");
                            btn_reset.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            btn_reset.addActionListener(e -> reset_book(e));
                            panel2.add(btn_reset);
                        }
                        panel14.add(panel2);
                        panel14.add(hSpacer2);
                    }
                    panel12.add(panel14, BorderLayout.CENTER);

                    //---- hSpacer4 ----
                    hSpacer4.setPreferredSize(new Dimension(20, 10));
                    panel12.add(hSpacer4, BorderLayout.WEST);

                    //---- hSpacer5 ----
                    hSpacer5.setPreferredSize(new Dimension(20, 10));
                    panel12.add(hSpacer5, BorderLayout.EAST);
                }
                panel9.add(panel12);
                panel12.setBounds(565, 40, 310, 390);
            }
            tabbedPane1.addTab("Book Mangement", new ImageIcon(getClass().getResource("/images/stack-of-books.png")), panel9);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);

        //======== panel4 ========
        {
            panel4.setLayout(null);

            //---- label10 ----
            label10.setBackground(new Color(0xd2d5d4));
            label10.setIcon(new ImageIcon(getClass().getResource("/images/banner.png")));
            panel4.add(label10);
            label10.setBounds(0, 0, 895, label10.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel4.getComponentCount(); i++) {
                    Rectangle bounds = panel4.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel4.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel4.setMinimumSize(preferredSize);
                panel4.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel4, BorderLayout.NORTH);
        setSize(905, 670);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - hdnguyendev
    private JTabbedPane tabbedPane1;
    private JPanel panel9;
    private JScrollPane scrollPaneTableBook;
    private JTable table_Book;
    private JPanel panel8;
    private JLabel label9;
    private JPanel hSpacer3;
    private JTextField tf_search;
    private JPanel panel10;
    private JButton button8;
    private JButton button6;
    private JButton button7;
    private JPanel panel12;
    private JPanel panel14;
    private JPanel panel15;
    private JLabel label12;
    private JTextField tf_ID;
    private JPanel panel16;
    private JLabel label13;
    private JTextField tf_title;
    private JPanel panel17;
    private JLabel label14;
    private JComboBox cb_category;
    private JPanel panel18;
    private JLabel label15;
    private JComboBox cb_author;
    private JPanel hSpacer1;
    private JPanel panel1;
    private JButton btn_create;
    private JButton btn_update;
    private JPanel panel2;
    private JButton btn_delete;
    private JButton btn_reset;
    private JPanel hSpacer2;
    private JPanel hSpacer4;
    private JPanel hSpacer5;
    private JPanel panel4;
    private JLabel label10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
