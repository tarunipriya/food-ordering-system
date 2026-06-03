import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

public class FoodOrderingSystem extends JFrame
implements ActionListener {

    JComboBox<Integer> pizzaQty,
                       burgerQty,
                       cokeQty;

    JComboBox<String> paymentBox;

    JButton orderButton;

    JLabel result;

    JTextArea receipt;

    HashMap<String,Integer> menu =
        new HashMap<>();

    FoodOrderingSystem() {

        setTitle("Food Ordering System");

        setSize(700,550);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Quantity Array

        Integer qty[] = {0,1,2,3,4,5};

        // Payment Methods

        String payment[] =
        {"UPI","Card","Cash"};

        // Menu Prices

        menu.put("Pizza",200);
        menu.put("Burger",120);
        menu.put("Coke",50);

        // Title

        JLabel title =
            new JLabel("Food Ordering System");

        title.setBounds(160,20,200,30);

        add(title);

        // =========================
        // Pizza Image
        // =========================

        ImageIcon pizzaImage =
            new ImageIcon("pizza.png");

        Image pizzaImg =
            pizzaImage.getImage();

        Image newPizzaImg =
            pizzaImg.getScaledInstance(
                60,
                60,
                Image.SCALE_SMOOTH
            );

        pizzaImage =
            new ImageIcon(newPizzaImg);

        JLabel pizzaImageLabel =
            new JLabel(pizzaImage);

        pizzaImageLabel.setBounds(
            10,
            60,
            60,
            60
        );

        add(pizzaImageLabel);

        // Pizza Label

        JLabel pizzaLabel =
            new JLabel("Pizza - 200");

        pizzaLabel.setBounds(
            90,
            75,
            120,
            30
        );

        add(pizzaLabel);

        // Pizza Quantity

        pizzaQty =
            new JComboBox<>(qty);

        pizzaQty.setBounds(
            250,
            75,
            60,
            30
        );

        add(pizzaQty);

        // =========================
        // Burger Image
        // =========================

        ImageIcon burgerImage =
            new ImageIcon("burger.png");

        Image burgerImg =
            burgerImage.getImage();

        Image newBurgerImg =
            burgerImg.getScaledInstance(
                60,
                60,
                Image.SCALE_SMOOTH
            );

        burgerImage =
            new ImageIcon(newBurgerImg);

        JLabel burgerImageLabel =
            new JLabel(burgerImage);

        burgerImageLabel.setBounds(
            10,
            150,
            60,
            60
        );

        add(burgerImageLabel);

        // Burger Label

        JLabel burgerLabel =
            new JLabel("Burger - 120");

        burgerLabel.setBounds(
            90,
            165,
            120,
            30
        );

        add(burgerLabel);

        // Burger Quantity

        burgerQty =
            new JComboBox<>(qty);

        burgerQty.setBounds(
            250,
            165,
            60,
            30
        );

        add(burgerQty);

        // =========================
        // Coke Image
        // =========================

        ImageIcon cokeImage =
            new ImageIcon("coke.png");

        Image cokeImg =
            cokeImage.getImage();

        Image newCokeImg =
            cokeImg.getScaledInstance(
                60,
                60,
                Image.SCALE_SMOOTH
            );

        cokeImage =
            new ImageIcon(newCokeImg);

        JLabel cokeImageLabel =
            new JLabel(cokeImage);

        cokeImageLabel.setBounds(
            10,
            240,
            60,
            60
        );

        add(cokeImageLabel);

        // Coke Label

        JLabel cokeLabel =
            new JLabel("Coke - 50");

        cokeLabel.setBounds(
            90,
            255,
            120,
            30
        );

        add(cokeLabel);

        // Coke Quantity

        cokeQty =
            new JComboBox<>(qty);

        cokeQty.setBounds(
            250,
            255,
            60,
            30
        );

        add(cokeQty);

        // Payment Label

        JLabel paymentLabel =
            new JLabel("Payment");

        paymentLabel.setBounds(
            90,
            340,
            120,
            30
        );

        add(paymentLabel);

        // Payment ComboBox

        paymentBox =
            new JComboBox<>(payment);

        paymentBox.setBounds(
            250,
            340,
            100,
            30
        );

        add(paymentBox);

        // Order Button

        orderButton =
            new JButton("Place Order");

        orderButton.setBounds(
            150,
            410,
            140,
            40
        );

        add(orderButton);

        // Result Label

        result =
            new JLabel();

        result.setBounds(
            130,
            470,
            250,
            30
        );

        add(result);

        // =========================
        // Receipt Area
        // =========================

        receipt =
            new JTextArea();

        receipt.setBounds(
            450,
            70,
            200,
            300
        );

        add(receipt);

        // Button Event

        orderButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int total = 0;

        String bill =
        "----- RECEIPT -----\n\n";

        try {

            Connection con =
                DBConnection.getConnection();

            // Pizza Quantity

            int pizzaQuantity =
                (Integer)pizzaQty.getSelectedItem();

            // Burger Quantity

            int burgerQuantity =
                (Integer)burgerQty.getSelectedItem();

            // Coke Quantity

            int cokeQuantity =
                (Integer)cokeQty.getSelectedItem();

            // Pizza Total

            if(pizzaQuantity > 0) {

                int pizzaTotal =
                    pizzaQuantity * 200;

                total += pizzaTotal;

                bill +=
                "Pizza : " +
                pizzaTotal +
                "\n";

                saveOrder(
                    con,
                    "Pizza",
                    pizzaTotal
                );
            }

            // Burger Total

            if(burgerQuantity > 0) {

                int burgerTotal =
                    burgerQuantity * 120;

                total += burgerTotal;

                bill +=
                "Burger : " +
                burgerTotal +
                "\n";

                saveOrder(
                    con,
                    "Burger",
                    burgerTotal
                );
            }

            // Coke Total

            if(cokeQuantity > 0) {

                int cokeTotal =
                    cokeQuantity * 50;

                total += cokeTotal;

                bill +=
                "Coke : " +
                cokeTotal +
                "\n";

                saveOrder(
                    con,
                    "Coke",
                    cokeTotal
                );
            }

            // Total

            bill +=
            "\nTotal : " +
            total;

            // Display Total

            result.setText(
                "Total Bill = " + total
            );

            // Show Receipt

            receipt.setText(bill);

            // Payment Method

            String method =
                (String)paymentBox.getSelectedItem();

            JOptionPane.showMessageDialog(
                this,
                "Payment Successful via "
                + method
            );

            // Print Receipt

            receipt.print();

        } catch(Exception ex) {

            System.out.println(ex);
        }
    }

    void saveOrder(
        Connection con,
        String item,
        int price
    ) {

        try {

            PreparedStatement ps =
                con.prepareStatement(
                "INSERT INTO orders(item_name,price) VALUES(?,?)"
            );

            ps.setString(1,item);

            ps.setInt(2,price);

            ps.executeUpdate();

        } catch(Exception e) {

            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        new FoodOrderingSystem();
    }
}