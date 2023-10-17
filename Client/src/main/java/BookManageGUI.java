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
//        updateTableBooks();
//        showTableBorrowed();
        showTableBook();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private synchronized void showTableBook() {
        try {
            Response response = controller.getBooksController();
            if (response.getStatus() == 100) {
                JOptionPane.showMessageDialog(this, response.getStatus());
            }
            tableBook.setModel((DefaultTableModel) response.getData());
            tableBook.setDefaultEditor(Object.class, null);
            scrollPaneTableBook.setViewportView(tableBook);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }







    private void tableBooksMouseEntered(MouseEvent e) throws MalformedURLException, SQLException, NotBoundException, RemoteException {
       
    }

    private void logout(ActionEvent e) {
        dispose();
        try {
            new LoginGUI().setVisible(true);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (NotBoundException ex) {
            throw new RuntimeException(ex);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - hdnguyendev
        tabbedPane1 = new JTabbedPane();
        panel9 = new JPanel();
        scrollPaneTableBook = new JScrollPane();
        tableBook = new JTable();
        panel8 = new JPanel();
        label9 = new JLabel();
        textField1 = new JTextField();
        panel10 = new JPanel();
        button8 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        panel12 = new JPanel();
        panel13 = new JPanel();
        label11 = new JLabel();
        panel14 = new JPanel();
        panel15 = new JPanel();
        label12 = new JLabel();
        textField2 = new JTextField();
        panel16 = new JPanel();
        label13 = new JLabel();
        textField3 = new JTextField();
        panel17 = new JPanel();
        label14 = new JLabel();
        textField4 = new JTextField();
        panel18 = new JPanel();
        label15 = new JLabel();
        textField5 = new JTextField();
        hSpacer1 = new JPanel(null);
        button9 = new JButton();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        hSpacer2 = new JPanel(null);
        panel4 = new JPanel();
        button5 = new JButton();
        label10 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));

        //======== tabbedPane1 ========
        {

            //======== panel9 ========
            {
                panel9.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
                javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax
                . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
                . awt .Font ( "D\u0069al\u006fg", java .awt . Font. BOLD ,12 ) ,java . awt
                . Color .red ) ,panel9. getBorder () ) ); panel9. addPropertyChangeListener( new java. beans .
                PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .
                equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
                panel9.setLayout(null);

                //======== scrollPaneTableBook ========
                {

                    //---- tableBook ----
                    tableBook.setFont(new Font("Montserrat", Font.PLAIN, 12));
                    scrollPaneTableBook.setViewportView(tableBook);
                }
                panel9.add(scrollPaneTableBook);
                scrollPaneTableBook.setBounds(15, 50, 545, 375);

                //======== panel8 ========
                {
                    panel8.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));

                    //---- label9 ----
                    label9.setText("Search");
                    label9.setIcon(new ImageIcon(getClass().getResource("/images/search_32.png")));
                    label9.setFont(new Font("Montserrat", Font.PLAIN, 12));
                    panel8.add(label9);

                    //---- textField1 ----
                    textField1.setFont(new Font("Montserrat", Font.PLAIN, 12));
                    textField1.setPreferredSize(new Dimension(300, 36));
                    panel8.add(textField1);
                }
                panel9.add(panel8);
                panel8.setBounds(15, 5, 545, 40);

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
                panel10.setBounds(15, 430, 545, panel10.getPreferredSize().height);

                //======== panel12 ========
                {
                    panel12.setLayout(new BorderLayout());

                    //======== panel13 ========
                    {
                        panel13.setLayout(new FlowLayout());

                        //---- label11 ----
                        label11.setText("Information");
                        label11.setFont(new Font("Montserrat Black", Font.PLAIN, 12));
                        panel13.add(label11);
                    }
                    panel12.add(panel13, BorderLayout.NORTH);

                    //======== panel14 ========
                    {
                        panel14.setLayout(new GridLayout(10, 2, 5, 5));

                        //======== panel15 ========
                        {
                            panel15.setLayout(new BoxLayout(panel15, BoxLayout.X_AXIS));

                            //---- label12 ----
                            label12.setText("ID");
                            label12.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
                            label12.setPreferredSize(new Dimension(50, 20));
                            panel15.add(label12);

                            //---- textField2 ----
                            textField2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            textField2.setPreferredSize(new Dimension(200, 36));
                            panel15.add(textField2);
                        }
                        panel14.add(panel15);

                        //======== panel16 ========
                        {
                            panel16.setLayout(new BoxLayout(panel16, BoxLayout.X_AXIS));

                            //---- label13 ----
                            label13.setText("Name");
                            label13.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
                            label13.setPreferredSize(new Dimension(50, 20));
                            panel16.add(label13);

                            //---- textField3 ----
                            textField3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            textField3.setPreferredSize(new Dimension(200, 36));
                            panel16.add(textField3);
                        }
                        panel14.add(panel16);

                        //======== panel17 ========
                        {
                            panel17.setLayout(new BoxLayout(panel17, BoxLayout.X_AXIS));

                            //---- label14 ----
                            label14.setText("Info");
                            label14.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
                            label14.setPreferredSize(new Dimension(50, 20));
                            panel17.add(label14);

                            //---- textField4 ----
                            textField4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            textField4.setPreferredSize(new Dimension(200, 36));
                            panel17.add(textField4);
                        }
                        panel14.add(panel17);

                        //======== panel18 ========
                        {
                            panel18.setLayout(new BoxLayout(panel18, BoxLayout.X_AXIS));

                            //---- label15 ----
                            label15.setText("Info");
                            label15.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
                            label15.setPreferredSize(new Dimension(50, 20));
                            panel18.add(label15);

                            //---- textField5 ----
                            textField5.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            textField5.setPreferredSize(new Dimension(200, 36));
                            panel18.add(textField5);
                        }
                        panel14.add(panel18);
                        panel14.add(hSpacer1);

                        //---- button9 ----
                        button9.setText("Create");
                        button9.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel14.add(button9);

                        //---- button10 ----
                        button10.setText("Update");
                        button10.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel14.add(button10);

                        //---- button11 ----
                        button11.setText("Delete");
                        button11.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel14.add(button11);

                        //---- button12 ----
                        button12.setText("Reset");
                        button12.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel14.add(button12);
                        panel14.add(hSpacer2);
                    }
                    panel12.add(panel14, BorderLayout.CENTER);
                }
                panel9.add(panel12);
                panel12.setBounds(570, 10, 315, 445);
            }
            tabbedPane1.addTab("Book Mangement", new ImageIcon(getClass().getResource("/images/stack-of-books.png")), panel9);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);

        //======== panel4 ========
        {
            panel4.setLayout(null);

            //---- button5 ----
            button5.setText("Log out");
            button5.addActionListener(e -> logout(e));
            panel4.add(button5);
            button5.setBounds(new Rectangle(new Point(780, 50), button5.getPreferredSize()));

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
        setSize(925, 675);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - hdnguyendev
    private JTabbedPane tabbedPane1;
    private JPanel panel9;
    private JScrollPane scrollPaneTableBook;
    private JTable tableBook;
    private JPanel panel8;
    private JLabel label9;
    private JTextField textField1;
    private JPanel panel10;
    private JButton button8;
    private JButton button6;
    private JButton button7;
    private JPanel panel12;
    private JPanel panel13;
    private JLabel label11;
    private JPanel panel14;
    private JPanel panel15;
    private JLabel label12;
    private JTextField textField2;
    private JPanel panel16;
    private JLabel label13;
    private JTextField textField3;
    private JPanel panel17;
    private JLabel label14;
    private JTextField textField4;
    private JPanel panel18;
    private JLabel label15;
    private JTextField textField5;
    private JPanel hSpacer1;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JPanel hSpacer2;
    private JPanel panel4;
    private JButton button5;
    private JLabel label10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
