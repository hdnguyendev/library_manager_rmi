/*
 * Created by JFormDesigner on Tue Sep 26 21:07:09 ICT 2023
 */

import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author nguye
 */
public class LibraryGUI extends JFrame {
    LibraryController controller = new LibraryController();
    public LibraryGUI() throws MalformedURLException, NotBoundException, RemoteException {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        updateTableBooks();
        
    }

    private void updateTableBooks() {
        try {
            tableBooks.setModel(controller.getDataTableBooks());
            scrollPane1.setViewportView(tableBooks);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableBooks.setRowHeight(50);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Hồ Đăng Nguyện
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        tableBooks = new JTable();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
                . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing
                .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
                Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
                ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
                public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName (
                ) ) )throw new RuntimeException( ) ;} } );
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
                        panel4.add(textField1);

                        //---- button1 ----
                        button1.setText("\u2705");
                        panel4.add(button1);
                    }
                    panel2.add(panel4, BorderLayout.NORTH);
                }
                panel1.add(panel2, BorderLayout.WEST);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(tableBooks);
                }
                panel1.add(scrollPane1, BorderLayout.CENTER);
            }
            tabbedPane1.addTab("Libary", panel1);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);
        setSize(700, 445);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Hồ Đăng Nguyện
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable tableBooks;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
