package entity.enemy;

import static utilities.Constants.Enemies.BAT;

public class Bat extends Enemy {

	public Bat(float x, float y, int ID, int incarnationLeft) {
		super(x, y, ID, BAT, incarnationLeft);
	}
}
