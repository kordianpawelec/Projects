import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Atm extends JFrame {

	private JTextField pinField;
	private JTextArea outpuTextArea;
	private JButton checkBalanceButton;
	private JButton withdrawButton;
	private JButton createNewUserButton;

	// private int balance = 1000; // Start Balcance

	private List<User> users = new ArrayList<>();

	public Atm() {
		setTitle("ATM");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		users.add(new User("John", "Doe", "1234", 1500));
		users.add(new User("Jane", "Smith", "5678", 2000));

		initComponents();
	}

	private void initComponents() {
		JPanel panel = new JPanel(new BorderLayout());

		pinField = new JTextField();
		pinField.setBorder(BorderFactory.createTitledBorder("Enter PIN"));
		panel.add(pinField, BorderLayout.NORTH);
		pinField.setFont(new Font("MV Boli", Font.PLAIN, 25));

		outpuTextArea = new JTextArea();
		outpuTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outpuTextArea);
		panel.add(scrollPane, BorderLayout.CENTER);

		checkBalanceButton = new JButton("CHECK BALANCE");
		withdrawButton = new JButton("WITHDRAW");
		createNewUserButton = new JButton("CREATE NEW USER");

		createNewUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createNewUser();

			}
		});

		checkBalanceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkBalance();

			}
		});

		withdrawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				withdraw();
			}
		});

		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		buttonPanel.add(checkBalanceButton);
		buttonPanel.add(withdrawButton);
		buttonPanel.add(createNewUserButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		add(panel);

		setVisible(true);

	}

	private void createNewUser() {

		String name = JOptionPane.showInputDialog("Name");
		String surename = JOptionPane.showInputDialog("Surname");
		String pin = JOptionPane.showInputDialog("New PIN");
		int startingBalance = Integer.parseInt(JOptionPane.showInputDialog("Starting Cash"));

		User newUser = new User(surename, name, pin, startingBalance);
		users.add(newUser);

		displayMessage("New User Created" + name + " " + surename);
	}

	private void checkBalance() {

		String pin = pinField.getText();

		if (isValidPIN(pin)) {
			User thisUser = getUserByPin(pin);

			if (thisUser != null) {
				displayMessage("Balance: $" + thisUser.getBalance());
			} else {
				displayMessage("User Not Found");
			}
		}
		else {
			displayMessage("Invalid PIN");
		}
		
		clearFields();
	}

	private void withdraw() {

		String pin = pinField.getText();
		if (isValidPIN(pin)) {
			User thisUser = getUserByPin(pin);

			if(thisUser != null) {

				int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter withdraw amount: "));
				if (amount > 0 && amount <= thisUser.getBalance()) {

					thisUser.setBalance(thisUser.getBalance() - amount);
					displayMessage("Success. Remaining Balance: $" + thisUser.getBalance());
				}
				else {
					displayMessage("Insufficient funds");
				}
			}
			else {
				displayMessage("User not Found");
			}
		}
		else {
			displayMessage("Invalid PIN");
		}
		clearFields();
	}

	private boolean isValidPIN(String pin) {
		for (User user : users) {
			if (user.getPin().equals(pin)) {
				return true;
			}
		}
		return false;
	}

	private void displayMessage(String message) {
		outpuTextArea.append(message + "\n");
	}

	private void clearFields() {
		pinField.setText("");
	}

	private User getUserByPin(String pin) {
		for (User user : users) {
			if (user.getPin().equals(pin)) {
				return user;
			}
		}
		return null;
	}

}
