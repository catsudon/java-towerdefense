package entity.tower;

import static utilities.Constants.Towers.CHEF;
import static utilities.Constants.Towers.getConstantDefaultCooldown;
import static utilities.Constants.Towers.getConstantDefaultRange;
import static utilities.Constants.Towers.getConstantStartDamage;

import entity.enemy.Enemy;
import managers.ProjectileManager;

public class Chef extends Tower {
	
	public Chef(int x, int y, int id) {
		super(x, y, id, CHEF, getConstantStartDamage(CHEF), getConstantDefaultRange(CHEF), getConstantDefaultCooldown(CHEF));
	}

	@Override
	public String getName() {
		return "Chef";
	}

	@Override
	public void attack(ProjectileManager pm, Enemy e) {
		pm.newProjectile(this, e, 1);
		if(this.getTier() >= 2 && isUltimateCooldownOver()) {
			perfromUltimate(pm, e);
			resetUltimateCooldown();
		}
	}
	
	@Override
	public void perfromUltimate(ProjectileManager pm, Enemy e) {
		for(int i = 1; i <= 3; i++) {
			pm.newProjectile(this, e, (float)(1 - 0.1 * i));
		}
	}
}
