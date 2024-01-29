
public class User  {
	private String name;
    private String surname;
    private String pin;
    private int balance;
    
    public User(String name, String surname, String pin, int balance) {
        this.name = name;
        this.surname = surname;
        this.pin = pin;
        this.balance = balance;
    }
    
    public String getPin() {
    	return pin;
    }
    
    public int getBalance() {
		return balance;
	}
    
    public void setBalance(int balance) {
		this.balance = balance;
	}
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "Balance: " + balance;
    }
    
    
}