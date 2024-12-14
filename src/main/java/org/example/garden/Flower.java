package org.example.garden;

import javafx.scene.paint.Color;

public class Flower extends Plant {
    private String bloomTime;
    private boolean isPicked;
    public Flower(String type, int age, Color color, int growthRate, Health health, String bloomTime,
                  boolean isPicked) {
        super(type, age, color, growthRate, health);
        this.bloomTime = bloomTime;
        this.isPicked = isPicked;
    }

    /**
     * @return the bloomTime
     */
    public String getBloomTime() {
        return bloomTime;
    }

    /**
     * @return the isPicked
     */
    public boolean isPicked() {
        return isPicked;
    }

    /**
     * @param bloomTime the bloomTime to set
     */
    public void setBloomTime(String bloomTime) {
        this.bloomTime = bloomTime;
    }

    /**
     * @param isPicked the isPicked to set
     */
    public void setPicked(boolean isPicked) {
        this.isPicked = isPicked;
    }

    public void pick() {
        this.setPicked(true);
        this.setGrowthRate(0);

    }


}
