/*
 * Created by JFormDesigner on Tue Sep 26 20:58:23 ICT 2023
 */

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;

/**
 * @author nguye
 */
public class LoginGUI extends JFrame {
    UserController controller = new UserController();

    public LoginGUI() throws MalformedURLException, NotBoundException, RemoteException {
        initComponents();
    }

    private void btnLogin(ActionEvent e) throws SQLException, RemoteException, MalformedURLException, NotBoundException {
        String username = tfUsername.getText().trim();
        String pass = tfPassword.getText();
        Response response = controller.login(username, pass);
        if (response.getStatus() == 200) {
            setVisible(false);
            new LibraryGUI((User) response.getData());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Hồ Đăng Nguyện
        panel1 = new JPanel();
        label1 = new JLabel();
        tfUsername = new JTextField();
        label2 = new JLabel();
        tfPassword = new JPasswordField();
        panel2 = new JPanel();
        btnLogin = new JButton();

        //======== this ========
        setTitle("Login to Library VKU");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/images/enter.png")).getImage());
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0
            ,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
            ,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red),
            panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}});
            panel1.setLayout(new GridLayout(2, 2));

            //---- label1 ----
            label1.setText("Student's ID");
            label1.setIcon(new ImageIcon(getClass().getResource("/images/face-scan.png")));
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            label1.setMaximumSize(new Dimension(50, 32));
            label1.setMinimumSize(new Dimension(50, 32));
            label1.setPreferredSize(new Dimension(50, 32));
            panel1.add(label1);

            //---- tfUsername ----
            tfUsername.setColumns(2);
            panel1.add(tfUsername);

            //---- label2 ----
            label2.setText("Password");
            label2.setIcon(new ImageIcon(getClass().getResource("/images/password.png")));
            label2.setHorizontalAlignment(SwingConstants.LEFT);
            label2.setMaximumSize(new Dimension(50, 32));
            label2.setMinimumSize(new Dimension(50, 32));
            label2.setPreferredSize(new Dimension(50, 32));
            panel1.add(label2);

            //---- tfPassword ----
            tfPassword.setColumns(2);
            panel1.add(tfPassword);
        }
        contentPane.add(panel1, BorderLayout.CENTER);

        //======== panel2 ========
        {
            panel2.setLayout(new FlowLayout());

            //---- btnLogin ----
            btnLogin.setText("Login");
            btnLogin.addActionListener(e -> {try {
btnLogin(e);} catch (SQLException ex) {
    throw new RuntimeException(ex);
} catch (RemoteException ex) {
    throw new RuntimeException(ex);
}catch (MalformedURLException ex) {
    throw new RuntimeException(ex);
}catch (NotBoundException ex) {
    throw new RuntimeException(ex);
}});
            panel2.add(btnLogin);
        }
        contentPane.add(panel2, BorderLayout.SOUTH);
        setSize(335, 160);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Hồ Đăng Nguyện
    private JPanel panel1;
    private JLabel label1;
    private JTextField tfUsername;
    private JLabel label2;
    private JPasswordField tfPassword;
    private JPanel panel2;
    private JButton btnLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
