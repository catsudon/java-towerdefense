package entity.tower;

import help.Constants;

public class Tower {
	
	private int x, y, id, towerType;
	private int atk;
	private float range, cooldown;
	
	private int cooldownTick;
	
	public Tower(int x, int y, int id, int towerType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		this.cooldownTick = 0;

		this.atk = Constants.Towers.getATK(towerType);
		this.range = Constants.Towers.getRange(towerType);
		this.cooldown = Constants.Towers.getCooldown(towerType);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}

	public int getTowerType() {
		return towerType;
	}

	public int getAtk() {
		return atk;
	}

	public float getRange() {
		return range;
	}

	public float getCooldown() {
		return cooldown;
	}

	public boolean isCooldownOver() {
		// TODO Auto-generated method stub
		return cooldownTick >= cooldown;
	}

	public void update() {
		cooldownTick++;
	}

	public void resetCooldown() {
		cooldownTick = 0;
	}

}