package entity.enemy;

import static utilities.Constants.Enemies.TOMATO;

public class Tomato extends Enemy {

	public Tomato(float x, float y, int ID, int waveIndex) {
		super(x, y, ID, TOMATO, waveIndex);
	}
}
