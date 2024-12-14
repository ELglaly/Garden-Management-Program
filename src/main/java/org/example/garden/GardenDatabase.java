package org.example.garden;

import java.util.*;
import javafx.scene.paint.Color;

public class GardenDatabase {

        private static List<Plant> fruits = new ArrayList<>();
        private static List<Plant> vegetables = new ArrayList<>();
        private static List<Plant> trees = new ArrayList<>();
        private static List<Plant> flowers = new ArrayList<>();
        

        // Adds predefined plant types to the garden
        public static void addTypes() {
                addFruits();
                addVegetables();
                addTrees();
                addFlowers();
        }

        // Add fruit types
        private static void addFruits() {
                fruits.add(new Fruit( "apple", 1, Color.RED, 1, Health.GOOD, false, 5.0));
                fruits.add(new Fruit("banana", 2, Color.YELLOW, 2, Health.GOOD, true, 6.0));
                fruits.add(new Fruit("orange", 1, Color.ORANGE, 1, Health.GOOD, false, 4.0));
                fruits.add(new Fruit("mango", 3, Color.YELLOW, 3, Health.GOOD, false, 8.0));
                fruits.add(new Fruit("strawberry", 0, Color.RED, 1, Health.GOOD, true, 0.5));
        }

        // Add vegetable types
        private static void addVegetables() {
                vegetables.add(new Vegetable("tomato", 1, Color.RED, 1, Health.GOOD, false));
                vegetables.add(new Vegetable("carrot", 1, Color.ORANGE, 2, Health.GOOD, false));
                vegetables.add(new Vegetable("potato", 1, Color.BROWN, 1, Health.GOOD, false));
                vegetables.add(new Vegetable("lettuce", 1, Color.GREEN, 1, Health.GOOD, false));
                vegetables.add(new Vegetable("spinach", 1, Color.GREEN, 1, Health.GOOD, false));
                vegetables.add(new Vegetable("yam", 2, Color.ORANGE, 2, Health.GOOD, false));
                vegetables.add(new Vegetable("garlic", 1, Color.WHITE, 1, Health.GOOD, false));
                vegetables.add(new Vegetable("zucchini", 1, Color.GREEN, 1, Health.GOOD, false));
        }

        // Add tree types
        private static void addTrees() {
                trees.add(new Tree("oak", 10, Color.BROWN, 3, Health.GOOD, 10.0, false, false));
                trees.add(new Tree("maple", 15, Color.GREEN, 4, Health.GOOD, 12.0, false, false));
                trees.add(new Tree("pine", 20, Color.GREEN, 2, Health.GOOD, 18.0, false, false));
                trees.add(new Tree("birch", 8, Color.WHITE, 2, Health.GOOD, 6.0, false, false));
                trees.add(new Tree("willow", 5, Color.GREEN, 3, Health.GOOD, 7.0, false, false));
                trees.add(new Tree("coconut", 5, Color.GREEN, 3, Health.GOOD, 12.0, true, false));
        }

        // Add flower types
        private static void addFlowers() {
                flowers.add(new Flower("rose", 1, Color.RED, 1, Health.GOOD, "spring", false));
                flowers.add(new Flower("tulip", 1, Color.PURPLE, 2, Health.GOOD, "summer", false));
                flowers.add(new Flower("sunflower", 1, Color.YELLOW, 1, Health.GOOD, "summer", false));
                flowers.add(new Flower("daisy", 1, Color.WHITE, 1, Health.GOOD, "spring", false));
                flowers.add(new Flower("lily", 1, Color.PINK, 1, Health.GOOD, "spring", false));
                flowers.add(new Flower("iris", 1, Color.PURPLE, 1, Health.GOOD, "spring", false));
        }

        // Getters for plant lists (if needed elsewhere in the application)
        public static List<Plant> getFruits() {
                return fruits;
        }

        public static List<Plant> getVegetables() {
                return vegetables;
        }

        public static List<Plant> getTrees() {
                return trees;
        }

        public static List<Plant> getFlowers() {
                return flowers;
        }


}
