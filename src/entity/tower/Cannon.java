package entity.tower;

public class Cannon extends Tower {
	
	
	public Cannon(int x, int y, int id) {
		super(x, y, id, 0, 250, 100, 50);
	}

	@Override
	public String getName() {
		return "Cannon";
	}
	

}
