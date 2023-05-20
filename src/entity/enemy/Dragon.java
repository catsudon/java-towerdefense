package entity.enemy;

import static utilities.Constants.Enemies.DRAGON;

public class Dragon extends Enemy {

	public Dragon(float x, float y, int ID, int waveIndex) {
		super(x, y, ID, DRAGON, waveIndex);
	}
}
