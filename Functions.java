package com.gradescope.garden;

public class Functions {
	
	public static void grid(int row, int col, char c, int rate, String className, char arr[][]) {
		// Determine the initial position for growth
		int startRow = row * 5;
		int startCol = col * 5;

		if (className.equalsIgnoreCase("tree")) {
			for (int i = 4; i >= 5 - rate; i--) { // Fill from the bottom upwards based on the rate
				if (startRow + i < arr.length) {
					arr[startRow + i][startCol + 2] = c; // Plant grows upwards in the middle
				}
			}
		} else if (className.equalsIgnoreCase("flower")) {

			if (rate == 1) {
				arr[startRow + 2][startCol + 2] = c;
				return;
			}
			if (rate == 2) {
				for (int i = startRow; i < startRow + 5; i++) {
					if (i - startRow == 1 || i - startRow == 3) {
						arr[i][startCol + 2] = c;
					} else if (i - startRow == 2) {
						for (int j = startCol + 1; j <= startCol + 3; j++) {
							arr[i][j] = c;
						}
					}

				}
			} else if (rate == 3) {
				int nu = 1;
				for (int i = startRow; i < startRow + 5; i++) {

					if (i - startRow == 0 || i - startRow == 4) {
						arr[i][startCol + 2] = c;
					} else if (i - startRow == 1 || i - startRow == 3) {
						arr[i][startCol + 1] = c;
						arr[i][startCol + 2] = c;
						arr[i][startCol + 3] = c;
					} else {
						for (int j = startCol; j < startCol + 5; j++) {
							arr[i][j] = c;
						}
					}

				}
			} else if (rate == 4) {
				for (int i = startRow; i < startRow + 5; i++) {
					for (int j = startCol; j < startCol + 5; j++) {
						arr[i][j] = c;
					}
				}

				arr[startRow][startCol] = '.';
				arr[startRow][startCol + 4] = '.';
				arr[startRow + 4][startCol] = '.';
				arr[startRow + 4][startCol + 4] = '.';

			} else if (rate >= 5) {
				for (int i = startRow; i < startRow + 5; i++) {
					for (int j = startCol; j < startCol + 5; j++) {
						arr[i][j] = c;
					}
				}
			}

		} else if (className.equalsIgnoreCase("fruit")) {
			for (int i = 0; i < rate; i++) {
				if (startRow + 4 - i < arr.length) {
					arr[startRow + 4 - i][startCol + 2] = c; // Plant grows downwards in the middle
				}
			}
		} else if (className.equalsIgnoreCase("vegetable")) {
			for (int i = 0; i < rate; i++) {
				if (startRow + i < arr.length) {
					arr[startRow + i][startCol + 2] = c; // Plant grows downwards in the middle
				}
			}
		}
	}

	
	public static void removeFunction(String typeOrClass,int rows, int cols,Plant land[][])
	{
		if (typeOrClass.equalsIgnoreCase("cut")) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (land[i][j] == null)
						continue;
					if (land[i][j].getClass().getName().split("\\.")[3].equalsIgnoreCase("Tree")) {
						((Tree) land[i][j]).cut();
						land[i][j] = null;
					}
				}
			}
		} else if (typeOrClass.equalsIgnoreCase("pick")) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (land[i][j] == null)
						continue;
					if (land[i][j].getClass().getName().split("\\.")[3].equalsIgnoreCase("Flower")) {
						((Flower) land[i][j]).pick();
						land[i][j] = null;
					}
				}
			}
		} else if (typeOrClass.equalsIgnoreCase("ripe")) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (land[i][j] == null)
						continue;
					if (land[i][j].getClass().getName().split("\\.")[3].equalsIgnoreCase(typeOrClass)) {
						((Fruit) land[i][j]).ripe();
						land[i][j] = null;
					}
				}
			}
		} else if (typeOrClass.equalsIgnoreCase("harvest")) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (land[i][j] == null)
						continue;
					if (land[i][j].getClass().getName().split("\\.")[3].equalsIgnoreCase("Vegetable")) {
						((Vegetable) land[i][j]).harvest();
						land[i][j] = null;
					}
				}
			}
		}

		else {

			String className = "";
			if (RunGarden.vegetables.contains(typeOrClass)) {
				className = "Vegetable";
			} else if (RunGarden.fruits.contains(typeOrClass)) {
				className = "Fruit";
			} else if (RunGarden.flowers.contains(typeOrClass)) {
				className = "Flower";
			} else if (RunGarden.trees.contains(typeOrClass)) {
				className = "Tree";
			}

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (land[i][j] == null)
						continue;
					if (land[i][j].getType().equalsIgnoreCase(typeOrClass)) {

						if (className.equalsIgnoreCase("tree")) {
							((Tree) land[i][j]).cut();

						} else if (className.equalsIgnoreCase("flower")) {
							((Flower) land[i][j]).pick();

						} else if (className.equalsIgnoreCase("fruit")) {
							((Fruit) land[i][j]).ripe();

						} else if (className.equalsIgnoreCase("vegetable")) {
							((Vegetable) land[i][j]).harvest();

						}
						land[i][j] = null;

					}
				}
			}

		}

	}

}