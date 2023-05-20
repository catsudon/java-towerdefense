package entity.tower;

import static utilities.Constants.Towers.OWNER;
import static utilities.Constants.Towers.getConstantDefaultCooldown;
import static utilities.Constants.Towers.getConstantDefaultRange;
import static utilities.Constants.Towers.getConstantStartDamage;

public class Owner extends Tower {
	
	public Owner(int x, int y, int id) {
		super(x, y, id, OWNER, getConstantStartDamage(OWNER), getConstantDefaultRange(OWNER), getConstantDefaultCooldown(OWNER));
	}

	@Override
	public String getName() {
		return "Owner";
	}
}
