import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePannel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int [GAME_UNITS];
	final int y[] = new int [GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	private List<Score> scoreboard;
	private String player;
	
	
	GamePannel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		
		this.setBackground(Color.black);
		this.setVerifyInputWhenFocusTarget(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		//scoreboard = loadScores();
		
		
	}
	/*
	 
	 
	private List<Score> loadScores() {
		
		List<Score> scores = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader("scores.txt"))){
			String line;
			while((line = reader.readLine()) != null) {
				String[] partStrings = line.split(" - ");
				if(partStrings.length == 2) {
					String player = partStrings[0];
					int score = Integer.parseInt(partStrings[1]);
					scores.add(new Score(player, score));
				}
			}
			
			
		}catch(IOException | NumberFormatException e) {
			
			createEmptyScoresFile();
			
		}
		return scores;
	}
	
	private void createEmptyScoresFile() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"))) {
	        // Create an empty scores file
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private void saveScores() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"))){
			for(Score score: scoreboard) {
				writer.write(score.toString());
				writer.newLine();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addScore(String player, int score) {
		
		Score newScore = new Score(player, score);
		scoreboard.add(newScore);
		Collections.sort(scoreboard, Collections.reverseOrder());
		saveScores();
		
	}
	*/
	public void startGame() {
		
		newApple();
		running =true;
		timer = new Timer(DELAY,this);
		timer.start();
		this.setFocusable(true);
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		draw(g);
		/*
		g.setColor(Color.white);
		g.setFont(new Font("Ink Free", Font.BOLD, 20));
		g.drawString("Player: " + player, 10, 30);
		for(int i=0; i<scoreboard.size(); i++) {
			g.drawString(scoreboard.get(i).toString(),10 ,30* i*20);
			
		}
		*/
		
	}
	
	
	
	public void draw(Graphics g) {
		
		if(running) {
			
		
			//grid forloop
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i=0;i<bodyParts;i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);	
				}
				else {
					//g.setColor(new Color(45,180,0));
					g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}	
			
			
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
			
			
			
			
		}
		else {
			gameOver(g);
		}
		
		
		
	}
	
	public void newApple() {
		
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))* UNIT_SIZE;	
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))* UNIT_SIZE;	
		
	}
	
	public void move() {
		
		for(int i=bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
			
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		
		}
		
	}
	
	public void checkApple() {
		
		if((x[0] == appleX) && (y[0] == appleY)) {
			
			bodyParts++;
			applesEaten++;
			newApple();
			
		}
		
	}

	public void checkCollisions() {
		//checks head collision with body.
		for(int i=bodyParts;i>0;i--) {
			if((x[0]==x[i] && y[0]==y[i])) {
				running = false;
			}
		}
		//checks if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//checks if head touches right border
		if(x[0] >= SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//bottom border
		if(y[0] >= SCREEN_HEIGHT) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		//score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
		
		//game over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		
		/*
		//Prompt user for name
		String player = JOptionPane.showInputDialog(this, "Enter your name: ");
		if(player != null && !player.trim().isEmpty()) {
			
			addScore(player, applesEaten);
			
			repaint();
			
		}
		*/
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
		
	}
	
	private void gameReset() {
		
		
		 bodyParts = 6;
		 applesEaten = 0;
		 direction = 'R';
		 running = true;
		 
		 
		 for(int i=0;i<bodyParts;i++) {
				x[i] = 0;
				y[i] = 0;
				
			}
		 
		 timer.start();
		 repaint();
		
	
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
				
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
				
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
				
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
				
			case KeyEvent.VK_R:
                if (!running) {
                    gameReset();
                }
                break;
			
			}
		}
	}	
}
