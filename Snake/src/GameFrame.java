import javax.swing.JFrame;

public class GameFrame extends JFrame{

	public GameFrame() {
		// TODO Auto-generated constructor stub
		
		
		
		this.add(new GamePannel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}

}
