package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.Utilities;

import javafx.scene.image.Image;
import objects.PathPoint;
import sharedObject.RenderableHolder;

public class LoadSave {

	public static Image getSpriteAtlas() {
		return RenderableHolder.mapSprite;
	}

	public static void CreateFile() {
		File txtFile = new File("res/testTextFile.txt");

		try {
			txtFile.createNewFile();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void CreateLevel(String name, int[] idArr) {
		File newLevel = new File("res/" + name + ".txt");
		if (newLevel.exists()) {
			System.out.println("File: " + name + " already exists!");
			return;
		}
		else {
			try {
				newLevel.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			WriteToFile(newLevel, idArr, new PathPoint(0, 0), new PathPoint(0, 0));
		}

	}

	private static void WriteToFile(File f, int[] idArr, PathPoint start, PathPoint end) {
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

	public static void SaveLevel(String name, int[][] idArr, PathPoint start, PathPoint end) {
		File levelFile = new File("res/" + name + ".txt");

		if (levelFile.exists()) {
			WriteToFile(levelFile, Utility.TwoDto1DintArr(idArr), start, end);
		}
		else {
			System.out.println("File: " + name + " does not exists! ");
			return;
		}
	}

	private static ArrayList<Integer> ReadFromFile(File file) {
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

	public static ArrayList<PathPoint> getLevelPathPoints(String name) {
		File lvlFile = new File("res/" + name + ".txt");

		if (lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile(lvlFile);
			ArrayList<PathPoint> points = new ArrayList<>();
			points.add(new PathPoint(list.get(400), list.get(401)));
			points.add(new PathPoint(list.get(402), list.get(403)));
			return points;
		}
		else {
			System.out.println("File: " + name + " does not exists! ");
			return null;
		}
	}
	
	public static int[][] GetLevelData(String name) {
		File lvlFile = new File("res/" + name + ".txt");

		if (lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile(lvlFile);
			return utilities.Utility.ArrayListTo2Dint(list, 20, 20);

		} else {
			System.out.println("File: " + name + " does not exists! ");
			return null;
		}
	}

	public static int[][] GetRandomLevelData(String name) {
		SaveLevel("new_level", LevelBuilder.getRandomLevelData(), LevelBuilder.getStartPoint(), LevelBuilder.getEndPoint());
		return GetLevelData("new_level");
	}
}