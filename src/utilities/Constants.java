package utilities;

public class Constants {
	
	public static class Projectiles {
		public static final int ARROW = 0;
		public static final int CHAINS = 1;
		public static final int BOMB = 2;

		public static float getConstantSpeed(int type) {
			float tmp = 1f;
			switch (type) {
				case ARROW:
					return tmp * 8f;
				case BOMB:
					return tmp * 4f;
				case CHAINS:
					return tmp * 6f;
			}
			return 0f;
		}
	}

	
	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class Enemies {
		public static final int ORC = 0;
		public static final int BAT = 1;
		public static final int KNIGHT = 2;
		public static final int WOLF = 3;
		
		public static int getConstantReward(int enemyType) {
			switch(enemyType) {
				case ORC:
					return 5;
				case BAT:
					return 5;
				case KNIGHT:
					return 25;
				case WOLF:
					return 10;
			}
			return 0;
		}
		
		public static float getConstantSpeed(int enemyType) {
			int tmp = 8;
			switch(enemyType) {
				case ORC:
					return tmp * 0.25f;
				case BAT:
					return tmp * 0.30f;
				case KNIGHT:
					return tmp * 0.15f;
				case WOLF:
					return tmp * 0.35f;
			}
			return 0;
		}
		
		public static int getConstantStartHealth(int enemyType) {
			switch(enemyType) {
			case ORC:
				return 100;
			case BAT:
				return 60;
			case KNIGHT:
				return 150;
			case WOLF:
				return 85;
		}
		return 0;
		}
	}
	
	public static class Towers {
		public static final int CANNON = 0;
		public static final int ARCHER = 1;
		public static final int WIZARD = 2;
		
		public static int getConstantStartDamage(int towerType) {
			switch (towerType) {
				case CANNON:
					return 300;
				case ARCHER:
					return 10;
				case WIZARD:
					return 0;
			}

			return 0;
		}

		public static float getConstantDefaultRange(int towerType) {
			switch (towerType) {
				case CANNON:
					return 400;
				case ARCHER:
					return 120;
				case WIZARD:
					return 300;
			}

			return 0;
		}

		public static float getConstantDefaultCooldown(int towerType) {
			switch (towerType) {
				case CANNON:
					return 120;
				case ARCHER:
					return 35;
				case WIZARD:
					return 50;
			}

			return 0;
		}

		public static String getConstantTowerName(int towerType) {
			switch (towerType) {
				case CANNON:
					return "Cannon";
				case ARCHER:
					return "Archer";
				case WIZARD:
					return "Wizard";
			}
			return "";
		}
		
		public static int getConstantTowerCost(int towerType) {
			switch (towerType) {
				case CANNON:
					return 65;
				case ARCHER:
					return 30;
				case WIZARD:
					return 45;
			}
			return 0;
		}
		
	}
	
	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}
}
