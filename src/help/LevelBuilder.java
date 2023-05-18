package help;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LevelBuilder {

	public static int[][] getLevelData() {

		int[][] lvlRand = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

		Random rand = new Random();
		int RIGHT = 0, UP = 1, LEFT = 2, DOWN = 3;
		int pre = -1;
		ArrayList<int[]> q = new ArrayList<int[]>();

		int x = 19, y = 0, size = 0;
		
		q.add(new int[] {2, RIGHT}); y+=2;

		while (x > 2 || y < 19) {

			int rng = rand.nextInt(100);

			if (rng % 4 == 0 && y < 19) {
				int dis = Math.min(rand.nextInt(4) + 2, 19 - y);
				q.add(new int[] { dis, RIGHT });
				y += dis;
				pre = RIGHT;
			} else if (rng % 4 == 1 && x > 2) {
				int dis = Math.min(rand.nextInt(4) + 2, x - 2);
				q.add(new int[] { dis, UP });
				x -= dis;
				pre = UP;
			} else if (rng % 8 == 3 && x < 19 && pre == RIGHT) {
				int dis = Math.min(Math.min(rand.nextInt(4) + 2, 19 - y), 19 - x);
				q.add(new int[] { dis, DOWN });
				q.add(new int[] { dis, RIGHT });
				x += dis;
				y += dis;
				pre = RIGHT;
			}
			else
				continue;
		}

		pre = -1;
		x = 19; y = 0;
		for (int[] it : q) {
			System.out.println(Integer.toString(it[0]) + "   " + Integer.toString(it[1]));
			System.out.println(Integer.toString(x) + " ;  " + Integer.toString(y));

			int dis = it[0];
			int dir = it[1];
			if(dir == RIGHT) {
				for(int i=0;i<dis;++i) {
					if(pre == UP)lvlRand[x][y] = 4;
					else if(pre == DOWN) lvlRand[x][y] = 7;
					else lvlRand[x][y] = 2;
					y++;
					pre = RIGHT;
				}
			}
			
			if(dir == UP) {
				for(int i=0;i<dis;++i) {
					if(pre == RIGHT) lvlRand[x][y] = 6;
					else lvlRand[x][y] = 3;
					x--;
					pre = UP;
				}
			}
			
			if(dir == DOWN) {
				for(int i=0;i<dis;++i) {
					if(pre == RIGHT) lvlRand[x][y] = 5;
					else lvlRand[x][y] = 3;
					x++;
					pre = DOWN;
				}
			}
			
		}

		if(lvlRand[2][18] != 0) lvlRand[2][19] = 2;
		if(lvlRand[3][19] != 0) lvlRand[2][19] = 4;
		
		// generate water
		q = new ArrayList<int[]>();
		int waterZone = 7;
		while(waterZone-- > 0) {
			x = rand.nextInt(20);
			y = rand.nextInt(20);
			if(lvlRand[x][y] != 0) continue;
			if(lvlRand[x][y] == 0) {
				size = rand.nextInt(8)+1;
				q.add(new int[] {x,y,size});
			}
		}
		
		for(int[] it : q) {
			x = it[0];
			y = it[1];
			size = it[2];
			
			for(int i=x; i<Math.min(19, x+size); ++i)
			for(int j=y; j<Math.min(19, y+size); ++j)
				if(lvlRand[i][j] == 0) lvlRand[i][j] = 1;
		}
		
		// make corner
		
//		for(int )

		return lvlRand;
	}
}
