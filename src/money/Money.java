package money;

public class Money {
	
	private int money = 0;
	
	public Money(int amount) {
		money = amount;
	}

	public void updateMoney(int quantity) {
		money = Math.max(0, money+quantity);
	}
	
	public int getMoney() {
		return money;
	}
}
