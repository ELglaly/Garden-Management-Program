package com.gradescope.garden;
import java.util.*;
import java.io.*;


/**
 * 
 * This program allows users to manage a virtual garden by planting, growing, harvesting, picking, and cutting various types of plants.
 * The program supports multiple commands to interact with the garden and provides output reflecting the state of the garden.
 *
 * To use the program, ensure that you know the following:
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
 *   Example :

rows: 1
cols: 1

PLANT (0,0) apple
PRINT
GROW 1
print


output :

> PRINT
.....
.....
.....
.....
..a..

> GROW 1

> PRINT
.....
.....
.....
..a..
..a..

 * Author: Sherif Ashraf Ali
 * 
 */

public class RunGarden {
	
	static ArrayList<String> fruits = new ArrayList<>();
    static ArrayList<String> vegetables = new ArrayList<>();
    static ArrayList<String> trees = new ArrayList<>();
    static ArrayList<String> flowers = new ArrayList<>();

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

	public static void main(String[] args) throws FileNotFoundException {
	     final int MAX = 80; // Maximum allowed width
	     int plotsWidth=0; // Number of plots across
        addTypes(fruits, vegetables, trees, flowers);
		Garden garden=new Garden();
		String fileName=args[0];
		File file = new File(fileName);
		//System.out.println(fileName);
		
		Scanner myreader = new Scanner(file);
		
	
		
		while(myreader.hasNext())
		{
			String lineString=myreader.nextLine();
			String line[]= lineString.split(" ");
			if(line[0].equals("rows:"))
			{
				garden.setRows(Integer.valueOf(line[1]));
			}
			else if(line[0].equalsIgnoreCase("cols:"))
			{
				garden.setCols(Integer.valueOf(line[1]));
				garden.setLand(garden.getRows(),garden.getCols());
				
			}
			
			else if(line[0].equalsIgnoreCase("plant"))
			{
			
				// Extract the rows number for the (2,2) format
				int row = Integer.parseInt(String.valueOf(line[1].charAt(1)));
				// Extract the cols number for the (2,2) format
				int col = Integer.parseInt(String.valueOf(line[1].charAt(3)));
				// pass the col, rows and the plant need to be planted
			    garden.plantGarden(row, col, line[2]);
				
			}
			
			else if(line[0].equalsIgnoreCase("print"))
			{
				System.out.println("> "+line[0].toUpperCase());
				garden.PrintGarden();
				
				if(myreader.hasNext())
				{
					// check if there are more commands so print new line
					System.out.println();
				}
			}
			
			else if(line[0].equalsIgnoreCase("GROW"))
			{
				System.out.println("> GROW "+line[1]+" "+line[2]+'\n');
				if(line.length==2)
				{
					// grow all the type in line[i] by 1
					garden.growAll(Integer.valueOf(line[1]));
				}
				else if(line[2].equalsIgnoreCase("flower"))
				{
					// extract the number of growth
					int num=Integer.valueOf(line[1]);
					// grow all the class flower in line[i]
					garden.growByClass(num, "flower");
				}
				
				else if(line[2].equalsIgnoreCase("tree"))
				{
					int num=Integer.valueOf(line[1]);
					garden.growByClass(num, "tree");
				}
				else if( line[2].equalsIgnoreCase("fruit"))
				{
					int num=Integer.valueOf(line[1]);
					garden.growByClass(num, "fruit");
				}
				else if(line[2].equalsIgnoreCase("vegetable") )
				{
					int num=Integer.valueOf(line[1]);
					garden.growByClass(num, "vegetable");
				}
				// check if the line [2] is (2,2) format not a class name or type using regx
				
				else if (line[2].matches("\\(\\d+,\\d+\\)")) {
					int num=Integer.valueOf(line[1]);
					int row = Integer.parseInt(String.valueOf(line[2].charAt(1)));
					int col = Integer.parseInt(String.valueOf(line[2].charAt(3)));
				
				    garden.growAtPosition(num, row, col);
				}
				else 
				{
					int num=Integer.valueOf(line[1]);
					garden.growByType(num, line[2]);
				}
			}
			
			
			else if(line[0].equalsIgnoreCase("HARVEST") || line[0].equalsIgnoreCase("CUT")
					||line[0].equalsIgnoreCase("PICK")|| line[0].equalsIgnoreCase("Ripe"))
			{
				System.out.print("> "+line[0].toUpperCase());
				
				if(line.length==1)
				{
					System.out.println("\n");
					garden.remove(line[0]);
				}
				// check if the line [2] is (2,2) format not a class name or type using regx
				
				else if(line[1].matches("\\(\\d+,\\d+\\)"))
				{
					System.out.println(" "+line[1]+'\n');
					int row = Integer.parseInt(String.valueOf(line[1].charAt(1)));
					int col = Integer.parseInt(String.valueOf(line[1].charAt(3)));
					garden.remove(row, col,line[0]);
				}
				else
				{
					System.out.println(" "+line[1]+'\n');
					garden.remove(line[1]);
				}
			}
		}
	}

}