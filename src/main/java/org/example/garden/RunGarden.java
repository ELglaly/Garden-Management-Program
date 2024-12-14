package org.example.garden;
import java.util.*;


/**
 *
 * This program allows users to manage a virtual garden by planting, growing, harvesting, picking, and cutting various types of plants.
 * The program supports multiple commands to interact with the garden and provides output reflecting the state of the garden.
 *
 * Usage:
 * - The user must specify the input file name as a command-line argument when running the program.
 * - Example: `java App myGarden.in`
 * - The input file should contain the garden configuration and commands as specified below.
 *
 * Input File Format:
 * - Each command must be on a separate line.
 * - Coordinates for plotting (x, y) are zero-indexed.
 * - The garden grid allows for a maximum width of 80 characters, accommodating a maximum of 16 plots across.
 *
 * Commands:
 * 1. PLANT (x,y) <type>
 *    - Example: PLANT (0,0) rose
 *    - This command plants the specified type of plant in the garden at the given coordinates (x,y).
 *
 * 2. PRINT
 *    - Example: PRINT
 *    - Prints the entire garden to standard output.
 *
 * 3. GROW <num>
 *    - Example: GROW 1
 *    - Increases the growth of all plants in the garden by the specified number of times.
 *
 * 4. GROW <num> (x,y)
 *    - Example: GROW 1 (2,3)
 *    - Grows the plant located at (x,y) by the specified number of times.
 *
 * 5. GROW <num> <type>
 *    - Example: GROW 1 rose
 *    - Grows all plants of the specified type by the specified number of times.
 *
 * 6. HARVEST
 *    - Removes all vegetables from the garden.
 *
 * 7. HARVEST (x,y)
 *    - Example: HARVEST (2,3)
 *    - Harvests the vegetable located at (x,y) if it is a vegetable.
 *
 * 8. HARVEST <type>
 *    - Example: HARVEST tomato
 *    - Harvests all vegetables of the specified type from the garden.
 *
 * 9. PICK
 *    - Removes all flowers from the garden.
 *
 * 10. PICK (x,y)
 *     - Example: PICK (2,3)
 *     - Picks the flower located at (x,y) if it is a flower.
 *
 * 11. PICK <type>
 *     - Example: PICK rose
 *     - Picks all flowers of the specified type from the garden.
 *
 * 12. CUT
 *     - Removes all trees from the garden.
 *
 * 13. CUT (x,y)
 *     - Example: CUT (2,3)
 *     - Cuts the tree located at (x,y) if it is a tree.
 *
 * 14. CUT <type>
 *     - Example: CUT PINE
 *     - Cuts all trees of the specified type from the garden.
 *
 * Example:
 *
 * Input file (e.g., `myGarden.in`):
 *
 * rows: 1
 * cols: 1
 *
 * PLANT (0,0) apple
 * PRINT
 * GROW 1
 * PRINT
 *
 * Expected Output:
 *
 * > PRINT
 * .....
 * .....
 * .....
 * .....
 * ..a..
 *
 * > GROW 1
 *
 * > PRINT
 * .....
 * .....
 * .....
 * ..a..
 * ..a..
 *
 * Author: Sherif Ashraf Ali
 *
 */


public class RunGarden {

	public static ArrayList<String> fruits = new ArrayList<>();
	public static ArrayList<String> vegetables = new ArrayList<>();
	public static ArrayList<String> trees = new ArrayList<>();
	public static ArrayList<String> flowers = new ArrayList<>();

	public static void addTypes(ArrayList<String> fruits, ArrayList<String> vegetables, ArrayList<String> trees, ArrayList<String> flowers) {
        // Add fruits
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("orange");
        fruits.add("mango");
        fruits.add("strawberry");
        
        
        // Add vegetables
        vegetables.add("tomato");
        vegetables.add("carrot");
        vegetables.add("potato");
        vegetables.add("lettuce");
        vegetables.add("spinach");
        vegetables.add("yam");
        vegetables.add("garlic");
        vegetables.add("zucchini");
      //  vegetables.add("coconut");
      
        
        // Add trees
        trees.add("oak");
        trees.add("maple");
        trees.add("pine");
        trees.add("birch");
        trees.add("willow");
        trees.add("coconut");
        
        // Add flowers
        flowers.add("rose");
        flowers.add("tulip");
        flowers.add("sunflower");
        flowers.add("daisy");
        flowers.add("lily");
        flowers.add("iris");
    
    }

}