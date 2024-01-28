import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TikTakToe implements ActionListener {

	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JPanel buttonJPanel = new JPanel();
	JLabel text = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean player1;

	JButton restart;

	public TikTakToe() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		text.setBackground(new Color(25, 25, 25));
		text.setForeground(Color.green);
		text.setFont(new Font("Ink Free", Font.BOLD, 75));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setText("TicTacToe");
		text.setOpaque(true);

		panel.setLayout(new BorderLayout());
		panel.setBounds(0, 0, 800, 100);

		buttonJPanel.setLayout(new GridLayout(3, 3));
		buttonJPanel.setBackground(new Color(150, 150, 150));

		restart = new JButton("Restart");
		restart.setFont(new Font("MV Boil", Font.BOLD, 30));
		restart.setFocusable(false);
		restart.addActionListener(this);

		panel.add(restart, BorderLayout.SOUTH);
		firstTurn();

		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttonJPanel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boil", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setBackground(Color.white);
		}

		panel.add(text);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(buttonJPanel);

		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == restart) {
			restartGame();
		} else {

			for (int i = 0; i < 9; i++) {
				if (e.getSource() == buttons[i]) {
					if (player1) {
						if (buttons[i].getText() == "") {
							buttons[i].setForeground(Color.blue);
							buttons[i].setText("X");
							player1 = false;
							text.setText("O turn");
							check();

						}
					} else {
						if (buttons[i].getText() == "") {
							buttons[i].setForeground(Color.red);
							buttons[i].setText("O");
							player1 = true;
							text.setText("X turn");
							check();

						}
					}
				}

			}

		}

	}

	public void firstTurn() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (random.nextInt(2) == 0) {
			player1 = true;
			text.setText("X turn");
		} else {
			player1 = false;
			text.setText("O turn");
		}

	}
	
	public void restartGame() {
		for(int i=0;i<9;i++) {
			buttons[i].setText("");
			buttons[i].setEnabled(true);
			buttons[i].setBackground(Color.white);
									
		}
		firstTurn();
		text.setText("TicTacToe");
		
	}

	public void check() {

		if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {

			xWis(0, 1, 2);

		}

		if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {

			xWis(3, 4, 5);

		}

		if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {

			xWis(6, 7, 8);

		}

		if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {

			xWis(0, 3, 6);

		}

		if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {

			xWis(1, 4, 7);

		}

		if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {

			xWis(2, 5, 8);

		}

		if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {

			xWis(0, 4, 8);

		}

		if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {

			xWis(2, 4, 6);

		}

		if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {

			oWis(0, 1, 2);

		}

		if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {

			oWis(3, 4, 5);

		}

		if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {

			oWis(6, 7, 8);

		}

		if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {

			oWis(0, 3, 6);

		}

		if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {

			oWis(1, 4, 7);

		}

		if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {

			oWis(2, 5, 8);

		}

		if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {

			oWis(0, 4, 8);

		}

		if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {

			oWis(2, 4, 6);

		}

	}

	public void xWis(int a, int b, int c) {

		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}

		text.setText("X wins");
	}

	public void oWis(int a, int b, int c) {

		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}

		text.setText("O wins");

	}

}
