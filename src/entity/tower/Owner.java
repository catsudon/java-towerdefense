package entity.tower;

import static utilities.Constants.Towers.OWNER;
import static utilities.Constants.Towers.getConstantDefaultCooldown;
import static utilities.Constants.Towers.getConstantDefaultRange;
import static utilities.Constants.Towers.getConstantStartDamage;

import entity.enemy.Enemy;
import managers.ProjectileManager;

public class Owner extends Tower {
	
	public Owner(int x, int y, int id) {
		super(x, y, id, OWNER, getConstantStartDamage(OWNER), getConstantDefaultRange(OWNER), getConstantDefaultCooldown(OWNER));
	}

	@Override
	public String getName() {
		return "Owner";
	}

	@Override
	public void attack(ProjectileManager pm, Enemy e) {
		perfromUltimate(pm, e);
	}

	@Override
	public void perfromUltimate(ProjectileManager pm, Enemy e) {
		pm.ownerUltimate(this);
	}
}
