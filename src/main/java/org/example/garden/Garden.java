package org.example.garden;


import javafx.scene.paint.Color;

public class Garden {

	private int rows;
	private int cols;
	private Plant[][] land;
	private String name;

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @return the land
	 */
	public Plant[][] getLand() {
		return land;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @param cols the cols to set
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**

	 */
	public void setLand(int cols, int rows) {

		this.land = new Plant[cols][rows];

	}

	/**
	 * @param name the name to set
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

			if (RunGarden.vegetables.contains(type)) {
				land[row][col] = new Vegetable(type, 0,Color.GREEN, 1, Health.Good, false);
			} else if (RunGarden.fruits.contains(type)) {
				land[row][col] = new Fruit(type, 0, Color.GREEN, 1, Health.Good, false, 0);

			} else if (RunGarden.flowers.contains(type)) {
				land[row][col] = new Flower(type, 0,Color.ORANGE, 1, Health.Good, "0", false);

			} else if (RunGarden.trees.contains(type)) {
				land[row][col] = new Tree(type, 0, Color.BLACK, 1, Health.Good, 0, true, false);
			}
			 // Set the type of the newly planted plant

		}

	}
	 /**
     * Prints the current state of the garden.
     */

	public Color [][] PrintGarden() {
		Color[][] arr = new Color[(rows) * 5][cols * 5];// 2D character array to represent the garden

		// Initialize the garden grid with empty spaces

		// Fill the garden with plants according to their types
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (land[i][j] != null) {

					char plantChar = land[i][j].getType().charAt(0);//Get the first character of the plant type
					int growthRate = land[i][j].getGrowthRate();// Get the growth rate of the plant
					// Get the class name of the plant as name class is com.gradescope.garden.classname
					String className = land[i][j].getClass().getName().split("\\.")[3]; 
					Functions.grid(i, j, plantChar, growthRate, className, arr);
				}
			}
		}
		// Print the garden grid
		return arr;
	}

	public void growAll(int num) {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// Check if there is a plant at this location
				if (land[i][j] != null) {
					land[i][j].setGrowthRate(land[i][j].getGrowthRate() + num); // Grow each plant in the garden
				}
			}
		}
	}

	 /**
     * Grows the plant at a specific position by a specified amount.
     * @param num The amount to grow the plant.
     * @param x The row index of the plant.
     * @param y The column index of the plant.
     */
	public void growAtPosition(int num, int x, int y) {
		if (x < 0 || x >= rows || y < 0 || y >= cols || land[x][y] == null) {
			System.out.println("Can't grow there.\n");
		} else {
			land[x][y].setGrowthRate(land[x][y].getGrowthRate() + num); // Grow each plant in the garden
			// Grow the plant at the specified position
		}
	}

	 // GROW [num] [type] - Grow all plants of a specific type by num times
    /**
     * Grows all plants of a specified type by a given amount.
     * @param num The amount to grow each plant of the specified type.
     * @param type The type of plants to grow.
     */
	
	public void growByType(int num, String type) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (land[i][j] != null && land[i][j].getType().equalsIgnoreCase(type)) {
					land[i][j].setGrowthRate(land[i][j].getGrowthRate() + num);

				}
			}
		}
	}


    // GROW [num] [plantClass] - Grow all plants of a specific class (flower, vegetable, tree)
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
					land[i][j].setGrowthRate(land[i][j].getGrowthRate() + num); // Grow each plant in the garden
				}
			}
		}
	}
	  /**
     * Removes all plants of a specified type or class from the garden.
     * @param typeOrClass The type or class of plants to remove.
     */
	public void remove(String typeOrClass) {

		Functions.removeFunction(typeOrClass, rows, cols, land);
	}


    /**
     * Removes a plant at a specific location based on the action verb.
     * @param row The row index of the plant.
     * @param col The column index of the plant.
     * @param verb The action verb (e.g., "cut", "pick", "ripe", "harvest").
     */
	public void remove(int row, int col, String verb) {
		if(land[row][col]==null)
			return;
		String className = land[row][col].getClass().getName().split("\\.")[3];

		if (verb.equalsIgnoreCase("cut")) {
			if (className.equalsIgnoreCase("tree")) { // Changed from "cub" to "cut"
				((Tree) land[row][col]).cut();
				land[row][col] = null;
			} else {
				System.out.println("Can't cut there.\n");
			}
		} else if (verb.equalsIgnoreCase("pick")) {
			if (className.equalsIgnoreCase("flower")) { // Added cut functionality
				((Flower) land[row][col]).pick(); // Assuming you have a cut() method for Flower
				land[row][col] = null;
			} else {
				System.out.println("Can't pick there.\n");
			}
		} else if (verb.equalsIgnoreCase("ripe")) {
			if (className.equalsIgnoreCase("fruit")) { // Added cut functionality
				((Fruit) land[row][col]).ripe();
				 // Assuming you have a cut() method for Fruit
				land[row][col] = null;
			} else {
				System.out.println("Can't ripe there.\n");
			}
		} else if (verb.equalsIgnoreCase("harvest")) {
			if (className.equalsIgnoreCase("vegetable")) { // Added cut functionality
				((Vegetable) land[row][col]).harvest();
				 // Assuming you have a cut() method for Vegetable
				land[row][col] = null;
			} else {
				System.out.println("Can't harvest there.\n");
			}
		} else {
			System.out.println("Can't " + verb + " there.\n");
		}
	}

}