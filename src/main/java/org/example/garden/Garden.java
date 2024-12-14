package org.example.garden;

import javafx.scene.paint.Color;

public class Garden {

	private int rows;
	private int cols;
	private Plant[][] land;
	private String name;

	/**
	 * @return the rows of the garden
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the cols of the garden
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @return the 2D array representing the land (garden)
	 */
	public Plant[][] getLand() {
		return land;
	}

	/**
	 * @return the name of the garden
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the number of rows in the garden.
	 * @param rows the number of rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Set the number of columns in the garden.
	 * @param cols the number of columns
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * Initializes the garden's land (2D array of plants) with the given number of rows and columns.
	 * @param cols number of columns
	 * @param rows number of rows
	 */
	public void setLand(int cols, int rows) {
		this.land = new Plant[cols][rows];
	}

	/**
	 * Set the name of the garden.
	 * @param name the name of the garden
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Plants a specified type of plant at a given location in the garden.
	 * @param row The row index where the plant should be planted.
	 * @param col The column index where the plant should be planted.
	 * @param type The type of plant to be planted.
	 */
	public void plantGarden(int row, int col, String type) {
		if (row >= rows || col >= cols || land[row][col] != null) {
			System.out.println("Can't plant there.");
		} else {
			// Check the type of plant and add the appropriate plant at the specified position
			if (GardenDatabase.getVegetables().contains(type)) {
				land[row][col] = new Vegetable(type, 0, Color.GREEN, 1, Health.GOOD, false);
			} else if (GardenDatabase.getFruits().contains(type)) {
				land[row][col] = new Fruit(type, 0, Color.GREEN, 1, Health.GOOD, false, 0);
			} else if (GardenDatabase.getFlowers().contains(type)) {
				land[row][col] = new Flower(type, 0, Color.ORANGE, 1, Health.GOOD, "0", false);
			} else if (GardenDatabase.getTrees().contains(type)) {
				land[row][col] = new Tree(type, 0, Color.BLACK, 1, Health.GOOD, 0, true, false);
			}
		}
	}

	/**
	 * Creates a visual representation of the garden as a 2D array of colors.
	 * @return a 2D array representing the garden with colors based on plant types.
	 */
	public Color [][] createGarden() {
		Color[][] arr = new Color[(rows) * 5][cols * 5]; // 2D character array to represent the garden

		// Fill the garden with plants according to their types
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (land[i][j] != null) {
					int growthRate = land[i][j].getGrowthRate(); // Get the growth rate of the plant
					land[i][j].draw(i, j, growthRate, arr); // Draw the plant's representation
					land[i][j].setGrowthRate(growthRate + 1); // Update growth rate
				}
			}
		}
		return arr;
	}

	/**
	 * Grows all plants in the garden by a specified amount.
	 * @param num The amount to grow all plants.
	 */
	public void growAll(int num) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (land[i][j] != null) {
					land[i][j].setGrowthRate(land[i][j].getGrowthRate() + num); // Grow each plant
				}
			}
		}
	}

	/**
	 * Grows the plant at a specific location by a specified amount.
	 * @param num The amount to grow the plant.
	 * @param x The row index of the plant.
	 * @param y The column index of the plant.
	 */
	public void growAtPosition(int num, int x, int y) {
		if (x < 0 || x >= rows || y < 0 || y >= cols || land[x][y] == null) {
			System.out.println("Can't grow there.\n");
		} else {
			land[x][y].setGrowthRate(land[x][y].getGrowthRate() + num); // Grow the plant at the specified position
		}
	}

	/**
	 * Grows all plants of a specified type by a given amount.
	 * @param num The amount to grow each plant of the specified type.
	 * @param type The type of plants to grow.
	 */
	public void growByType(int num, String type) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (land[i][j] != null && land[i][j].getType() == Plant.PlantType.getName(type)) {
					land[i][j].setGrowthRate(land[i][j].getGrowthRate() + num); // Grow plants of specific type
				}
			}
		}
	}

	/**
	 * Grows all plants of a specific class (e.g., flower, vegetable, tree) by a given amount.
	 * @param num The amount to grow each plant of the specified class.
	 * @param plantClass The class of plants to grow.
	 */
	public void growByClass(int num, String plantClass) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (land[i][j] != null
						&& land[i][j].getClass().getName().split("\\.")[3].equalsIgnoreCase(plantClass)) {
					land[i][j].setGrowthRate(land[i][j].getGrowthRate() + num); // Grow plants of specific class
				}
			}
		}
	}

	/**
	 * Removes all plants of a specified type or class from the garden.
	 * @param typeOrClass The type or class of plants to remove.
	 */
	public void remove(String typeOrClass) {
		Plant.removePlant(typeOrClass, rows, cols, land); // Remove plants of the specified type/class
	}

	/**
	 * Removes a plant at a specific location based on the action verb (e.g., "cut", "pick", "ripe", "harvest").
	 * @param row The row index of the plant.
	 * @param col The column index of the plant.
	 * @param verb The action verb (e.g., "cut", "pick", "ripe", "harvest").
	 */
	public void remove(int row, int col, String verb) {
		if(land[row][col] == null) return;
		Plant.PlantType type = land[row][col].getType();
		if (type == Plant.PlantType.TREE) {
			((Tree) land[row][col]).cut(); // Cut tree
			land[row][col] = null;
		} else if (type == Plant.PlantType.FLOWER) {
			((Flower) land[row][col]).pick(); // Pick flower
			land[row][col] = null;
		} else if (type == Plant.PlantType.FRUIT) {
			((Fruit) land[row][col]).ripe(); // Ripe fruit
			land[row][col] = null;
		} else if (type == Plant.PlantType.VEGETABLE) {
			((Vegetable) land[row][col]).harvest(); // Harvest vegetable
			land[row][col] = null;
		} else {
			System.out.println("Can't " + verb + " there.\n");
		}
	}
}
