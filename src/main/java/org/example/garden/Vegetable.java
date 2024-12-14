package org.example.garden;


import javafx.scene.paint.Color;

public class Vegetable extends Plant {

	

	private boolean isHarvest = false;
	
	
	public Vegetable(String type, int age, Color color, int growthRate, Health health, boolean isHarvest) {
		super(type, age, color, growthRate, health);
		this.isHarvest=isHarvest;
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the isHarvest
	 */
	public boolean getHarvest() {
		return isHarvest;
	}


	/**
	 * @param isHarvest the isHarvest to set
	 */
	public void setHarvest(boolean isHarvest) {
		this.isHarvest = isHarvest;
		
	}
	
	public void harvest()
	{
		setHarvest(true);
		this.setGrowthRate(0);
	}
	
	
	
	
}
