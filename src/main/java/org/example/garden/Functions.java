package org.example.garden;

import javafx.scene.paint.Color;


public class Functions {
	
	public static void grid(int row, int col, char c, int rate, String className, Color arr[][]) {
		// Determine the initial position for growth
		//c='*';
		int startRow = row * 5;
		int startCol = col * 5;

		if (className.equalsIgnoreCase("tree")) {

			for (int i = startRow+4; i >= startRow; i--) {
				for (int j = startCol; j < startCol+5; j++) {
					if(j==startCol+2 && rate>0)
					{
						arr[i][j]=Color.BLACK;
						rate--;
					}
				}
			}

		} else if (className.equalsIgnoreCase("flower")) {

			if (rate == 1) {
				arr[startRow + 2][startCol + 2] = Color.RED;
				return;
			}
			if (rate == 2) {
				for (int i = startRow; i < startRow + 5; i++) {
					if (i - startRow == 1 || i - startRow == 3) {
						arr[i][startCol + 2] = Color.RED;
					} else if (i - startRow == 2) {
						for (int j = startCol + 1; j <= startCol + 3; j++) {
							arr[i][j] = Color.RED;
						}
					}

				}
			} else if (rate == 3) {
				int nu = 1;
				for (int i = startRow; i < startRow + 5; i++) {

					if (i - startRow == 0 || i - startRow == 4) {
						arr[i][startCol + 2] = Color.RED;
					} else if (i - startRow == 1 || i - startRow == 3) {
						arr[i][startCol + 1] = Color.RED;
						arr[i][startCol + 2] = Color.RED;
						arr[i][startCol + 3] = Color.RED;
					} else {
						for (int j = startCol; j < startCol + 5; j++) {
							arr[i][j] = Color.RED;
						}
					}

				}
			} else if (rate == 4) {
				for (int i = startRow; i < startRow + 5; i++) {
					for (int j = startCol; j < startCol + 5; j++) {
						arr[i][j] = Color.RED;
					}
				}

			} else if (rate >= 5) {
				for (int i = startRow; i < startRow + 5; i++) {
					for (int j = startCol; j < startCol + 5; j++) {
						arr[i][j] = Color.RED;;
					}
				}
			}

		} else if (className.equalsIgnoreCase("fruit")) {
			for (int i = 0; i < rate; i++) {
				if (startRow + 4 - i < arr.length) {
					arr[startRow + 4 - i][startCol + 2] = Color.ORANGE; // Plant grows downwards in the middle
				}
			}
		} else if (className.equalsIgnoreCase("vegetable")) {
			for (int i = 0; i < rate; i++) {
				if (startRow + i < arr.length) {
					arr[startRow + i][startCol + 2] = Color.GREEN; // Plant grows downwards in the middle
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
			if (GardenDatabase.getVegetables().contains(typeOrClass)) {
				className = "Vegetable";
			} else if (GardenDatabase.getFruits().contains(typeOrClass)) {
				className = "Fruit";
			} else if (GardenDatabase.getFlowers().contains(typeOrClass)) {
				className = "Flower";
			} else if (GardenDatabase.getTrees().contains(typeOrClass)) {
				className = "Tree";
			}

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (land[i][j] == null)
						continue;
					Plant.PlantType type=Plant.PlantType.getName(className);
						if (type== Plant.PlantType.TREE) {
							((Tree) land[i][j]).cut();
						} else if (type == Plant.PlantType.FLOWER) {
							((Flower) land[i][j]).pick();
						} else if (type== Plant.PlantType.FRUIT) {
							((Fruit) land[i][j]).ripe();
						} else if (type== Plant.PlantType.VEGETABLE) {
							((Vegetable) land[i][j]).harvest();
						}
						land[i][j] = null;

				}
			}

		}

	}

}