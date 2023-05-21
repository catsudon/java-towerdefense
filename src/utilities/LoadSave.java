package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.image.Image;
import objects.PathPoint;

public class LoadSave {

	/*
	 * Retrieves the map sprite.
	 */
	public static Image getMapSprite() {
		return SpritesHolder.getMapSprite();
	}

	/*
	 * Writes the level data to a file.
	 */
	private static void writeToFile(File f, int[] idArr, PathPoint start, PathPoint end) {
		try {
			PrintWriter pw = new PrintWriter(f);
			for (Integer i : idArr) {
				pw.println(i);
			}
			pw.println(start.getxIndex());
			pw.println(start.getyIndex());
			pw.println(end.getxIndex());
			pw.println(end.getyIndex());

			pw.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Saves the level data.
	 */
	public static void saveLevel(String name, int[][] idArr, PathPoint start, PathPoint end) {
		File levelFile = new File("res/" + name + ".txt");

		if (levelFile.exists()) {
			writeToFile(levelFile, Utility.twoDto1DintArr(idArr), start, end);
		}
		else {
			System.out.println("File: " + name + " does not exist!");
			return;
		}
	}

	/*
	 * Reads level data from a file.
	 */
	private static ArrayList<Integer> readFromFile(File file) {
		ArrayList<Integer> list = new ArrayList<>();

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}

			sc.close();

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * Retrieves the path points for a specific level.
	 */
	public static ArrayList<PathPoint> getLevelPathPoints(String name) {
		File lvlFile = new File("res/" + name + ".txt");

		if (lvlFile.exists()) {
			ArrayList<Integer> list = readFromFile(lvlFile);
			ArrayList<PathPoint> points = new ArrayList<>();
			points.add(new PathPoint(list.get(400), list.get(401)));
			points.add(new PathPoint(list.get(402), list.get(403)));
			return points;
		}
		else {
			System.out.println("File: " + name + " does not exist!");
			return null;
		}
	}

	/*
	 * Retrieves the level data for a specific level.
	 */
	public static int[][] getLevelData(String name) {
		File lvlFile = new File("res/" + name + ".txt");

		if (lvlFile.exists()) {
			ArrayList<Integer> list = readFromFile(lvlFile);
			return utilities.Utility.arrayListTo2Dint(list, 20, 20);

		} else {
			System.out.println("File: " + name + " does not exist!");
			return null;
		}
	}

	/*
	 * Retrieves random level data.
	 */
	public static int[][] getRandomLevelData(String name) {
		saveLevel("new_level", LevelBuilder.getRandomLevelData(), LevelBuilder.getStartPoint(), LevelBuilder.getEndPoint());
		return getLevelData("new_level");
	}
}
