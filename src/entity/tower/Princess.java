package entity.tower;

import static utilities.Constants.Towers.*;

public class Princess extends Tower {

	public Princess(int x, int y, int id) {
		super(x, y, id, PRINCESS, getConstantStartDamage(PRINCESS), getConstantDefaultRange(PRINCESS), getConstantDefaultCooldown(PRINCESS));
	}

	@Override
	public String getName() {
		return "Princess";
	}
	

}
