package entity.enemy;

import static utilities.Constants.Enemies.WOLF;

public class Wolf extends Enemy {

	public Wolf(float x, float y, int ID, int incarnationLeft) {
		super(x, y, ID, WOLF, incarnationLeft);
	}
}
