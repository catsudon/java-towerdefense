package entity.enemy;

import static utilities.Constants.Enemies.ORC;

public class Orc extends Enemy {
	
	public Orc(float x, float y, int ID, int incarnationLeft) {
		super(x, y, ID, ORC, incarnationLeft);
	}
}
