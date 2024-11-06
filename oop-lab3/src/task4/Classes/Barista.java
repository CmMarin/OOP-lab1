package task4.Classes;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Barista {
    private JFrame frame;
    private List<Coffee> coffeeOrders = new ArrayList<>();


    public void showMainMenu() {
        frame = new JFrame("Java Beans Coffee Shop");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(94, 38, 18));

        JLabel titleLabel = new JLabel("☕ Welcome to Java Beans Coffee Shop! ☕");
        titleLabel.setFont(new Font("Futura", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 222, 173));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add a greeting message
        JLabel greetingLabel = new JLabel("<html><p style='text-align: center; color: #FFDAB9;'>"
                + "Hello there! I'm your friendly barista!<br>"
                + "What can I get started for you today? ☕✨</p></html>");
        greetingLabel.setFont(new Font("Futura", Font.ITALIC, 16));
        greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(greetingLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(new Color(94, 38, 18));

        JButton startOrderingButton = new JButton("🌟 Start Ordering Your Favorite Brew! 🌟");
        styleButton(startOrderingButton);
        startOrderingButton.addActionListener(e -> showOrderScreen());

        JButton viewProductsButton = new JButton("🍽️ Explore Our Coffee Delights! 🍽️");
        styleButton(viewProductsButton);
        viewProductsButton.addActionListener(e -> showProductDetails());

        buttonPanel.add(startOrderingButton);
        buttonPanel.add(viewProductsButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }


    private void styleButton(JButton button) {
        button.setFont(new Font("Futura", Font.BOLD, 18));
        button.setBackground(new Color(205, 133, 63));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    private void showProductDetails() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setSize(600, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(94, 38, 18));

        JLabel label = new JLabel("☕ Our Coffee Creations ☕");
        label.setFont(new Font("Futura", Font.BOLD, 24));
        label.setForeground(new Color(255, 222, 173));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createVerticalStrut(10));
        panel.add(createProductPanel("🌟 **Cappuccino**", "A rich espresso with steamed milk and foam."));
        panel.add(createProductPanel("🔥 **Americano**", "Espresso with hot water for a strong flavor."));
        panel.add(createProductPanel("🍯 **Syrup Cappuccino**", "Classic cappuccino with flavored syrup."));
        panel.add(createProductPanel("🎃 **Pumpkin Spice Latte**", "Espresso with milk and pumpkin spice flavor."));

        panel.add(Box.createVerticalGlue());

        JButton backButton = new JButton("🔙 Back to Main Menu");
        styleButton(backButton);
        backButton.addActionListener(e -> showMainMenu());
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.revalidate();
    }

    private JPanel createProductPanel(String title, String description) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 222, 173), 2));
        productPanel.setBackground(new Color(255, 235, 205));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Futura", Font.BOLD, 18));
        titleLabel.setForeground(new Color(94, 38, 18));
        productPanel.add(titleLabel);

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Futura", Font.PLAIN, 14));
        descriptionArea.setForeground(new Color(94, 38, 18));
        descriptionArea.setBackground(new Color(255, 235, 205));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        descriptionArea.setPreferredSize(new Dimension(560, 80));
        productPanel.add(descriptionArea);
        productPanel.add(Box.createVerticalStrut(10));
        return productPanel;
    }

    private void showOrderScreen() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(94, 38, 18));

        JLabel label = new JLabel("☕ Select Your Coffee ☕");
        label.setFont(new Font("Futura", Font.BOLD, 24));
        label.setForeground(new Color(255, 222, 173));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createVerticalStrut(20)); // Add spacing

        String[] coffeeTypes = {"Cappuccino", "Americano", "Syrup Cappuccino", "Pumpkin Spice Latte"};
        JComboBox<String> coffeeDropdown = new JComboBox<>(coffeeTypes);
        coffeeDropdown.setFont(new Font("Futura", Font.PLAIN, 16));
        coffeeDropdown.setBackground(new Color(255, 235, 205));
        coffeeDropdown.setForeground(new Color(94, 38, 18));
        coffeeDropdown.setPreferredSize(new Dimension(250, 30)); // Adjust size for a nicer dropdown
        panel.add(coffeeDropdown);

        panel.add(Box.createVerticalStrut(20)); // Add spacing

        JButton selectButton = new JButton("Customize Coffee");
        styleButton(selectButton);
        selectButton.addActionListener(e -> showCustomizationScreen((String) coffeeDropdown.getSelectedItem()));

        JButton backButton = new JButton("🔙 Back to Menu");
        styleButton(backButton);
        backButton.addActionListener(e -> showMainMenu());

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.setBackground(new Color(94, 38, 18));
        bottomPanel.add(backButton);
        bottomPanel.add(selectButton);

        panel.add(Box.createVerticalStrut(20)); // Add spacing before buttons
        panel.add(bottomPanel);

        frame.add(panel);
        frame.revalidate();
    }

    private void showCustomizationScreen(String coffeeType) {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(94, 38, 18));

        JLabel label = new JLabel("Customize Your " + coffeeType + ":");
        label.setFont(new Font("Futura", Font.BOLD, 24));
        label.setForeground(new Color(255, 222, 173));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createVerticalStrut(20)); // Add spacing

        JPanel optionsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        optionsPanel.setBackground(new Color(94, 38, 18));

        // Presets for Milk, Spice, and Water
        String[] milkPresets = {"0", "50", "100", "150", "200", "250"};
        String[] spicePresets = {"0", "10", "20", "30", "40", "50"};
        String[] waterPresets = {"0", "50", "100", "150", "200", "250"};

        JComboBox<String> milkDropdown = new JComboBox<>(milkPresets);
        JLabel milkLabel = new JLabel("Milk (ml):");
        styleLabel(milkLabel);

        JComboBox<String> spiceDropdown = new JComboBox<>(spicePresets);
        JLabel spiceLabel = new JLabel("Pumpkin Spice (mg):");
        styleLabel(spiceLabel);

        JComboBox<String> waterDropdown = new JComboBox<>(waterPresets);
        JLabel waterLabel = new JLabel("Water (ml):");
        styleLabel(waterLabel);

        JComboBox<Coffee.Intensity> intensityDropdown = new JComboBox<>(Coffee.Intensity.values());
        intensityDropdown.setFont(new Font("Futura", Font.PLAIN, 14));
        intensityDropdown.setBackground(new Color(255, 235, 205));
        intensityDropdown.setForeground(new Color(94, 38, 18));
        intensityDropdown.setPreferredSize(new Dimension(250, 30)); // Adjust size for a nicer dropdown
        JLabel intensityLabel = new JLabel("Intensity:");
        styleLabel(intensityLabel);

        JComboBox<SyrupCappucino.SyrupType> syrupDropdown = new JComboBox<>(SyrupCappucino.SyrupType.values());
        syrupDropdown.setFont(new Font("Futura", Font.PLAIN, 14));
        syrupDropdown.setBackground(new Color(255, 235, 205));
        syrupDropdown.setForeground(new Color(94, 38, 18));
        syrupDropdown.setPreferredSize(new Dimension(250, 30)); // Adjust size for a nicer dropdown
        JLabel syrupLabel = new JLabel("Syrup Type:");
        styleLabel(syrupLabel);

        optionsPanel.add(intensityLabel);
        optionsPanel.add(intensityDropdown);

        if (coffeeType.equals("Cappuccino") || coffeeType.equals("Syrup Cappuccino") || coffeeType.equals("Pumpkin Spice Latte")) {
            optionsPanel.add(milkLabel);
            optionsPanel.add(milkDropdown);
        }
        if (coffeeType.equals("Pumpkin Spice Latte")) {
            optionsPanel.add(spiceLabel);
            optionsPanel.add(spiceDropdown);
        }
        if (coffeeType.equals("Syrup Cappuccino")) {
            optionsPanel.add(syrupLabel);
            optionsPanel.add(syrupDropdown);
        }
        if (coffeeType.equals("Americano") || coffeeType.equals("Mocha")) {
            optionsPanel.add(waterLabel);
            optionsPanel.add(waterDropdown);
        }

        panel.add(optionsPanel);

        panel.add(Box.createVerticalStrut(20)); // Add spacing before buttons

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.setBackground(new Color(94, 38, 18));

        JButton backButton = new JButton("🔙 Back");
        styleButton(backButton);
        backButton.addActionListener(e -> showOrderScreen());


        JButton addToOrderButton = new JButton("Add to Order");
        styleButton(addToOrderButton);
        addToOrderButton.addActionListener(e -> {
            // Get selected values from the dropdowns
            int milkVolume = Integer.parseInt((String) milkDropdown.getSelectedItem());
            int spiceVolume = Integer.parseInt((String) spiceDropdown.getSelectedItem());
            int waterVolume = Integer.parseInt((String) waterDropdown.getSelectedItem());

            addCoffeeToOrder(coffeeType, milkVolume, spiceVolume, waterVolume, intensityDropdown, syrupDropdown);
            int response = JOptionPane.showConfirmDialog(frame, "Coffee added! Add another coffee?", "Order", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                showOrderScreen();
            } else {
                showPreparationScreen();
            }
        });

        bottomPanel.add(backButton);
        bottomPanel.add(addToOrderButton);

        panel.add(bottomPanel);

        frame.add(panel);
        frame.revalidate();
    }




    private void addCoffeeToOrder(String coffeeType, int milkVolume, int spiceVolume, int waterVolume,
                                  JComboBox<Coffee.Intensity> intensityDropdown,
                                  JComboBox<SyrupCappucino.SyrupType> syrupDropdown) {
        try {
            Coffee.Intensity selectedIntensity = (Coffee.Intensity) intensityDropdown.getSelectedItem();
            Coffee coffee;

            switch (coffeeType) {
                case "Cappuccino":
                    coffee = Cappucino.makeCappucino(milkVolume, selectedIntensity);
                    break;
                case "Americano":
                    coffee = Americano.makeAmericano(waterVolume, selectedIntensity);
                    break;
                case "Syrup Cappuccino":
                    SyrupCappucino.SyrupType syrupType = (SyrupCappucino.SyrupType) syrupDropdown.getSelectedItem();
                    coffee = SyrupCappucino.makeSyrupCappucino(syrupType, milkVolume, selectedIntensity);
                    break;
                case "Pumpkin Spice Latte":
                    coffee = PumpkinSpiceLatte.makePumpkinSpice(milkVolume, spiceVolume, selectedIntensity);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid coffee type selected.");
            }

            coffeeOrders.add(coffee);
            JOptionPane.showMessageDialog(frame, coffeeType + " added to order!");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }






    private void showPreparationScreen() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(94, 38, 18));

        JLabel label = new JLabel("Preparing your order:");
        label.setFont(new Font("Futura", Font.BOLD, 20));
        label.setForeground(new Color(255, 222, 173));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JTextArea preparationText = new JTextArea();
        preparationText.setEditable(false);
        preparationText.setFont(new Font("Futura", Font.PLAIN, 14));
        preparationText.setForeground(new Color(94, 38, 18));
        preparationText.setBackground(new Color(255, 235, 205));
        panel.add(preparationText, BorderLayout.CENTER);

        frame.add(panel);
        frame.revalidate();


        new Thread(() -> {
            for (Coffee coffee : coffeeOrders) {
                try {

                    preparationText.append("Preparing: " + coffee.getClass().getSimpleName() + "...\n");
                    preparationText.append("Your " + coffee.getClass().getSimpleName() + " is ready!\n\n");


                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }


            SwingUtilities.invokeLater(() -> {
                JButton summaryButton = new JButton("Show Order Summary");
                styleButton(summaryButton);
                summaryButton.addActionListener(e -> showOrderSummary());
                panel.add(summaryButton, BorderLayout.SOUTH);
                frame.revalidate();
            });
        }).start();
    }



    private void showOrderSummary() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(94, 38, 18));

        JLabel label = new JLabel("Order Summary:");
        label.setFont(new Font("Futura", Font.BOLD, 20));
        label.setForeground(new Color(255, 222, 173));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JTextArea summaryText = new JTextArea();
        summaryText.setEditable(false);
        summaryText.setFont(new Font("Futura", Font.PLAIN, 14));
        summaryText.setForeground(new Color(94, 38, 18));
        summaryText.setBackground(new Color(255, 235, 205));


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));


        for (Coffee coffee : coffeeOrders) {
            coffee.printDetails();
            summaryText.append(outputStream.toString() + "\n\n");
            outputStream.reset();
        }


        System.setOut(originalOut);

        JScrollPane scrollPane = new JScrollPane(summaryText);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.revalidate();
    }




    private void styleLabel(JLabel label) {
        label.setFont(new Font("Futura", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
    }
}