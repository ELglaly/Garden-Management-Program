package org.example.garden;

import javafx.scene.paint.Color;

public class Tree extends Plant {

	private double height;
	private boolean isFruited;
	private boolean isCut;

	// Constructor to initialize Tree properties
	public Tree(String name, int age, Color color, int growthRate, Health health, double height, boolean isFruited, boolean isCut) {
		super(PlantType.TREE,name, age, color, growthRate, health);
		this.height = height;
		this.isFruited = isFruited;
		this.isCut = isCut;
	}

	// Getter for height
	public double getHeight() {
		return height;
	}

	// Getter for isFruited
	public boolean isFruited() {
		return isFruited;
	}

	// Getter for isCut
	public boolean isCut() {
		return isCut;
	}

	// Setter for height with validation
	public void setHeight(double height) {
		if (height <= 0) {
			throw new IllegalArgumentException("Height must be greater than 0");
		}
		this.height = height;
	}

	// Setter for isFruited
	public void setFruited(boolean isFruited) {
		this.isFruited = isFruited;
	}

	// Setter for isCut
	public void setCut(boolean isCut) {
		this.isCut = isCut;
	}

	// Method to simulate cutting the tree
	public void cut() {
		if (this.isCut) {
			System.out.println("The tree has already been cut.");
			return;
		}
		this.setCut(true);
		this.setGrowthRate(0); // Stop growth after cutting
		System.out.println("The tree has been cut and will no longer grow.");
	}

	// Override the grow() method for tree-specific growth behavior
	@Override
	public void grow() {
		if (!isCut) {
			// Simulate tree growth behavior (e.g., increase height, grow fruit)
			System.out.println("The tree is growing!");
			this.height += 1;  // Example: increase height by 1 unit each time it grows
		} else {
			System.out.println("The tree cannot grow because it has been cut.");
		}
	}

	@Override
	public void draw(int row, int col, int rate, Color[][] arr) {
		int startRow = row * 5;
		int startCol = col * 5;
			for (int i = startRow + 4; i >= startRow; i--) {
				for (int j = startCol; j < startCol + 5; j++) {
					if (j == startCol + 2 && rate > 0) {
						arr[i][j] = Color.BLACK;
						rate--;
					}
				}
			}
	}

	// Override the toString() method to provide a string representation of the tree's state
	@Override
	public String toString() {
		return "Tree{" +
				"type='" + getType() + '\'' +
				", age=" + getAge() +
				", color=" + getColor() +
				", growthRate=" + getGrowthRate() +
				", health=" + getHealth() +
				", height=" + height +
				", isFruited=" + isFruited +
				", isCut=" + isCut +
				'}';
	}
}
