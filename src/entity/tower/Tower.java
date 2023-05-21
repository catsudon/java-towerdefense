package entity.tower;

import static utilities.Constants.Towers.*;

import entity.enemy.Enemy;
import managers.ProjectileManager;

public abstract class Tower {
	
	private int x, y, id, towerType;
	private int atk;
	private float range, cooldown;
	
	private int cooldownTick;
	private int tier;
	private int animationStatus = 0;
	private int ultimateCooldownTick;
	
	public Tower(int x, int y, int id, int towerType, int atk, float range, float cooldown) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		this.cooldownTick = (int)cooldown - 60;
		this.ultimateCooldownTick = 5 * 60;

		this.atk = atk;
		this.range = range;
		this.cooldown = cooldown;
		
		this.tier = 1;
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
		return cooldownTick >= this.cooldown;
	}
	

	public boolean isUltimateCooldownOver() {
		// TODO Auto-generated method stub
		return ultimateCooldownTick >= 5 * 60;
	}

	public void update() {
		cooldownTick++;
		ultimateCooldownTick++;
	}

	public void resetCooldown() {
		cooldownTick = 0;
	}
	
	public void resetUltimateCooldown() {
		ultimateCooldownTick = 0;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	public void setId(int newId) {
		id = newId;
	}
	
	public abstract String getName();
	
	public abstract void attack(ProjectileManager pm, Enemy e);
	
	public abstract void perfromUltimate(ProjectileManager pm, Enemy e);

	public int getCost() {
		return getConstantTowerCost(towerType);
	}

	public int getUpgradeCost() {
		return (int)(getConstantTowerCost(towerType) * 0.3f);
	}
	
	public int getSellPrice() {
		int upgradedPrice = (getTier() - 1) * getUpgradeCost();
		return (getConstantTowerCost(towerType) / 2) + upgradedPrice;
	}

	public void upgradeTower() {
		this.tier++;
		
		switch(towerType) {
			case PRINCESS:
				this.atk += 5;
				this.range += 20;
				this.cooldown -= 15;
				break;
			case CHEF:
				this.atk += 2;
				this.range += 20;
				this.cooldown -= 5;
				break;
			case OWNER:
				this.range += 20;
				this.cooldown -= 10;
				break;
		}
	}
	
	public int getTier() {
		return tier;
	}

	public int getAnimationStatus() {
		return animationStatus;
	}

	public void toggleAnimationStatus() {
		if(animationStatus == 1) animationStatus = 0;
		else animationStatus = 1;
	}
}