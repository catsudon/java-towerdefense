package entity.tower;

import static utilities.Constants.Towers.*;

import entity.enemy.Enemy;
import managers.ProjectileManager;

public class Princess extends Tower {

	public Princess(int x, int y, int id) {
		super(x, y, id, PRINCESS, getConstantStartDamage(PRINCESS), getConstantDefaultRange(PRINCESS), getConstantDefaultCooldown(PRINCESS));
	}

	@Override
	public String getName() {
		return "Princess";
	}

	@Override
	public void attack(ProjectileManager pm, Enemy e) {
		pm.newProjectile(this, e, 1);
		if(this.getTier() >= 2) {
			perfromUltimate(pm, e);
		}
	}

	@Override
	public void perfromUltimate(ProjectileManager pm, Enemy e) {
		pm.princessUltimate(this);
	}
	

}
