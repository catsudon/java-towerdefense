package utilities;

import java.util.ArrayList;

public class Utility {

	/*
	 * Converts an ArrayList of integers to a 2D integer array.
	 */
	public static int[][] arrayListTo2Dint(ArrayList<Integer> list, int ySize, int xSize) {
		int[][] newArr = new int[ySize][xSize];

		for (int j = 0; j < newArr.length; j++) {
			for (int i = 0; i < newArr[j].length; i++) {
				int index = j * ySize + i;
				newArr[j][i] = list.get(index);
			}
		}
		newArr[0][18] = 22;
		newArr[0][19] = 23;
		newArr[1][18] = 24;
		newArr[1][19] = 25;

		return newArr;
	}

	/*
	 * Converts a 2D integer array to a 1D integer array.
	 */
	public static int[] twoDto1DintArr(int[][] twoArr) {
		int[] oneArr = new int[twoArr.length * twoArr[0].length];

		for (int j = 0; j < twoArr.length; j++) {
			for (int i = 0; i < twoArr[j].length; i++) {
				int index = j * twoArr.length + i;
				oneArr[index] = twoArr[j][i];
			}
		}

		return oneArr;
	}
	
	/*
	 * Calculates the Euclidean distance between two points.
	 */
	public static int getEuclideanDistance(float x1, float y1, float x2, float y2) {
		float xDiff = Math.abs(x1 - x2);
		float yDiff = Math.abs(y1 - y2);
		return (int) Math.hypot(xDiff, yDiff);
	}
}
