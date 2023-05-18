package entity.tower;

public class Wizard extends Tower {
	
	public Wizard(int x, int y, int id) {
		super(x, y, id, 2, 30, 300, 100);
	}

	@Override
	public String getName() {
		return "Wizard";
	}
}
