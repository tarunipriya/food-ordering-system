import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminPanel extends JFrame
implements ActionListener {

    JTextField itemField,
               priceField;

    JButton addButton;

    JLabel result;

    AdminPanel() {

        setTitle("Admin Panel");

        setSize(400,350);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title

        JLabel title =
            new JLabel("Admin Panel");

        title.setBounds(140,20,200,30);

        add(title);

        // Item Name Label

        JLabel itemLabel =
            new JLabel("Item Name");

        itemLabel.setBounds(50,80,100,30);

        add(itemLabel);

        // Item TextField

        itemField =
            new JTextField();

        itemField.setBounds(170,80,150,30);

        add(itemField);

        // Price Label

        JLabel priceLabel =
            new JLabel("Price");

        priceLabel.setBounds(50,140,100,30);

        add(priceLabel);

        // Price TextField

        priceField =
            new JTextField();

        priceField.setBounds(170,140,150,30);

        add(priceField);

        // Add Button

        addButton =
            new JButton("Add Item");

        addButton.setBounds(120,210,120,40);

        add(addButton);

        // Result Label

        result =
            new JLabel();

        result.setBounds(90,270,250,30);

        add(result);

        // Button Event

        addButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            String item =
                itemField.getText();

            int price =
                Integer.parseInt(
                    priceField.getText()
                );

            Connection con =
                DBConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement(
                "INSERT INTO food VALUES(?,?)"
            );

            ps.setString(1,item);

            ps.setInt(2,price);

            ps.executeUpdate();

            result.setText(
                "Food Item Added Successfully"
            );

            // Clear Fields

            itemField.setText("");

            priceField.setText("");

        } catch(Exception ex) {

            System.out.println(ex);
        }
    }

    public static void main(String[] args) {

        new AdminPanel();
    }
}
