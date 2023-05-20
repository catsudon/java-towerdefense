package entity.tower;

import static utilities.Constants.Towers.WIZARD;
import static utilities.Constants.Towers.getConstantDefaultCooldown;
import static utilities.Constants.Towers.getConstantDefaultRange;
import static utilities.Constants.Towers.getConstantStartDamage;

public class Wizard extends Tower {
	
	public Wizard(int x, int y, int id) {
		super(x, y, id, WIZARD, getConstantStartDamage(WIZARD), getConstantDefaultRange(WIZARD), getConstantDefaultCooldown(WIZARD));
	}

	@Override
	public String getName() {
		return "Wizard";
	}
}
