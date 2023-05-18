package entity.tower;

public class Archer extends Tower {
	
	public Archer(int x, int y, int id) {
		super(x, y, id, 1, 15, 100, 10);
	}

	@Override
	public String getName() {
		return "Archer";
	}
}
