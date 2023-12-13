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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author nguye
 */
public class ClientGUI extends javax.swing.JFrame {
    Patron patron;
    /**
     * Creates new form ClientGUI
     */
    class ClientImpl extends UnicastRemoteObject implements ClientInterface {

        public ClientImpl() throws RemoteException {
        }

        @Override
        public void notify(NOTIFY notify) throws RemoteException {

            if (notify == NOTIFY.CLIENT_UPDATE_NOTIFICATION) showNotification();

        }
    }
    ClientController controller;
    private void showNotification() {
        try {
            Response response = controller.getNotificationByPatronId(patron.getId());
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            }
            tbl_Notification.setModel((DefaultTableModel) response.getData());
//            tbl_Notification.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            tbl_Notification.setRowHeight(50);
            tbl_Notification.setDefaultEditor(Object.class, null);
            sp_Notification.setViewportView(tbl_Notification);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    private void showBookForSearch() {
        try {
            Response response = controller.getBookForSearchController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getData());
            }
            tbl_Book.setModel((DefaultTableModel) response.getData());
            tbl_Book.setRowHeight(50);
            tbl_Book.setDefaultEditor(Object.class, null);
            sp_Book.setViewportView(tbl_Book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ClientGUI(Patron patron) {
        initComponents();
        this.patron = patron;
        setTitle("VKU Library - " + patron.getEmail());

        try {
            controller = new ClientController(new ClientImpl());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        showNotification();
        showBookForSearch();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        panel_banner = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panel_main = new javax.swing.JTabbedPane();
        panel_home = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(100, 32767));
        jPanel5 = new javax.swing.JPanel();
        btn_Logout = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(100, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 20));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 20));
        jPanel6 = new javax.swing.JPanel();
        sp_Notification = new javax.swing.JScrollPane();
        tbl_Notification = new javax.swing.JTable();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        panel_search = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_Report_Book = new javax.swing.JButton();
        btn_Borrow = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        sp_Book = new javax.swing.JScrollPane();
        tbl_Book = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        tf_Search_Book = new javax.swing.JTextField();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        panel_profile = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        jPanel16 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        filler29 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler30 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        filler27 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler28 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        btn_Report_Profile = new javax.swing.JButton();
        btn_Return = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        sp_Profile = new javax.swing.JScrollPane();
        tbl_Profile = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        tf_Search_Profile = new javax.swing.JTextField();
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler24 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler25 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler26 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_banner.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/client_banner.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1000, 150));
        jLabel1.setMinimumSize(new java.awt.Dimension(1000, 150));
        jLabel1.setPreferredSize(new java.awt.Dimension(1000, 150));
        panel_banner.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panel_banner, java.awt.BorderLayout.NORTH);

        panel_main.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        panel_home.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reading_1.png"))); // NOI18N
        jLabel2.setText("VKU Library");
        jPanel4.add(jLabel2, java.awt.BorderLayout.CENTER);
        jPanel4.add(filler1, java.awt.BorderLayout.LINE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());

        btn_Logout.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btn_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        btn_Logout.setText("Logout");
        btn_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LogoutActionPerformed(evt);
            }
        });
        jPanel5.add(btn_Logout, java.awt.BorderLayout.CENTER);
        jPanel5.add(filler2, java.awt.BorderLayout.LINE_END);
        jPanel5.add(filler3, java.awt.BorderLayout.PAGE_START);
        jPanel5.add(filler4, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(jPanel5, java.awt.BorderLayout.LINE_END);

        panel_home.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel6.setLayout(new java.awt.BorderLayout());

        tbl_Notification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_NotificationMousePressed(evt);
            }
        });
        sp_Notification.setViewportView(tbl_Notification);

        jPanel6.add(sp_Notification, java.awt.BorderLayout.CENTER);
        jPanel6.add(filler5, java.awt.BorderLayout.LINE_START);
        jPanel6.add(filler6, java.awt.BorderLayout.LINE_END);
        jPanel6.add(filler7, java.awt.BorderLayout.PAGE_END);
        jPanel6.add(filler8, java.awt.BorderLayout.PAGE_START);

        panel_home.add(jPanel6, java.awt.BorderLayout.CENTER);

        panel_main.addTab("Home", new javax.swing.ImageIcon(getClass().getResource("/images/notification.png")), panel_home); // NOI18N

        panel_search.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel8.add(filler9, java.awt.BorderLayout.LINE_START);
        jPanel8.add(filler10, java.awt.BorderLayout.LINE_END);
        jPanel8.add(filler11, java.awt.BorderLayout.PAGE_END);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reading-book (1).png"))); // NOI18N
        jPanel11.add(jLabel10, java.awt.BorderLayout.PAGE_START);

        jPanel9.setLayout(new java.awt.GridLayout(6, 2, 10, 30));

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel4.setText("Book Name");
        jPanel9.add(jLabel4);

        jLabel9.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel9.setText("OOP");
        jPanel9.add(jLabel9);

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel5.setText("Publishing");
        jPanel9.add(jLabel5);

        jLabel11.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel11.setText("Huynh Cong Phap");
        jPanel9.add(jLabel11);

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel6.setText("Year Published");
        jPanel9.add(jLabel6);

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel12.setText("2024");
        jPanel9.add(jLabel12);

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel7.setText("Category");
        jPanel9.add(jLabel7);

        jLabel13.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel13.setText("Tham Khao");
        jPanel9.add(jLabel13);

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel8.setText("Detail");
        jPanel9.add(jLabel8);

        jLabel14.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel14.setText("This Book very good!");
        jPanel9.add(jLabel14);

        btn_Report_Book.setBackground(new java.awt.Color(255, 0, 0));
        btn_Report_Book.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btn_Report_Book.setForeground(new java.awt.Color(255, 255, 255));
        btn_Report_Book.setText("Report");
        btn_Report_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Report_BookActionPerformed(evt);
            }
        });
        jPanel9.add(btn_Report_Book);

        btn_Borrow.setBackground(new java.awt.Color(0, 204, 0));
        btn_Borrow.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btn_Borrow.setForeground(new java.awt.Color(255, 255, 255));
        btn_Borrow.setText("Borrow this book !");
        btn_Borrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BorrowActionPerformed(evt);
            }
        });
        jPanel9.add(btn_Borrow);

        jPanel11.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel11, java.awt.BorderLayout.CENTER);

        jLabel3.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Book Detail");
        jPanel8.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.BorderLayout());

        tbl_Book.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_BookMousePressed(evt);
            }
        });
        sp_Book.setViewportView(tbl_Book);

        jPanel10.add(sp_Book, java.awt.BorderLayout.CENTER);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel15.setText("Search");
        jPanel12.add(jLabel15, java.awt.BorderLayout.WEST);
        jPanel12.add(tf_Search_Book, java.awt.BorderLayout.CENTER);
        jPanel12.add(filler15, java.awt.BorderLayout.PAGE_END);
        jPanel12.add(filler16, java.awt.BorderLayout.PAGE_START);
        jPanel12.add(filler17, java.awt.BorderLayout.LINE_END);

        jPanel10.add(jPanel12, java.awt.BorderLayout.PAGE_START);
        jPanel10.add(filler12, java.awt.BorderLayout.LINE_START);
        jPanel10.add(filler13, java.awt.BorderLayout.LINE_END);
        jPanel10.add(filler14, java.awt.BorderLayout.PAGE_END);

        jPanel7.add(jPanel10, java.awt.BorderLayout.LINE_START);

        panel_search.add(jPanel7, java.awt.BorderLayout.CENTER);

        panel_main.addTab("Search", new javax.swing.ImageIcon(getClass().getResource("/images/paper-plane.png")), panel_search); // NOI18N

        panel_profile.setLayout(new java.awt.BorderLayout());

        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel15.setLayout(new java.awt.BorderLayout());
        jPanel15.add(filler18, java.awt.BorderLayout.LINE_START);
        jPanel15.add(filler19, java.awt.BorderLayout.LINE_END);
        jPanel15.add(filler20, java.awt.BorderLayout.PAGE_END);

        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reading (2).png"))); // NOI18N
        jPanel16.add(jLabel16, java.awt.BorderLayout.PAGE_START);

        jPanel17.setLayout(new java.awt.GridLayout(6, 2, 10, 30));
        jPanel17.add(filler29);
        jPanel17.add(filler30);

        jLabel17.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel17.setText("Book Name");
        jPanel17.add(jLabel17);

        jLabel18.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel18.setText("OOP");
        jPanel17.add(jLabel18);

        jLabel19.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel19.setText("Start Time");
        jPanel17.add(jLabel19);

        jLabel20.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel20.setText("2023-10-15");
        jPanel17.add(jLabel20);

        jLabel21.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel21.setText("End Time");
        jPanel17.add(jLabel21);

        jLabel22.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel22.setText("2023-10-15");
        jPanel17.add(jLabel22);
        jPanel17.add(filler27);
        jPanel17.add(filler28);

        btn_Report_Profile.setBackground(new java.awt.Color(255, 0, 0));
        btn_Report_Profile.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btn_Report_Profile.setForeground(new java.awt.Color(255, 255, 255));
        btn_Report_Profile.setText("Report");
        btn_Report_Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Report_ProfileActionPerformed(evt);
            }
        });
        jPanel17.add(btn_Report_Profile);

        btn_Return.setBackground(new java.awt.Color(0, 204, 0));
        btn_Return.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btn_Return.setForeground(new java.awt.Color(255, 255, 255));
        btn_Return.setText("Return this book");
        btn_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReturnActionPerformed(evt);
            }
        });
        jPanel17.add(btn_Return);

        jPanel16.add(jPanel17, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel16, java.awt.BorderLayout.CENTER);

        jLabel27.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 24)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Borrow Ticket Detail");
        jPanel15.add(jLabel27, java.awt.BorderLayout.PAGE_START);

        jPanel14.add(jPanel15, java.awt.BorderLayout.WEST);

        jPanel18.setLayout(new java.awt.BorderLayout());

        tbl_Profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_ProfileMousePressed(evt);
            }
        });
        sp_Profile.setViewportView(tbl_Profile);

        jPanel18.add(sp_Profile, java.awt.BorderLayout.CENTER);

        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel28.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_32.png"))); // NOI18N
        jLabel28.setText("Search");
        jPanel19.add(jLabel28, java.awt.BorderLayout.WEST);
        jPanel19.add(tf_Search_Profile, java.awt.BorderLayout.CENTER);
        jPanel19.add(filler21, java.awt.BorderLayout.PAGE_END);
        jPanel19.add(filler22, java.awt.BorderLayout.PAGE_START);
        jPanel19.add(filler23, java.awt.BorderLayout.LINE_END);

        jPanel18.add(jPanel19, java.awt.BorderLayout.PAGE_START);
        jPanel18.add(filler24, java.awt.BorderLayout.LINE_START);
        jPanel18.add(filler25, java.awt.BorderLayout.LINE_END);
        jPanel18.add(filler26, java.awt.BorderLayout.PAGE_END);

        jPanel14.add(jPanel18, java.awt.BorderLayout.CENTER);

        panel_profile.add(jPanel14, java.awt.BorderLayout.CENTER);

        panel_main.addTab("Profile", new javax.swing.ImageIcon(getClass().getResource("/images/reading_24.png")), panel_profile); // NOI18N

        getContentPane().add(panel_main, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void btn_BorrowActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_ReturnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_NotificationMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_LogoutActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_BookMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_Report_BookActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tbl_ProfileMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void btn_Report_ProfileActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btn_Borrow;
    private javax.swing.JButton btn_Logout;
    private javax.swing.JButton btn_Report_Book;
    private javax.swing.JButton btn_Report_Profile;
    private javax.swing.JButton btn_Return;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler24;
    private javax.swing.Box.Filler filler25;
    private javax.swing.Box.Filler filler26;
    private javax.swing.Box.Filler filler27;
    private javax.swing.Box.Filler filler28;
    private javax.swing.Box.Filler filler29;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler30;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel panel_banner;
    private javax.swing.JPanel panel_home;
    private javax.swing.JTabbedPane panel_main;
    private javax.swing.JPanel panel_profile;
    private javax.swing.JPanel panel_search;
    private javax.swing.JScrollPane sp_Book;
    private javax.swing.JScrollPane sp_Notification;
    private javax.swing.JScrollPane sp_Profile;
    private javax.swing.JTable tbl_Book;
    private javax.swing.JTable tbl_Notification;
    private javax.swing.JTable tbl_Profile;
    private javax.swing.JTextField tf_Search_Book;
    private javax.swing.JTextField tf_Search_Profile;
    // End of variables declaration
}
