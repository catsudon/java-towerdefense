package help;

public class Constants {
	
	public static class Projectiles {
		public static final int ARROW = 0;
		public static final int CHAINS = 1;
		public static final int BOMB = 2;
		
		public static float getSpeed(int projectileType) {
			switch(projectileType) {
				case ARROW:
					return 3f;
				case CHAINS:
					return 2f;
				case BOMB:
					return 1f;
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
		
		public static float getSpeed(int enemyType) {
			switch(enemyType) {
				case ORC:
					return 0.5f;
				case BAT:
					return 0.65f;
				case KNIGHT:
					return 0.3f;
				case WOLF:
					return 0.75f;
			}
			return 0;
		}
		
		public static int getStartHealth(int enemyType) {
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
	}
	
	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}
}
