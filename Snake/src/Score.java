
public class Score implements Comparable<Score>{
	private String player;
	private int score;
	
	
	public Score(String player, int score) {
		this.player = player;
		this.score = score;
	}
	
	public String getPayer() {
		return player;
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
    public int compareTo(Score other) {
        // Compare scores in descending order
        return Integer.compare(other.score, this.score);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return player + " - " + score;
	}

}
