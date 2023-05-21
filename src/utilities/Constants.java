package utilities;

public class Constants {
	/*
	 *  a class which determines projectiles id.
	 */
	public static class Projectiles {
		/*
		 * croissant projectile id.
		 */
		public static final int CROISSANT = 0;
		/*
		 * purple macarons projectile id.
		 */
		public static final int MACARONS = 1;
		/*
		 * cupcake projectile id.
		 */
		public static final int CUPCAKE = 2;
		/*
		 * blue macarons projectile id.
		 */
		public static final int BLUEMACARONS = 3;
		/*
		 * yellow macarons projectile id.
		 */
		public static final int YELLOWMACARONS = 4;
		
		/*
		 *  a function for getting speed of each projectiles.
		 */
		public static float getConstantSpeed(int type) {
			switch (type) {
				case CUPCAKE:
					return 7f;
				case CROISSANT:
					return 8f;
			}
			return 0f;
		}
	}

	/*
	 *  a class for determining direction.
	 */
	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	/*
	 *  a class for determining enemy id.
	 */
	public static class Enemies {
		public static final int CORN = 0;
		public static final int TOMATO = 1;
		public static final int CABBAGE = 2;
		public static final int ONION = 3;

		/*
		 * a function determining rewards from killing an enemy.
		 */
		public static int getConstantReward(int enemyType) {
			switch (enemyType) {
			case CORN:
				return 5;
			case TOMATO:
				return 5;
			case CABBAGE:
				return 25;
			case ONION:
				return 10;
			}
			return 0;
		}

		/*
		 *  a function determining enemy speed.
		 */
		public static float getConstantSpeed(int enemyType) {
			switch (enemyType) {
			case CORN:
				return 1.2f;
			case TOMATO:
				return 1.6f;
			case CABBAGE:
				return 2.4f;
			case ONION:
				return 2.8f;
			}
			return 0;
		}

		/*
		 *  a function determining enemy start health.
		 */
		public static int getConstantStartHealth(int enemyType) {
			switch (enemyType) {
			case CORN:
				return 300;
			case TOMATO:
				return 500;
			case CABBAGE:
				return 650;
			case ONION:
				return 850;
			}
			return 0;
		}
	}

	/*
	 *  a class determining towers.
	 */
	public static class Towers {
		public static final int PRINCESS = 0;
		public static final int CHEF = 1;
		public static final int OWNER = 2;

		/*
		 *  a function determining tower's start damage.
		 */
		public static int getConstantStartDamage(int towerType) {
			switch (towerType) {
			case PRINCESS:
				return 80;
			case CHEF:
				return 30;
			case OWNER:
				return 1;
			}

			return 0;
		}

		/*
		 *  a function determining tower's start range.
		 */
		public static float getConstantDefaultRange(int towerType) {
			switch (towerType) {
			case PRINCESS:
				return 300;
			case CHEF:
				return 300;
			case OWNER:
				return 300;
			}

			return 0;
		}

				/*
		 *  a function determining tower's start cooldown.
		 */
		public static float getConstantDefaultCooldown(int towerType) {
			switch (towerType) {
			case PRINCESS:
				return 100;
			case CHEF:
				return 50;
			case OWNER:
				return 5 * 60;
			}

			return 0;
		}

		/*
		 *  a function for getting towers name.
		 */
		public static String getConstantTowerName(int towerType) {
			switch (towerType) {
			case PRINCESS:
				return "Princess";
			case CHEF:
				return "Chef";
			case OWNER:
				return "Owner";
			}
			return "";
		}

		/*
		 *  a function for getting tower cost.
		 */
		public static int getConstantTowerCost(int towerType) {
			switch (towerType) {
			case PRINCESS:
				return 60;
			case CHEF:
				return 40;
			case OWNER:
				return 800;
			}
			return 0;
		}

	}

	/*
	 *  a class for determining tiles types.
	 */
	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}
}
