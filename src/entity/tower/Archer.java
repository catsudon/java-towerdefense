package entity.tower;

import static utilities.Constants.Towers.ARCHER;
import static utilities.Constants.Towers.getConstantDefaultCooldown;
import static utilities.Constants.Towers.getConstantDefaultRange;
import static utilities.Constants.Towers.getConstantStartDamage;

public class Archer extends Tower {
	
	public Archer(int x, int y, int id) {
		super(x, y, id, ARCHER, getConstantStartDamage(ARCHER), getConstantDefaultRange(ARCHER), getConstantDefaultCooldown(ARCHER));
	}

	@Override
	public String getName() {
		return "Archer";
	}
}
