package org.example.garden;

import javafx.scene.paint.Color;

public class Fruit extends Plant {

	private boolean isRipen = false;
	private double size;

	// Constructor to initialize the Fruit object
	public Fruit(String name, int age, Color color, int growthRate, Health health, boolean isRipen, double size) {
		super(PlantType.FRUIT,name, age, color, growthRate, health);
		this.isRipen = isRipen;
		this.size = size;
	}

	// Getter for isRipen
	public boolean isRipen() {
		return isRipen;
	}

	// Getter for size
	public double getSize() {
		return size;
	}

	// Setter for isRipen
	public void setRipen(boolean isRipen) {
		this.isRipen = isRipen;
	}

	// Setter for size with validation
	public void setSize(double size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be greater than zero");
		}
		this.size = size;
	}

	// Method to simulate the fruit ripening process
	public void ripe() {
		if (!this.isRipen) {
			setRipen(true);
			this.setGrowthRate(0);  // No further growth once ripe
			System.out.println("The fruit has ripened.");
		} else {
			System.out.println("The fruit is already ripened.");
		}
	}

	// Override the grow() method to implement behavior for fruit growth
	@Override
	public void grow() {
		if (!isRipen()) {
			// Simulate growth of fruit size
			this.size += 0.5; // For example, the fruit grows by 0.5 units each time it grows
			System.out.println("The fruit is growing. Current size: " + this.size);
		} else {
			System.out.println("The fruit cannot grow because it is ripened.");
		}
	}

	@Override
	public void draw(int row, int col, int rate, Color[][] arr) {
		int startRow = row * 5;
		int startCol = col * 5;
		for (int i = 0; i < rate; i++) {
			if (startRow + 4 - i < arr.length) {
				arr[startRow + 4 - i][startCol + 2] = Color.ORANGE; // Plant grows downwards in the middle
			}
		}
	}

	// Override the toString() method for better representation of the Fruit object
	@Override
	public String toString() {
		return "Fruit{" +
				"type='" + getType() + '\'' +
				", age=" + getAge() +
				", color=" + getColor() +
				", growthRate=" + getGrowthRate() +
				", health=" + getHealth() +
				", isRipen=" + isRipen +
				", size=" + size +
				'}';
	}
}
