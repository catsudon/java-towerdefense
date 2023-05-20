package entity.tower;

import static utilities.Constants.Towers.*;

public abstract class Tower {
	
	private int x, y, id, towerType;
	private int atk;
	private float range, cooldown;
	
	private int cooldownTick;
	private int tier;
	private int animationStatus = 0;
	
	public Tower(int x, int y, int id, int towerType, int atk, float range, float cooldown) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		this.cooldownTick = 0;

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
		return cooldownTick >= cooldown;
	}

	public void update() {
		cooldownTick++;
	}

	public void resetCooldown() {
		cooldownTick = 0;
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