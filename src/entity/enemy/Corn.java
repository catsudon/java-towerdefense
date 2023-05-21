package entity.enemy;

import static utilities.Constants.Enemies.CORN;

public class Corn extends Enemy {
	
	public Corn(float x, float y, int ID, int waveIndex) {
		super(x, y, ID, CORN, waveIndex);
	}
}
