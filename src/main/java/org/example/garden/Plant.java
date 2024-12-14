package org.example.garden;


import javafx.scene.paint.Color;

enum Health{
	Good,Bad
}
public class Plant {
	private String type;
	private int age;
	private Color color;
	private int growthRate = 0;
	private Health health;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @return the growthRate
	 */
	public int getGrowthRate() {
		return growthRate;
	}
	/**
	 * @return the health
	 */
	public Health getHealth() {
		return health;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * @param growthRate the growthRate to set
	 */
	public void setGrowthRate(int growthRate) {
		this.growthRate = growthRate;
	}
	/**
	 * @param health the health to set
	 */
	public void setHealth(Health health) {
		this.health = health;
	}
	public Plant(String type, int age, Color color, int growthRate, Health health) {
		super();
		this.type = type;
		this.age = age;
		this.color = color;
		this.growthRate = growthRate;
		this.health = health;
	}
	
    
	
	

}
