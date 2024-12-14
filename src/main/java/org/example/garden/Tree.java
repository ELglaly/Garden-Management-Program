package org.example.garden;

import javafx.scene.paint.Color;

public class Tree extends Plant {
	
	    private double height;
	    private boolean isFruited;
	    private boolean isCut;
	    

		public Tree(String type, int age, Color color, int growthRate, Health health, double height, boolean isFruited,
                    boolean isCut) {
			super(type, age, color, growthRate, health);
			this.height = height;
			this.isFruited = isFruited;
			this.isCut = isCut;
		}

		/**
		 * @return the height
		 */
		public double getHeight() {
			return height;
		}

		/**
		 * @return the isFruited
		 */
		public boolean isFruited() {
			return isFruited;
		}

		/**
		 * @return the isCut
		 */
		public boolean isCut() {
			return isCut;
		}

		/**
		 * @param height the height to set
		 */
		public void setHeight(double height) {
			this.height = height;
		}

		/**
		 * @param isFruited the isFruited to set
		 */
		public void setFruited(boolean isFruited) {
			this.isFruited = isFruited;
		}

		/**
		 * @param isCut the isCut to set
		 */
		public void setCut(boolean isCut) {
			this.isCut = isCut;
		}

		
		public void cut()
		{
			this.setCut(true);
			this.setGrowthRate(0);
			
		}
}
