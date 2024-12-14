
package org.example.garden;
import javafx.scene.paint.Color;

public class Fruit extends Plant {
	
	private boolean  isRipen =false;
	private double size;
	

	/**
	 * @return the isRipen
	 */
	public boolean isRipen() {
		return isRipen;
	}

	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @param isRipen the isRipen to set
	 */
	public void setRipen(boolean isRipen) {
		this.isRipen = isRipen;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
	}
	

	public void ripe()
	{
		setRipen(true);
		this.setGrowthRate(0);
	}

	public Fruit(String type, int age, Color color, int growthRate, Health health, boolean isRipen, double size) {
		super(type, age, color, growthRate, health);
		this.isRipen = isRipen;
		this.size = size;
	}
	
	
	

}