package org.example.garden;

import javafx.scene.paint.Color;

// Abstract class for different types of plants
public abstract class Plant {
	// Enum representing different plant types
	public enum PlantType {
		TREE, FLOWER, FRUIT, VEGETABLE;

		/**
		 * Returns the plant type based on the input string.
		 * @param type String with the plant name
		 * @return the plant type
		 */
		public static Plant.PlantType getName(String type) {
			return switch (type.toLowerCase()) {
				case "fruit" -> PlantType.FRUIT;
				case "vegetable" -> PlantType.VEGETABLE;
				case "trees" -> PlantType.TREE;
				case "flowers" -> PlantType.FLOWER;
				default -> null;
			};
		}
	}

	// Fields representing a plant's characteristics
	private PlantType type;  // Type of the plant (e.g., TREE, FLOWER, etc.)
	private int age;         // Age of the plant
	private Color color;     // Color of the plant
	private int growthRate = 0;  // Growth rate of the plant
	private Health health;   // Health of the plant
	private String name = ""; // Name of the plant

	// Constructor to initialize a new plant
	public Plant(PlantType type ,String name,int age, Color color, int growthRate, Health health) {
		this.age = age;
		this.color = color;
		this.growthRate = growthRate;
		this.health = health;
		this.type = type;
		this.name = name;
	}

	// Abstract method for growing the plant (to be implemented by subclasses)
	public abstract void grow();
	public abstract void draw(int row, int col, int rate, Color arr[][]);

	// Getter methods (Encapsulation)
	public PlantType getType() {
		return type;
	}

	public int getAge() {
		return age;
	}

	public Color getColor() {
		return color;
	}

	public int getGrowthRate() {
		return growthRate;
	}

	public Health getHealth() {
		return health;
	}

	// Setter methods (Encapsulation)
	public void setType(PlantType type) {
		this.type = type;
	}

	public void setAge(int age) {
		if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
		this.age = age;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setGrowthRate(int growthRate) {
		if (growthRate < 0) throw new IllegalArgumentException("Growth rate cannot be negative");
		this.growthRate = growthRate;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Override toString() for better representation of the plant
	@Override
	public String toString() {
		return "Plant{" +
				"type='" + type + '\'' +
				", age=" + age +
				", color=" + color +
				", growthRate=" + growthRate +
				", health=" + health +
				'}';
	}

	// Method to remove a plant by name or verb (e.g., "cut", "pick")
	public static void removePlant(String nameOrVerb, int rows, int cols, Plant land[][]) {
		// Try to get the class name based on the nameOrVerb input
		String className = getClassNameByPlantName(nameOrVerb);
		if (className != null) {
			// Remove by class name
			RemovePlantByName(className, nameOrVerb, rows, cols, land);
		} else {
			// Remove by type (e.g., "harvest", "ripe")
			RemovePlantByType(nameOrVerb, rows, cols, land);
		}
	}

	// Helper method to remove plants based on their type (e.g., "harvest" for vegetables)
	private static void RemovePlantByType(String Verb, int rows, int cols, Plant[][] land) {
		// Get the plant type by verb (e.g., "harvest" -> Vegetable)
		Plant.PlantType type = getClassNameByVerb(Verb);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (land[i][j] == null)
					continue;
				else if (type == PlantType.FLOWER) {
					((Flower) land[i][j]).pick();
				}
				else if (type == PlantType.FRUIT) {
					((Fruit) land[i][j]).ripe();
				}
				else if (type == PlantType.VEGETABLE) {
					((Vegetable) land[i][j]).harvest();
				}
				land[i][j] = null;
			}
		}
	}

	// Helper method to remove plants by their name
	private static void RemovePlantByName(String className, String name, int rows, int cols, Plant[][] land) {
		// Get the plant type based on the class name (e.g., "Tree" -> TREE)
		Plant.PlantType typePlant = Plant.PlantType.getName(className);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (land[i][j] == null)
					continue;
				// Check if the plant's name matches the input name
				if (land[i][j].getName().equalsIgnoreCase(name)) {
					// Perform the appropriate action based on the plant's type
					if (typePlant == Plant.PlantType.TREE) {
						((Tree) land[i][j]).cut();
					} else if (typePlant == Plant.PlantType.FLOWER) {
						((Flower) land[i][j]).pick();
					} else if (typePlant == Plant.PlantType.FRUIT) {
						((Fruit) land[i][j]).ripe();
					} else if (typePlant == Plant.PlantType.VEGETABLE) {
						((Vegetable) land[i][j]).harvest();
					}
					land[i][j] = null;  // Remove the plant from the land
				}
			}
		}
	}

	// Helper method to get the class name based on the plant's name (e.g., "tree" -> "Tree")
	private static String getClassNameByPlantName(String nameOrVerb) {
		if (GardenDatabase.getVegetables().contains(nameOrVerb)) {
			return "Vegetable";
		} else if (GardenDatabase.getFruits().contains(nameOrVerb)) {
			return "Fruit";
		} else if (GardenDatabase.getFlowers().contains(nameOrVerb)) {
			return "Flower";
		} else if (GardenDatabase.getTrees().contains(nameOrVerb)) {
			return "Tree";
		}
		return null;
	}

	// Helper method to get the plant type based on the verb (e.g., "harvest" -> Vegetable)
	public static Plant.PlantType getClassNameByVerb(String verb) {
		if (verb.equalsIgnoreCase("harvest")) {
			return PlantType.VEGETABLE;
		} else if (verb.equalsIgnoreCase("pick")) {
			return PlantType.FLOWER;
		} else if (verb.equalsIgnoreCase("cut")) {
			return PlantType.TREE;
		} else if (verb.equalsIgnoreCase("ripe")) {
			return PlantType.FRUIT;
		}
		return null;
	}

}
