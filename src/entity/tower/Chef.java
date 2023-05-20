package entity.tower;

import static utilities.Constants.Towers.CHEF;
import static utilities.Constants.Towers.getConstantDefaultCooldown;
import static utilities.Constants.Towers.getConstantDefaultRange;
import static utilities.Constants.Towers.getConstantStartDamage;

public class Chef extends Tower {
	
	public Chef(int x, int y, int id) {
		super(x, y, id, CHEF, getConstantStartDamage(CHEF), getConstantDefaultRange(CHEF), getConstantDefaultCooldown(CHEF));
	}

	@Override
	public String getName() {
		return "Chef";
	}
}
