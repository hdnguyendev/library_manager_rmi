/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author nguye
 */
public class LibraryGUI extends javax.swing.JFrame {
    ManagerController controller = new ManagerController();
    TableRowSorter sorter;
    Log log;
    InetAddress ipAddress;

    {
        try {
            ipAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    String ip = ipAddress.getHostAddress().isEmpty() ? "Unknown" : ipAddress.getHostAddress();
    String username = "manage";
    String table_name;
    String col_id;

    /**
     * Creates new form LibraryGUI
     */
    public LibraryGUI() {
        initComponents();
        // load data
        showTableBook();
        showTableAuthor();
        showTableCategory();
        showTablePublished();
        showTableBookCopy();
        showTableHold();
        showDataComboBoxCategory();
        showDataComboBoxAuthor();
        //
    }

    private synchronized void showTableBook() {
        try {
            Response response = controller.getBooksController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            sorter = new TableRowSorter<>((DefaultTableModel) response.getData());
            tbl_Book.setRowSorter(sorter);
            tf_search_Book.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tf_search_Book.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tf_search_Book.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tf_search_Book.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });

            tbl_Book.setModel((DefaultTableModel) response.getData());
            tbl_Book.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            tbl_Book.setRowHeight(30);
            tbl_Book.setDefaultEditor(Object.class, null);
            TableColumn indexColumn = tbl_Book.getColumnModel().getColumn(0);
            indexColumn.setCellRenderer(new CenteredTableCellRenderer());
            indexColumn.setMaxWidth(80);
            sp_Book.setViewportView(tbl_Book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    private synchronized void showTableAuthor() {
        try {
            Response response = controller.getAuthorsController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            sorter = new TableRowSorter<>((DefaultTableModel) response.getData());
            tbl_Author.setRowSorter(sorter);
            tf_search_Author.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tf_search_Author.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tf_search_Author.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tf_search_Author.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });

            tbl_Author.setModel((DefaultTableModel) response.getData());
            tbl_Author.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            tbl_Author.setRowHeight(30);
            tbl_Author.setDefaultEditor(Object.class, null);
            TableColumn indexColumn = tbl_Author.getColumnModel().getColumn(0);
            indexColumn.setCellRenderer(new CenteredTableCellRenderer());
            indexColumn.setMaxWidth(80);
            sp_Author.setViewportView(tbl_Author);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    private synchronized void showTableCategory() {
        try {
            Response response = controller.getCategoriesController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            sorter = new TableRowSorter<>((DefaultTableModel) response.getData());
            tbl_Category.setRowSorter(sorter);
            tf_search_Category.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tf_search_Category.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tf_search_Category.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tf_search_Category.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });

            tbl_Category.setModel((DefaultTableModel) response.getData());
            tbl_Category.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            tbl_Category.setRowHeight(30);
            tbl_Category.setDefaultEditor(Object.class, null);
            TableColumn indexColumn = tbl_Category.getColumnModel().getColumn(0);
            indexColumn.setCellRenderer(new CenteredTableCellRenderer());
            indexColumn.setMaxWidth(80);
            sp_Category.setViewportView(tbl_Category);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    private synchronized void showTablePublished() {
        try {
            Response response = controller.getPublishedController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            sorter = new TableRowSorter<>((DefaultTableModel) response.getData());
            tbl_Published.setRowSorter(sorter);
            tf_search_Published.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tf_search_Published.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tf_search_Published.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tf_search_Published.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });

            tbl_Published.setModel((DefaultTableModel) response.getData());
            tbl_Published.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            tbl_Published.setRowHeight(30);
            tbl_Published.setDefaultEditor(Object.class, null);
            TableColumn indexColumn = tbl_Published.getColumnModel().getColumn(0);
            indexColumn.setCellRenderer(new CenteredTableCellRenderer());
            indexColumn.setMaxWidth(80);
            sp_Published.setViewportView(tbl_Published);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    private synchronized void showTableBookCopy() {
        try {
            Response response = controller.getBooksCopyController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            sorter = new TableRowSorter<>((DefaultTableModel) response.getData());
            tbl_BookCopy.setRowSorter(sorter);
            tf_search_BookCopy.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tf_search_BookCopy.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tf_search_BookCopy.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tf_search_BookCopy.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });

            tbl_BookCopy.setModel((DefaultTableModel) response.getData());
            tbl_BookCopy.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            tbl_BookCopy.setRowHeight(30);
            tbl_BookCopy.setDefaultEditor(Object.class, null);
            TableColumn indexColumn = tbl_BookCopy.getColumnModel().getColumn(0);
            indexColumn.setCellRenderer(new CenteredTableCellRenderer());
            indexColumn.setMaxWidth(80);
            sp_BookCopy.setViewportView(tbl_BookCopy);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    private synchronized void showTableHold() {
        try {
            Response response = controller.getHoldsController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            }
            sorter = new TableRowSorter<>((DefaultTableModel) response.getData());
            tbl_Hold.setRowSorter(sorter);
            tf_search_Hold.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tf_search_Hold.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tf_search_Hold.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tf_search_Hold.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });

            tbl_Hold.setModel((DefaultTableModel) response.getData());
            tbl_Hold.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            tbl_Hold.setRowHeight(30);
            tbl_Hold.setDefaultEditor(Object.class, null);
            TableColumn indexColumn = tbl_Hold.getColumnModel().getColumn(0);
            indexColumn.setCellRenderer(new CenteredTableCellRenderer());
            indexColumn.setMaxWidth(80);
            sp_Hold.setViewportView(tbl_Hold);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    // ------------------------------------------------------------------
    private synchronized void showDataComboBoxCategory() {
        try {
            Response response = controller.getDataComboBoxCategories();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            cb_category_Book.removeAllItems();
            List<Category> categoryList = (List<Category>) response.getData();
            for (Category i :
                    categoryList) {
                cb_category_Book.addItem(i);
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
            cb_author_Book.removeAllItems();
            List<Author> authorList = (List<Author>) response.getData();
            for (Author i : authorList) {
                cb_author_Book.addItem(i);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_Logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        main_panel = new javax.swing.JTabbedPane();
        panel_Book = new javax.swing.JPanel();
        sp_Book = new javax.swing.JScrollPane();
        tbl_Book = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tf_search_Book = new javax.swing.JTextField();
        btn_create_Book = new javax.swing.JButton();
        btn_update_Book = new javax.swing.JButton();
        btn_delete_Book = new javax.swing.JButton();
        btn_refresh_Book = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tf_ID_Book = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_title_Book = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cb_category_Book = new javax.swing.JComboBox();
        cb_author_Book = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        panel_Author = new javax.swing.JPanel();
        sp_Author = new javax.swing.JScrollPane();
        tbl_Author = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        tf_search_Author = new javax.swing.JTextField();
        btn_create_Author = new javax.swing.JButton();
        btn_update_Author = new javax.swing.JButton();
        btn_delete_Author = new javax.swing.JButton();
        btn_refresh_Author = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tf_ID_Author = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_name_Author = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        panel_Category = new javax.swing.JPanel();
        sp_Category = new javax.swing.JScrollPane();
        tbl_Category = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        tf_search_Category = new javax.swing.JTextField();
        btn_create_Category = new javax.swing.JButton();
        btn_update_Category = new javax.swing.JButton();
        btn_delete_Category = new javax.swing.JButton();
        btn_refresh_Category = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tf_ID_Category = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tf_name_Category = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        panel_Published = new javax.swing.JPanel();
        sp_Published = new javax.swing.JScrollPane();
        tbl_Published = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        tf_search_Published = new javax.swing.JTextField();
        btn_create_Published = new javax.swing.JButton();
        btn_update_Published = new javax.swing.JButton();
        btn_delete_Published = new javax.swing.JButton();
        btn_refresh_Published = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        tf_ID_Published = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        tf_name_Published = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        panel_BookCopy = new javax.swing.JPanel();
        sp_BookCopy = new javax.swing.JScrollPane();
        tbl_BookCopy = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        tf_search_BookCopy = new javax.swing.JTextField();
        btn_create_BookCopy = new javax.swing.JButton();
        btn_update_BookCopy = new javax.swing.JButton();
        btn_delete_BookCopy = new javax.swing.JButton();
        btn_refresh_BookCopy = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        tf_ID_BookCopy = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tf_year_BookCopy = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cb_book_BookCopy = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        cb_published_BookCopy = new javax.swing.JComboBox();
        panel_Hold = new javax.swing.JPanel();
        sp_Hold = new javax.swing.JScrollPane();
        tbl_Hold = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        tf_search_Hold = new javax.swing.JTextField();
        btn_create_Hold = new javax.swing.JButton();
        btn_update_Hold = new javax.swing.JButton();
        btn_delete_Hold = new javax.swing.JButton();
        btn_refresh_Hold = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        tf_ID_Hold = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tf_start_Hold = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cb_book_Hold = new javax.swing.JComboBox();
        cb_patron_Hold = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        tf_end_Hold = new javax.swing.JTextField();
        panel_Checkout = new javax.swing.JPanel();
        sp_Checkout = new javax.swing.JScrollPane();
        tbl_Checkout = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        tf_search_Checkout = new javax.swing.JTextField();
        btn_create_Checkout = new javax.swing.JButton();
        btn_update_Checkout = new javax.swing.JButton();
        btn_delete_Checkout = new javax.swing.JButton();
        btn_refresh_Checkout = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        cb_book_Checkout = new javax.swing.JComboBox();
        cb_patron_Checkout = new javax.swing.JComboBox();
        tf_end_Checkout = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        tf_ID_Checkout = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        tf_start_Checkout = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        checkBox_Checkout = new javax.swing.JCheckBox();
        jLabel52 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        panel_Patron = new javax.swing.JPanel();
        sp_Patron = new javax.swing.JScrollPane();
        tbl_Patron = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        tf_search_Patron = new javax.swing.JTextField();
        btn_create_Patron = new javax.swing.JButton();
        btn_update_Patron = new javax.swing.JButton();
        btn_delete_Patron = new javax.swing.JButton();
        btn_refresh_Patron = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        tf_ID_Patron = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        tf_email_Patron = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        tf_lname_Patron = new javax.swing.JTextField();
        tf_fname_Patron = new javax.swing.JTextField();
        tf_pass_Patron = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        checkBox_Patron = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VKU Lib");

        btn_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        btn_Logout.setText("Logout");
        btn_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LogoutActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banner.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(780, 780, 780)
                                .addComponent(btn_Logout))
                        .addComponent(jLabel1)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btn_Logout))
                        .addComponent(jLabel1)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        tbl_Book.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_BookMousePressed(evt);
            }
        });
        sp_Book.setViewportView(tbl_Book);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel2.setText("Search");

        btn_create_Book.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_create_Book.setText("Create");
        btn_create_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_BookActionPerformed(evt);
            }
        });

        btn_update_Book.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_update_Book.setText("Update");
        btn_update_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_BookActionPerformed(evt);
            }
        });

        btn_delete_Book.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        btn_delete_Book.setText("Delete");
        btn_delete_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_BookActionPerformed(evt);
            }
        });

        btn_refresh_Book.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        btn_refresh_Book.setText("Refresh");
        btn_refresh_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_BookActionPerformed(evt);
            }
        });

        jLabel3.setText("ID");

        jLabel4.setText("Title");

        jLabel5.setText("Category");

        jLabel6.setText("Author");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jLabel16.setText("Information");

        javax.swing.GroupLayout panel_BookLayout = new javax.swing.GroupLayout(panel_Book);
        panel_Book.setLayout(panel_BookLayout);
        panel_BookLayout.setHorizontalGroup(
                panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_BookLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_BookLayout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_search_Book))
                                        .addComponent(sp_Book, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_BookLayout.createSequentialGroup()
                                                        .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel5)
                                                                .addComponent(jLabel6)
                                                                .addComponent(jLabel4)
                                                                .addComponent(jLabel3))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(tf_ID_Book)
                                                                .addComponent(tf_title_Book)
                                                                .addComponent(cb_category_Book, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cb_author_Book, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel_BookLayout.createSequentialGroup()
                                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btn_delete_Book, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                        .addComponent(btn_create_Book, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_update_Book, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btn_refresh_Book, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(17, 17, 17))
        );
        panel_BookLayout.setVerticalGroup(
                panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_BookLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(tf_search_Book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_BookLayout.createSequentialGroup()
                                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(tf_ID_Book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(tf_title_Book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(cb_category_Book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(cb_author_Book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(19, 19, 19)
                                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_create_Book, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_update_Book, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btn_delete_Book, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_refresh_Book, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(sp_Book, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        main_panel.addTab("Book", panel_Book);

        tbl_Author.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_AuthorMousePressed(evt);
            }
        });
        sp_Author.setViewportView(tbl_Author);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel7.setText("Search");

        btn_create_Author.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_create_Author.setText("Create");
        btn_create_Author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_AuthorActionPerformed(evt);
            }
        });

        btn_update_Author.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_update_Author.setText("Update");
        btn_update_Author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_AuthorActionPerformed(evt);
            }
        });

        btn_delete_Author.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        btn_delete_Author.setText("Delete");
        btn_delete_Author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_AuthorActionPerformed(evt);
            }
        });

        btn_refresh_Author.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        btn_refresh_Author.setText("Refresh");
        btn_refresh_Author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_AuthorActionPerformed(evt);
            }
        });

        jLabel8.setText("ID");

        jLabel9.setText("Name");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jLabel17.setText("Information");

        javax.swing.GroupLayout panel_AuthorLayout = new javax.swing.GroupLayout(panel_Author);
        panel_Author.setLayout(panel_AuthorLayout);
        panel_AuthorLayout.setHorizontalGroup(
                panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_AuthorLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_AuthorLayout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_search_Author))
                                        .addComponent(sp_Author, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_AuthorLayout.createSequentialGroup()
                                                        .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel9)
                                                                .addComponent(jLabel8))
                                                        .addGap(34, 34, 34)
                                                        .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(tf_ID_Author, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                                                .addComponent(tf_name_Author)))
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel_AuthorLayout.createSequentialGroup()
                                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btn_delete_Author, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                        .addComponent(btn_create_Author, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_update_Author, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btn_refresh_Author, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(17, 17, 17))
        );
        panel_AuthorLayout.setVerticalGroup(
                panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_AuthorLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(tf_search_Author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_AuthorLayout.createSequentialGroup()
                                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel8)
                                                        .addComponent(tf_ID_Author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel9)
                                                        .addComponent(tf_name_Author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(21, 21, 21)
                                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_create_Author, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_update_Author, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_AuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btn_delete_Author, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_refresh_Author, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(sp_Author, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(55, Short.MAX_VALUE))
        );

        main_panel.addTab("Author", panel_Author);

        tbl_Category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_CategoryMousePressed(evt);
            }
        });
        sp_Category.setViewportView(tbl_Category);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel10.setText("Search");

        btn_create_Category.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_create_Category.setText("Create");
        btn_create_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_CategoryActionPerformed(evt);
            }
        });

        btn_update_Category.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_update_Category.setText("Update");
        btn_update_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_CategoryActionPerformed(evt);
            }
        });

        btn_delete_Category.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        btn_delete_Category.setText("Delete");
        btn_delete_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_CategoryActionPerformed(evt);
            }
        });

        btn_refresh_Category.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        btn_refresh_Category.setText("Refresh");
        btn_refresh_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_CategoryActionPerformed(evt);
            }
        });

        jLabel11.setText("ID");

        jLabel12.setText("Name");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jLabel18.setText("Information");

        javax.swing.GroupLayout panel_CategoryLayout = new javax.swing.GroupLayout(panel_Category);
        panel_Category.setLayout(panel_CategoryLayout);
        panel_CategoryLayout.setHorizontalGroup(
                panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_CategoryLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_CategoryLayout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_search_Category))
                                        .addComponent(sp_Category, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_CategoryLayout.createSequentialGroup()
                                                        .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel12)
                                                                .addComponent(jLabel11))
                                                        .addGap(34, 34, 34)
                                                        .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(tf_ID_Category, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                                                .addComponent(tf_name_Category)))
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel_CategoryLayout.createSequentialGroup()
                                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btn_delete_Category, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                        .addComponent(btn_create_Category, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_update_Category, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btn_refresh_Category, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(17, 17, 17))
        );
        panel_CategoryLayout.setVerticalGroup(
                panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_CategoryLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(tf_search_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_CategoryLayout.createSequentialGroup()
                                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(tf_ID_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel12)
                                                        .addComponent(tf_name_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_create_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_update_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btn_delete_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_refresh_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(sp_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(55, Short.MAX_VALUE))
        );

        main_panel.addTab("Category", panel_Category);

        tbl_Published.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_PublishedMousePressed(evt);
            }
        });
        sp_Published.setViewportView(tbl_Published);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel20.setText("Search");

        btn_create_Published.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_create_Published.setText("Create");
        btn_create_Published.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_PublishedActionPerformed(evt);
            }
        });

        btn_update_Published.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_update_Published.setText("Update");
        btn_update_Published.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_PublishedActionPerformed(evt);
            }
        });

        btn_delete_Published.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        btn_delete_Published.setText("Delete");
        btn_delete_Published.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_PublishedActionPerformed(evt);
            }
        });

        btn_refresh_Published.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        btn_refresh_Published.setText("Refresh");
        btn_refresh_Published.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_PublishedActionPerformed(evt);
            }
        });

        jLabel21.setText("ID");

        jLabel22.setText("Name");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jLabel23.setText("Information");

        javax.swing.GroupLayout panel_PublishedLayout = new javax.swing.GroupLayout(panel_Published);
        panel_Published.setLayout(panel_PublishedLayout);
        panel_PublishedLayout.setHorizontalGroup(
                panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_PublishedLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_PublishedLayout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_search_Published))
                                        .addComponent(sp_Published, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_PublishedLayout.createSequentialGroup()
                                                        .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel22)
                                                                .addComponent(jLabel21))
                                                        .addGap(34, 34, 34)
                                                        .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(tf_ID_Published, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                                                .addComponent(tf_name_Published)))
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel_PublishedLayout.createSequentialGroup()
                                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btn_delete_Published, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                        .addComponent(btn_create_Published, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_update_Published, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btn_refresh_Published, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(17, 17, 17))
        );
        panel_PublishedLayout.setVerticalGroup(
                panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_PublishedLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel20)
                                        .addComponent(tf_search_Published, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_PublishedLayout.createSequentialGroup()
                                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel21)
                                                        .addComponent(tf_ID_Published, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel22)
                                                        .addComponent(tf_name_Published, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_create_Published, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_update_Published, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PublishedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btn_delete_Published, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_refresh_Published, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(sp_Published, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(48, Short.MAX_VALUE))
        );

        main_panel.addTab("Published", panel_Published);

        tbl_BookCopy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_BookCopyMousePressed(evt);
            }
        });
        sp_BookCopy.setViewportView(tbl_BookCopy);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel13.setText("Search");

        btn_create_BookCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_create_BookCopy.setText("Create");
        btn_create_BookCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_BookCopyActionPerformed(evt);
            }
        });

        btn_update_BookCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_update_BookCopy.setText("Update");
        btn_update_BookCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_BookCopyActionPerformed(evt);
            }
        });

        btn_delete_BookCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        btn_delete_BookCopy.setText("Delete");
        btn_delete_BookCopy.setMaximumSize(new java.awt.Dimension(89, 23));
        btn_delete_BookCopy.setMinimumSize(new java.awt.Dimension(89, 23));
        btn_delete_BookCopy.setPreferredSize(new java.awt.Dimension(89, 23));
        btn_delete_BookCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_BookCopyActionPerformed(evt);
            }
        });

        btn_refresh_BookCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        btn_refresh_BookCopy.setText("Refresh");
        btn_refresh_BookCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_BookCopyActionPerformed(evt);
            }
        });

        jLabel14.setText("ID");

        jLabel15.setText("Year Published");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jLabel19.setText("Information");

        jLabel24.setText("Book Name");

        jLabel25.setText("Published Name");

        javax.swing.GroupLayout panel_BookCopyLayout = new javax.swing.GroupLayout(panel_BookCopy);
        panel_BookCopy.setLayout(panel_BookCopyLayout);
        panel_BookCopyLayout.setHorizontalGroup(
                panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_BookCopyLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_BookCopyLayout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_search_BookCopy))
                                        .addComponent(sp_BookCopy, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_BookCopyLayout.createSequentialGroup()
                                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel15)
                                                                        .addComponent(jLabel14))
                                                                .addGap(22, 22, 22)
                                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(tf_year_BookCopy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(tf_ID_BookCopy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(panel_BookCopyLayout.createSequentialGroup()
                                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel24)
                                                                        .addComponent(jLabel25))
                                                                .addGap(13, 13, 13)
                                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(cb_published_BookCopy, 0, 188, Short.MAX_VALUE)
                                                                        .addComponent(cb_book_BookCopy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGroup(panel_BookCopyLayout.createSequentialGroup()
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btn_create_BookCopy, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                        .addComponent(btn_delete_BookCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(22, 22, 22)
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_update_BookCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btn_refresh_BookCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(17, 17, 17))
        );
        panel_BookCopyLayout.setVerticalGroup(
                panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_BookCopyLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(tf_search_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panel_BookCopyLayout.createSequentialGroup()
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel14)
                                                        .addComponent(tf_ID_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel15)
                                                        .addComponent(tf_year_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel24)
                                                        .addComponent(cb_book_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel25)
                                                        .addComponent(cb_published_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(24, 24, 24)
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_create_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_update_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_BookCopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btn_delete_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_refresh_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(sp_BookCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        main_panel.addTab("Book Copy", panel_BookCopy);

        tbl_Hold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_HoldMousePressed(evt);
            }
        });
        sp_Hold.setViewportView(tbl_Hold);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel26.setText("Search");

        btn_create_Hold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_create_Hold.setText("Create");
        btn_create_Hold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_HoldActionPerformed(evt);
            }
        });

        btn_update_Hold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_update_Hold.setText("Update");
        btn_update_Hold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_HoldActionPerformed(evt);
            }
        });

        btn_delete_Hold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        btn_delete_Hold.setText("Delete");
        btn_delete_Hold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_HoldActionPerformed(evt);
            }
        });

        btn_refresh_Hold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        btn_refresh_Hold.setText("Refresh");
        btn_refresh_Hold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_HoldActionPerformed(evt);
            }
        });

        jLabel27.setText("ID");

        jLabel28.setText("Time Start");

        jLabel29.setText("Book Name");

        jLabel30.setText("Patron");

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jLabel31.setText("Information");

        jLabel46.setText("Time End");

        javax.swing.GroupLayout panel_HoldLayout = new javax.swing.GroupLayout(panel_Hold);
        panel_Hold.setLayout(panel_HoldLayout);
        panel_HoldLayout.setHorizontalGroup(
                panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_HoldLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_HoldLayout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_search_Hold))
                                        .addComponent(sp_Hold, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(panel_HoldLayout.createSequentialGroup()
                                                        .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel29)
                                                                .addComponent(jLabel30)
                                                                .addComponent(jLabel28)
                                                                .addComponent(jLabel27)
                                                                .addComponent(jLabel46))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(tf_ID_Hold)
                                                                .addComponent(tf_start_Hold)
                                                                .addComponent(cb_book_Hold, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cb_patron_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(tf_end_Hold)))
                                                .addGroup(panel_HoldLayout.createSequentialGroup()
                                                        .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(btn_delete_Hold, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                                .addComponent(btn_create_Hold, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(btn_update_Hold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btn_refresh_Hold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
        );
        panel_HoldLayout.setVerticalGroup(
                panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_HoldLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel26)
                                        .addComponent(tf_search_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panel_HoldLayout.createSequentialGroup()
                                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel27)
                                                        .addComponent(tf_ID_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel28)
                                                        .addComponent(tf_start_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel46)
                                                        .addComponent(tf_end_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel29)
                                                        .addComponent(cb_book_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel30)
                                                        .addComponent(cb_patron_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(99, 99, 99)
                                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_create_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_update_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_HoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btn_delete_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_refresh_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(sp_Hold, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        main_panel.addTab("Hold", panel_Hold);

        tbl_Checkout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_CheckoutMousePressed(evt);
            }
        });
        sp_Checkout.setViewportView(tbl_Checkout);

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel32.setText("Search");

        btn_create_Checkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_create_Checkout.setText("Create");
        btn_create_Checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_CheckoutActionPerformed(evt);
            }
        });

        btn_update_Checkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_update_Checkout.setText("Update");
        btn_update_Checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_CheckoutActionPerformed(evt);
            }
        });

        btn_delete_Checkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        btn_delete_Checkout.setText("Delete");
        btn_delete_Checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_CheckoutActionPerformed(evt);
            }
        });

        btn_refresh_Checkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        btn_refresh_Checkout.setText("Refresh");
        btn_refresh_Checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_CheckoutActionPerformed(evt);
            }
        });

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jLabel37.setText("Information");

        jLabel48.setText("ID");

        jLabel49.setText("Time Start");

        jLabel50.setText("Book Name");

        jLabel51.setText("Patron");

        checkBox_Checkout.setText("Returned");

        jLabel52.setText("Status");

        jLabel47.setText("Time End");

        javax.swing.GroupLayout panel_CheckoutLayout = new javax.swing.GroupLayout(panel_Checkout);
        panel_Checkout.setLayout(panel_CheckoutLayout);
        panel_CheckoutLayout.setHorizontalGroup(
                panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_CheckoutLayout.createSequentialGroup()
                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_CheckoutLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panel_CheckoutLayout.createSequentialGroup()
                                                                .addComponent(jLabel32)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tf_search_Checkout))
                                                        .addComponent(sp_Checkout, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE))
                                                .addGap(27, 27, 27)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panel_CheckoutLayout.createSequentialGroup()
                                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(btn_delete_Checkout, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                                        .addComponent(btn_create_Checkout, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btn_update_Checkout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btn_refresh_Checkout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_CheckoutLayout.createSequentialGroup()
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panel_CheckoutLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel50)
                                                                        .addComponent(jLabel51)
                                                                        .addComponent(jLabel49)
                                                                        .addComponent(jLabel48)
                                                                        .addComponent(jLabel52)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_CheckoutLayout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(jLabel47)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(checkBox_Checkout)
                                                        .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(tf_ID_Checkout)
                                                                .addComponent(tf_start_Checkout)
                                                                .addComponent(cb_book_Checkout, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cb_patron_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(tf_end_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18))
        );
        panel_CheckoutLayout.setVerticalGroup(
                panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_CheckoutLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel32)
                                        .addComponent(tf_search_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_CheckoutLayout.createSequentialGroup()
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel48)
                                                        .addComponent(tf_ID_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel49)
                                                        .addComponent(tf_start_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(tf_end_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel47))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel50)
                                                        .addComponent(cb_book_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel51)
                                                        .addComponent(cb_patron_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel52)
                                                        .addComponent(checkBox_Checkout))
                                                .addGap(59, 59, 59)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_create_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_update_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_CheckoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btn_delete_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_refresh_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(sp_Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        main_panel.addTab("Checkout", panel_Checkout);

        tbl_Patron.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_PatronMousePressed(evt);
            }
        });
        sp_Patron.setViewportView(tbl_Patron);

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel38.setText("Search");

        btn_create_Patron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_create_Patron.setText("Create");
        btn_create_Patron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_PatronActionPerformed(evt);
            }
        });

        btn_update_Patron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_update_Patron.setText("Update");
        btn_update_Patron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_PatronActionPerformed(evt);
            }
        });

        btn_delete_Patron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bin.png"))); // NOI18N
        btn_delete_Patron.setText("Delete");
        btn_delete_Patron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_PatronActionPerformed(evt);
            }
        });

        btn_refresh_Patron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changes.png"))); // NOI18N
        btn_refresh_Patron.setText("Refresh");
        btn_refresh_Patron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_PatronActionPerformed(evt);
            }
        });

        jLabel39.setText("ID");

        jLabel40.setText("First name");

        jLabel41.setText("Last Name");

        jLabel42.setText("Email");

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jLabel43.setText("Information");

        jLabel44.setText("Password");

        jLabel45.setText("Status");

        checkBox_Patron.setText("Active");

        javax.swing.GroupLayout panel_PatronLayout = new javax.swing.GroupLayout(panel_Patron);
        panel_Patron.setLayout(panel_PatronLayout);
        panel_PatronLayout.setHorizontalGroup(
                panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_PatronLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_PatronLayout.createSequentialGroup()
                                                .addComponent(jLabel38)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_search_Patron))
                                        .addComponent(sp_Patron, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panel_PatronLayout.createSequentialGroup()
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btn_delete_Patron, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                        .addComponent(btn_create_Patron, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_update_Patron, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btn_refresh_Patron, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_PatronLayout.createSequentialGroup()
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_PatronLayout.createSequentialGroup()
                                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel41)
                                                                        .addComponent(jLabel42)
                                                                        .addComponent(jLabel40)
                                                                        .addComponent(jLabel39)
                                                                        .addComponent(jLabel44)
                                                                        .addComponent(jLabel45))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(tf_ID_Patron, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                                                        .addComponent(tf_email_Patron)
                                                                        .addComponent(tf_lname_Patron)
                                                                        .addComponent(tf_pass_Patron)
                                                                        .addComponent(checkBox_Patron)
                                                                        .addComponent(tf_fname_Patron)))
                                                        .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(17, 17, 17))
        );
        panel_PatronLayout.setVerticalGroup(
                panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_PatronLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel38)
                                        .addComponent(tf_search_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_PatronLayout.createSequentialGroup()
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel39)
                                                        .addComponent(tf_ID_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel40)
                                                        .addComponent(tf_fname_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel41)
                                                        .addComponent(tf_lname_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel42)
                                                        .addComponent(tf_email_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel44)
                                                        .addComponent(tf_pass_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel45)
                                                        .addComponent(checkBox_Patron))
                                                .addGap(70, 70, 70)
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_create_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_update_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panel_PatronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btn_delete_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_refresh_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(sp_Patron, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(53, Short.MAX_VALUE))
        );

        main_panel.addTab("Patron Account", panel_Patron);

        getContentPane().add(main_panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void btn_LogoutActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    // ==================================================================================================================
    // Block

    private boolean checkBlock(String table_name, int col_id) {
        try {
            return controller.checkLog(table_name, col_id);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    // ==================================================================================================================
    // Book - done

    private void tbl_BookMousePressed(java.awt.event.MouseEvent evt) {
        int selectedRow = tbl_Book.getSelectedRow();
        tf_ID_Book.setEditable(false);
        if (selectedRow != -1) {
            table_name = "book";
            // Lấy thông tin từ hàng dữ liệu được chọn
            int bookId = (int) tbl_Book.getValueAt(selectedRow, 0);
            String title = (String) tbl_Book.getValueAt(selectedRow, 1);
            String category = (String) tbl_Book.getValueAt(selectedRow, 2);
            String author = (String) tbl_Book.getValueAt(selectedRow, 3);


            // check block
            if (checkBlock(table_name, bookId)) {
                JOptionPane.showMessageDialog(this, "Bạn không thể thao tác với bản ghi này! Có người dùng khác đang sử dụng bản ghi này!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Set vào tf
            tf_ID_Book.setText(String.valueOf(bookId));
            tf_title_Book.setText(title);

            ComboBoxModel<Category> cb_model_category = cb_category_Book.getModel();
            for (int i = 0; i < cb_model_category.getSize(); i++) {
                if (cb_model_category.getElementAt(i).toString().equals(category)) {
                    cb_model_category.setSelectedItem(cb_model_category.getElementAt(i));
                    break;
                }
            }
            ComboBoxModel<Author> cb_model_author = cb_author_Book.getModel();
            for (int i = 0; i < cb_model_author.getSize(); i++) {
                if (cb_model_author.getElementAt(i).toString().equals(author)) {
                    cb_model_author.setSelectedItem(cb_model_author.getElementAt(i));
                    break;
                }
            }
            Date date = new Date();
            Timestamp time_now = new Timestamp(date.getTime());
            if (log == null) {
                log = new Log(ip, username, table_name, bookId, time_now);
                try {
                    log.setId(controller.createLog(log));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else
                // TH: Click vào bảng khác
                if (log.getTable_name() != table_name) {

                    try {
                        controller.deleteLog(log.getId());
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    // Lấy thời gian hiện tại

                    log = new Log(ip, username, table_name, bookId, time_now);
                    try {
                        log.setId(controller.createLog(log));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }

                } else
                    // TH: Click vào cùng bảng
                    if (log.getTable_name().equals(table_name)) {
                        log.setCol_id(bookId);
                        try {
                            controller.updateLog(log);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
            System.out.println(" LOG: " + log.toString());

        }
    }

    private void btn_refresh_BookActionPerformed(java.awt.event.ActionEvent evt) {
        tf_ID_Book.setEditable(true);

        tf_ID_Book.setText("");
        tf_title_Book.setText("");
        showTableBook();
        showDataComboBoxAuthor();
        showDataComboBoxCategory();
    }

    private void btn_delete_BookActionPerformed(java.awt.event.ActionEvent evt) {

        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sách này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {


            int book_id = Integer.parseInt(tf_ID_Book.getText());

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
        btn_refresh_BookActionPerformed(null);
    }

    private void btn_update_BookActionPerformed(java.awt.event.ActionEvent evt) {
        Book book = new Book();
        int book_id = Integer.parseInt(tf_ID_Book.getText());
        String book_title = tf_title_Book.getText();
        Category category = (Category) cb_category_Book.getSelectedItem();
        int category_id = category.getId();
        Author author = (Author) cb_author_Book.getSelectedItem();
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
        btn_refresh_BookActionPerformed(null);
    }

    private void btn_create_BookActionPerformed(java.awt.event.ActionEvent evt) {
        Book book = new Book();
        String book_title = tf_title_Book.getText();
        Category category = (Category) cb_category_Book.getSelectedItem();
        int category_id = category.getId();
        Author author = (Author) cb_author_Book.getSelectedItem();
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
        btn_refresh_BookActionPerformed(null);
    }
    // ==================================================================================================================
    // Author

    private void tbl_AuthorMousePressed(java.awt.event.MouseEvent evt) {
        int selectedRow = tbl_Author.getSelectedRow();
        tf_ID_Author.setEditable(false);
        if (selectedRow != -1) {
            table_name = "author";
            // Lấy thông tin từ hàng dữ liệu được chọn
            int author_id = (int) tbl_Author.getValueAt(selectedRow, 0);
            String name = (String) tbl_Author.getValueAt(selectedRow, 1);


            // check block
            if (checkBlock(table_name, author_id)) {
                JOptionPane.showMessageDialog(this, "Bạn không thể thao tác với bản ghi này! Có người dùng khác đang sử dụng bản ghi này!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Set vào tf
            tf_ID_Author.setText(String.valueOf(author_id));
            tf_name_Author.setText(name);

            Date date = new Date();
            Timestamp time_now = new Timestamp(date.getTime());
            if (log == null) {
                log = new Log(ip, username, table_name, author_id, time_now);
                try {
                    log.setId(controller.createLog(log));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else
                // TH: Click vào bảng khác
                if (log.getTable_name() != table_name) {

                    try {
                        controller.deleteLog(log.getId());
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    // Lấy thời gian hiện tại

                    log = new Log(ip, username, table_name, author_id, time_now);
                    try {
                        log.setId(controller.createLog(log));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }

                } else
                    // TH: Click vào cùng bảng
                    if (log.getTable_name().equals(table_name)) {
                        log.setCol_id(author_id);
                        try {
                            controller.updateLog(log);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
            System.out.println(" LOG: " + log.toString());

        }
    }

    private void btn_refresh_AuthorActionPerformed(java.awt.event.ActionEvent evt) {
        tf_ID_Author.setEditable(true);

        tf_ID_Author.setText("");
        tf_name_Author.setText("");
        showTableAuthor();
    }

    private void btn_delete_AuthorActionPerformed(java.awt.event.ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {


            int author_id = Integer.parseInt(tf_ID_Author.getText());

            try {
                Response res = controller.deleteAuthorController(author_id);
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
        btn_refresh_BookActionPerformed(null);
    }

    private void btn_update_AuthorActionPerformed(java.awt.event.ActionEvent evt) {
        Author author = new Author();
        if (tf_ID_Author.getText() != null) {
            int author_id = Integer.parseInt(tf_ID_Author.getText());
            author.setId(author_id);
        }
        String author_name = tf_name_Author.getText();
        author.setName(author_name);

        try {
            Response response = controller.updateAuthorController(author);
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            } else {
                showTableAuthor();
                JOptionPane.showMessageDialog(this, response.getData());
            }

        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
        btn_refresh_BookActionPerformed(null);
    }

    private void btn_create_AuthorActionPerformed(java.awt.event.ActionEvent evt) {
        Author author = new Author();
        if (!tf_ID_Author.getText().isEmpty()) {
            int author_id = Integer.parseInt(tf_ID_Author.getText());
            author.setId(author_id);
        }
        String name = tf_name_Author.getText();
        author.setName(name);

        try {
            Response response = controller.createAuthorController(author);
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            } else {
                showTableAuthor();
                JOptionPane.showMessageDialog(this, response.getData());
            }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        btn_refresh_AuthorActionPerformed(null);
    }
    // ==================================================================================================================
    // Category

    private void tbl_CategoryMousePressed(java.awt.event.MouseEvent evt) {
        int selectedRow = tbl_Category.getSelectedRow();
        tf_ID_Category.setEditable(false);
        if (selectedRow != -1) {
            table_name = "category";
            // Lấy thông tin từ hàng dữ liệu được chọn
            int category_id = (int) tbl_Category.getValueAt(selectedRow, 0);
            String name = (String) tbl_Category.getValueAt(selectedRow, 1);


            // check block
            if (checkBlock(table_name, category_id)) {
                JOptionPane.showMessageDialog(this, "Bạn không thể thao tác với bản ghi này! Có người dùng khác đang sử dụng bản ghi này!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Set vào tf
            tf_ID_Category.setText(String.valueOf(category_id));
            tf_name_Category.setText(name);

            Date date = new Date();
            Timestamp time_now = new Timestamp(date.getTime());
            if (log == null) {
                log = new Log(ip, username, table_name, category_id, time_now);
                try {
                    log.setId(controller.createLog(log));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else
                // TH: Click vào bảng khác
                if (log.getTable_name() != table_name) {

                    try {
                        controller.deleteLog(log.getId());
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    // Lấy thời gian hiện tại

                    log = new Log(ip, username, table_name, category_id, time_now);
                    try {
                        log.setId(controller.createLog(log));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }

                } else
                    // TH: Click vào cùng bảng
                    if (log.getTable_name().equals(table_name)) {
                        log.setCol_id(category_id);
                        try {
                            controller.updateLog(log);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
            System.out.println(" LOG: " + log.toString());

        }
    }

    private void btn_refresh_CategoryActionPerformed(java.awt.event.ActionEvent evt) {
        tf_ID_Category.setEditable(true);

        tf_ID_Category.setText("");
        tf_name_Category.setText("");
        showTableCategory();
    }

    private void btn_delete_CategoryActionPerformed(java.awt.event.ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {


            int category_id = Integer.parseInt(tf_ID_Category.getText());

            try {
                Response res = controller.deleteAuthorController(category_id);
                if (res.getStatus() == 100) {
                    JOptionPane.showMessageDialog(this, res.getData());
                } else {
                    showTableCategory();
                    JOptionPane.showMessageDialog(this, res.getData());
                }
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        btn_refresh_CategoryActionPerformed(null);
    }

    private void btn_update_CategoryActionPerformed(java.awt.event.ActionEvent evt) {
        Category category = new Category();
        if (tf_ID_Category.getText() != null) {
            int category_id = Integer.parseInt(tf_ID_Category.getText());
            category.setId(category_id);
        }
        String category_name = tf_name_Category.getText();
        category.setName(category_name);

        try {
            Response response = controller.updateCategoryController(category);
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            } else {
                showTableCategory();
                JOptionPane.showMessageDialog(this, response.getData());
            }

        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
        btn_refresh_BookActionPerformed(null);
    }

    private void btn_create_CategoryActionPerformed(java.awt.event.ActionEvent evt) {
        Category category = new Category();
        if (!tf_ID_Category.getText().isEmpty()) {
            int category_id = Integer.parseInt(tf_ID_Category.getText());
            category.setId(category_id);
        }
        String name = tf_name_Category.getText();
        category.setName(name);

        try {
            Response response = controller.createCategoryController(category);
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            } else {
                showTableCategory();
                JOptionPane.showMessageDialog(this, response.getData());
            }

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        btn_refresh_CategoryActionPerformed(null);
    }
    // ==================================================================================================================
    //

    private void btn_refresh_PatronActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_delete_PatronActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_update_PatronActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_create_PatronActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_PatronMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_refresh_HoldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_delete_HoldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_update_HoldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_HoldMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_BookCopyMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_refresh_PublishedActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_delete_PublishedActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_update_PublishedActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_create_PublishedActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_PublishedMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }


    private void tbl_CheckoutMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_create_CheckoutActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_update_CheckoutActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_delete_CheckoutActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_refresh_CheckoutActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_create_BookCopyActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_delete_BookCopyActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_update_BookCopyActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_refresh_BookCopyActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_create_HoldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibraryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibraryGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btn_Logout;
    private javax.swing.JButton btn_create_Author;
    private javax.swing.JButton btn_create_Book;
    private javax.swing.JButton btn_create_BookCopy;
    private javax.swing.JButton btn_create_Category;
    private javax.swing.JButton btn_create_Checkout;
    private javax.swing.JButton btn_create_Hold;
    private javax.swing.JButton btn_create_Patron;
    private javax.swing.JButton btn_create_Published;
    private javax.swing.JButton btn_delete_Author;
    private javax.swing.JButton btn_delete_Book;
    private javax.swing.JButton btn_delete_BookCopy;
    private javax.swing.JButton btn_delete_Category;
    private javax.swing.JButton btn_delete_Checkout;
    private javax.swing.JButton btn_delete_Hold;
    private javax.swing.JButton btn_delete_Patron;
    private javax.swing.JButton btn_delete_Published;
    private javax.swing.JButton btn_refresh_Author;
    private javax.swing.JButton btn_refresh_Book;
    private javax.swing.JButton btn_refresh_BookCopy;
    private javax.swing.JButton btn_refresh_Category;
    private javax.swing.JButton btn_refresh_Checkout;
    private javax.swing.JButton btn_refresh_Hold;
    private javax.swing.JButton btn_refresh_Patron;
    private javax.swing.JButton btn_refresh_Published;
    private javax.swing.JButton btn_update_Author;
    private javax.swing.JButton btn_update_Book;
    private javax.swing.JButton btn_update_BookCopy;
    private javax.swing.JButton btn_update_Category;
    private javax.swing.JButton btn_update_Checkout;
    private javax.swing.JButton btn_update_Hold;
    private javax.swing.JButton btn_update_Patron;
    private javax.swing.JButton btn_update_Published;
    private javax.swing.JComboBox cb_author_Book;
    private javax.swing.JComboBox cb_book_BookCopy;
    private javax.swing.JComboBox cb_book_Checkout;
    private javax.swing.JComboBox cb_book_Hold;
    private javax.swing.JComboBox cb_category_Book;
    private javax.swing.JComboBox cb_patron_Checkout;
    private javax.swing.JComboBox cb_patron_Hold;
    private javax.swing.JComboBox cb_published_BookCopy;
    private javax.swing.JCheckBox checkBox_Checkout;
    private javax.swing.JCheckBox checkBox_Patron;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane main_panel;
    private javax.swing.JPanel panel_Author;
    private javax.swing.JPanel panel_Book;
    private javax.swing.JPanel panel_BookCopy;
    private javax.swing.JPanel panel_Category;
    private javax.swing.JPanel panel_Checkout;
    private javax.swing.JPanel panel_Hold;
    private javax.swing.JPanel panel_Patron;
    private javax.swing.JPanel panel_Published;
    private javax.swing.JScrollPane sp_Author;
    private javax.swing.JScrollPane sp_Book;
    private javax.swing.JScrollPane sp_BookCopy;
    private javax.swing.JScrollPane sp_Category;
    private javax.swing.JScrollPane sp_Checkout;
    private javax.swing.JScrollPane sp_Hold;
    private javax.swing.JScrollPane sp_Patron;
    private javax.swing.JScrollPane sp_Published;
    private javax.swing.JTable tbl_Author;
    private javax.swing.JTable tbl_Book;
    private javax.swing.JTable tbl_BookCopy;
    private javax.swing.JTable tbl_Category;
    private javax.swing.JTable tbl_Checkout;
    private javax.swing.JTable tbl_Hold;
    private javax.swing.JTable tbl_Patron;
    private javax.swing.JTable tbl_Published;
    private javax.swing.JTextField tf_ID_Author;
    private javax.swing.JTextField tf_ID_Book;
    private javax.swing.JTextField tf_ID_BookCopy;
    private javax.swing.JTextField tf_ID_Category;
    private javax.swing.JTextField tf_ID_Checkout;
    private javax.swing.JTextField tf_ID_Hold;
    private javax.swing.JTextField tf_ID_Patron;
    private javax.swing.JTextField tf_ID_Published;
    private javax.swing.JTextField tf_email_Patron;
    private javax.swing.JTextField tf_end_Checkout;
    private javax.swing.JTextField tf_end_Hold;
    private javax.swing.JTextField tf_fname_Patron;
    private javax.swing.JTextField tf_lname_Patron;
    private javax.swing.JTextField tf_name_Author;
    private javax.swing.JTextField tf_name_Category;
    private javax.swing.JTextField tf_name_Published;
    private javax.swing.JTextField tf_pass_Patron;
    private javax.swing.JTextField tf_search_Author;
    private javax.swing.JTextField tf_search_Book;
    private javax.swing.JTextField tf_search_BookCopy;
    private javax.swing.JTextField tf_search_Category;
    private javax.swing.JTextField tf_search_Checkout;
    private javax.swing.JTextField tf_search_Hold;
    private javax.swing.JTextField tf_search_Patron;
    private javax.swing.JTextField tf_search_Published;
    private javax.swing.JTextField tf_start_Checkout;
    private javax.swing.JTextField tf_start_Hold;
    private javax.swing.JTextField tf_title_Book;
    private javax.swing.JTextField tf_year_BookCopy;
    // End of variables declaration
}
