package entity.tower;

import static utilities.Constants.Towers.*;

public class Cannon extends Tower {

	public Cannon(int x, int y, int id) {
		super(x, y, id, CANNON, getConstantStartDamage(CANNON), getConstantDefaultRange(CANNON), getConstantDefaultCooldown(CANNON));
	}

	@Override
	public String getName() {
		return "Cannon";
	}
	

}
