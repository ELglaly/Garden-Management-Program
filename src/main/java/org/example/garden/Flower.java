package org.example.garden;

import javafx.scene.paint.Color;

public class Flower extends Plant {
    private String bloomTime;
    private boolean isPicked;

    // Constructor to initialize a Flower object
    public Flower(String name, int age, Color color, int growthRate, Health health, String bloomTime, boolean isPicked) {
        super(PlantType.FLOWER,name,age, color, growthRate, health);
        this.bloomTime = bloomTime;
        this.isPicked = isPicked;
    }

    // Getter for bloomTime
    public String getBloomTime() {
        return bloomTime;
    }

    // Getter for isPicked
    public boolean isPicked() {
        return isPicked;
    }

    // Setter for bloomTime with validation
    public void setBloomTime(String bloomTime) {
        if (bloomTime == null || bloomTime.trim().isEmpty()) {
            throw new IllegalArgumentException("Bloom time cannot be null or empty");
        }
        this.bloomTime = bloomTime;
    }

    // Setter for isPicked
    public void setPicked(boolean isPicked) {
        this.isPicked = isPicked;
    }

    // Method to simulate picking the flower
    public void pick() {
        if (this.isPicked) {
            System.out.println("The flower has already been picked.");
            return;
        }
        this.setPicked(true);
        this.setGrowthRate(0); // The flower stops growing once picked
        System.out.println("The flower has been picked.");
    }

    // Overriding the grow() method to implement specific behavior for flowers
    @Override
    public void grow() {
        if (!isPicked) {
            // Simulating flower growth behavior
            System.out.println("The flower is growing!");
        } else {
            System.out.println("The flower cannot grow because it has been picked.");
        }
    }

    // Override the toString() method for better representation of a Flower
    @Override
    public String toString() {
        return "Flower{" +
                "type='" + getType() + '\'' +
                ", age=" + getAge() +
                ", color=" + getColor() +
                ", growthRate=" + getGrowthRate() +
                ", health=" + getHealth() +
                ", bloomTime='" + bloomTime + '\'' +
                ", isPicked=" + isPicked +
                '}';
    }
}
