    package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import objects.PathPoint;

public class LevelBuilder {
	
	private static int lvlGrid[][] = new int[20][20];
	
	private static ArrayList<int[]> queue = new ArrayList<int[]>();
	
	private static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	private static int x, y;
	
	private static PathPoint startPoint;
	private static PathPoint endPoint;
	
	private static Random random = new Random();
	
	public static int[][] getRandomLevelData() {
		
		// Initialize lvlGrid
		initializeGrid();
		// Generate random path
		createRandomPath();
		// Assign the correct road sprite into the grid
		assignRoadSprite();
		// Generate Water tiles
		createWaterTiles();
		// Assign the correct water sprite into the grid
		assignWaterSprite();
			
		return lvlGrid;
	}
	
	private static void assignWaterSprite() {
		// Remove water with 3 open side
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				int cnt = 0;
				if(lvlGrid[i][j] != 1) {
					continue;
				}
				if(i > 0 && lvlGrid[i - 1][j] == 0) {
					cnt++;
				}
				if(j > 0 && lvlGrid[i][j - 1] == 0) {
					cnt++;
				}
				if(i < 19 && lvlGrid[i + 1][j] == 0) {
					cnt++;
				}
				if(i < 19 && lvlGrid[i][j + 1] == 0) {
					cnt++;
				}
				if(cnt >= 3) {
					lvlGrid[i][j] = 0;
				}
			}
		}
		
		// Make lines
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if(lvlGrid[i][j] == 1 && j > 0 && lvlGrid[i][j - 1] == 0) {
					lvlGrid[i][j] = 15;
				}
				else if(lvlGrid[i][j] == 1 && j < 19 && lvlGrid[i][j + 1] == 0) {
					lvlGrid[i][j] = 13;
				}
				else if(lvlGrid[i][j] == 1 && i > 0 && lvlGrid[i - 1][j] == 0) {
					lvlGrid[i][j] = 12;
				}
				else if(lvlGrid[i][j] == 1 && i < 19 && lvlGrid[i + 1][j] == 0) {
					lvlGrid[i][j] = 14;
				}
			}
		}
		
		// Fix double line	
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if(lvlGrid[i][j] == 12) {
					if (j > 0 && lvlGrid[i][j - 1] == 0) {
						lvlGrid[i][j] = 9;
					}
					else if (j < 19 && lvlGrid[i][j + 1] == 0) {
						lvlGrid[i][j] = 10;
					}
				}
				else if(lvlGrid[i][j] == 13) {
					if (i > 0 && lvlGrid[i - 1][j] == 0) {
						lvlGrid[i][j] = 10;
					}
					else if (i < 19 && lvlGrid[i + 1][j] == 0) {
						lvlGrid[i][j] = 11;
					}
				}
				else if(lvlGrid[i][j] == 14) {
					if (j > 0 && lvlGrid[i][j - 1] == 0) {
						lvlGrid[i][j] = 8;
					}
					else if (j < 19 && lvlGrid[i][j + 1] == 0) {
						lvlGrid[i][j] = 11;
					}
				}
				else if(lvlGrid[i][j] == 15) {
					if (i > 0 && lvlGrid[i - 1][j] == 0) {
						lvlGrid[i][j] = 9;
					}
					else if (i < 19 && lvlGrid[i + 1][j] == 0) {
						lvlGrid[i][j] = 8;
					}
				}
			}
		}
	}

	private static void initializeGrid() {
		for(int y = 0; y < 20; y++) {
			for(int x = 0; x < 20; x++) {
				lvlGrid[y][x] = 0;
			}
		}
	}

	private static void createWaterTiles() {
		queue.clear();

		int x, y;
		int waterZone = 20;
		
		while(waterZone-- > 0) {
			x = random.nextInt(20);
			y = random.nextInt(20);
			if(lvlGrid[y][x] == 0) {
				int sz = random.nextInt(6)+2;
				queue.add(new int[] {x,y,sz});
			}
		}
		
		for(int[] it : queue) {
			x = it[0];
			y = it[1];
			int sz = it[2];
			
			for(int i=x; i<Math.min(19, x+sz); ++i)
			for(int j=y; j<Math.min(19, y+sz); ++j)
				if(lvlGrid[i][j] == 0) lvlGrid[i][j] = 1;
		}
	}

	private static void assignRoadSprite() {
		int lastDir = -1;
		
		for (int[] it : queue) {
			int dis = it[0];
			int dir = it[1];
			
			if(dir == UP) {
				for(int i = 0; i < dis; i++) {
					if(lastDir == RIGHT) {
						lvlGrid[y][x] = 6;
					}
					else if(lastDir == LEFT) {
						lvlGrid[y][x] = 7;
					}
					else {
						lvlGrid[y][x] = 3;
					}
					y--;
					lastDir = UP;
				}
			}
			else if(dir == RIGHT) {
				for(int i = 0; i < dis; i++) {
					if(lastDir == UP) {
						lvlGrid[y][x] = 4;
					}
					else if(lastDir == DOWN) {
						lvlGrid[y][x] = 7;
					}
					else {
						lvlGrid[y][x] = 2;
					}
					x++;
					lastDir = RIGHT;
				}
			}
			else if(dir == DOWN) {
				for(int i = 0; i < dis; i++) {
					if(lastDir == RIGHT) {
						lvlGrid[y][x] = 5;
					}
					else if(lastDir == LEFT) {
						lvlGrid[y][x] = 4;
					}
					else {
						lvlGrid[y][x] = 3;
					}
					y++;
					lastDir = DOWN;
				}
			}
			else if(dir == LEFT) {
				for(int i = 0; i < dis; i++) {
					if(lastDir == UP) {
						lvlGrid[y][x] = 5;
					}
					else if(lastDir == DOWN) {
						lvlGrid[y][x] = 6;
					}
					else {
						lvlGrid[y][x] = 2;
					}
					x--;
					lastDir = LEFT;
				}
			}		
		}
	}

	private static void createRandomPath() {
		x = 18;
		y = 1;
		while(y < 17) {
			int targetX, targetY;
			
			targetX = x;
			while(Math.abs(targetX - x) <= 3) {
				if(y > 1) {
					targetX = random.nextInt(19);
				}
				else {
					targetX = random.nextInt(16);
				}
			}
			if(x <= targetX) {
				queue.add(new int[]{targetX - x, LEFT});
			}
			else {
				queue.add(new int[]{x - targetX, RIGHT});
			}
			x = targetX;
			
			targetY = y;
			while(Math.abs(targetY - y) <= 1 || y % 2 != targetY % 2) {
				targetY = y + random.nextInt(Math.min(18 - y, 4));
			}
			queue.add(new int[] {targetY - y, UP});
			y = targetY;
		}
		
		queue.add(new int[] {19 - y, UP});
		
		if(x <= 9) {
			queue.add(new int[]{19 - x, LEFT});
			x = 19;
			y = 19;
		}
		else {
			queue.add(new int[]{x - 0, RIGHT});
			x = 0;
			y = 19;
		}
		startPoint = new PathPoint(x, y);
		endPoint = new PathPoint(17, 1);
		
		Collections.reverse(queue);
	}

	public static PathPoint getStartPoint() {
		return startPoint;
	}

	public static PathPoint getEndPoint() {
		return endPoint;
	}

}
