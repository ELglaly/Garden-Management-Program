package org.example.garden;

import javafx.scene.paint.Color;

public class Vegetable extends Plant {

	private boolean isHarvested;

	// Constructor to initialize Vegetable object
	public Vegetable(String name, int age, Color color, int growthRate, Health health, boolean isHarvested) {
		super(PlantType.VEGETABLE,name, age, color, growthRate, health);
		this.isHarvested = isHarvested;
	}

	// Getter for isHarvested
	public boolean isHarvested() {
		return isHarvested;
	}

	// Setter for isHarvested
	public void setHarvested(boolean isHarvested) {
		this.isHarvested = isHarvested;
	}

	// Method to simulate harvesting the vegetable
	public void harvest() {
		if (!isHarvested) {
			setHarvested(true);
			this.setGrowthRate(0); // No further growth after harvesting
			System.out.println("The vegetable has been harvested.");
		} else {
			System.out.println("The vegetable has already been harvested.");
		}
	}

	// Override grow() method to provide behavior for vegetable growth
	@Override
	public void grow() {
		if (!isHarvested) {
			// Simulate vegetable growth
			this.setGrowthRate(this.getGrowthRate() + 1); // Increase growth rate incrementally
			System.out.println("The vegetable is growing. Growth rate: " + this.getGrowthRate());
		} else {
			System.out.println("The vegetable cannot grow anymore because it is harvested.");
		}
	}

	// Override the toString() method for better representation of Vegetable object
	@Override
	public String toString() {
		return "Vegetable{" +
				"type='" + getType() + '\'' +
				", age=" + getAge() +
				", color=" + getColor() +
				", growthRate=" + getGrowthRate() +
				", health=" + getHealth() +
				", isHarvested=" + isHarvested +
				'}';
	}
}
