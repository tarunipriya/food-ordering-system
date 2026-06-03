import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame
implements ActionListener {

    JTextField userField;

    JPasswordField passField;

    JButton login;

    LoginPage() {

        setTitle("Login Page");

        setSize(300,300);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username Label

        JLabel userLabel =
            new JLabel("Username");

        userLabel.setBounds(30,50,100,30);

        add(userLabel);

        // Username TextField

        userField = new JTextField();

        userField.setBounds(120,50,120,30);

        add(userField);

        // Password Label

        JLabel passLabel =
            new JLabel("Password");

        passLabel.setBounds(30,100,100,30);

        add(passLabel);

        // Password Field

        passField = new JPasswordField();

        passField.setBounds(120,100,120,30);

        add(passField);

        // Login Button

        login = new JButton("Login");

        login.setBounds(90,170,100,30);

        add(login);

        login.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con =
                DBConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(
                1,
                userField.getText()
            );

            ps.setString(
                2,
                passField.getText()
            );

            ResultSet rs =
                ps.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Login Successful"
                );

                new FoodOrderingSystem();

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Invalid Login"
                );
            }

        } catch(Exception ex) {

            System.out.println(ex);
        }
    }

    public static void main(String[] args) {

        new LoginPage();
    }
}
