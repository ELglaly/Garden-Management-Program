package org.example.garden;

/**
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
 * 1. PLANT (x,y) <type> - Plants a specified type of plant at (x,y).
 * 2. PRINT - Prints the current state of the garden.
 * 3. GROW <num> - Grows all plants by the specified amount.
 * 4. GROW <num> (x,y) - Grows the plant at (x,y) by the specified amount.
 * 5. GROW <num> <type> - Grows all plants of the specified type.
 * 6. HARVEST - Removes all vegetables from the garden.
 * 7. PICK - Removes all flowers from the garden.
 * 8. CUT - Removes all trees from the garden.
 *
 * Example:
 * Input file (e.g., `myGarden.in`):
 * rows: 1
 * cols: 1
 * PLANT (0,0) apple
 * PRINT
 * GROW 1
 * PRINT
 *
 * Author: Sherif Ashraf Ali
 */

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.garden.Garden;
import org.example.garden.GardenDatabase;

import java.io.*;
import java.util.*;

public class App extends Application {

    // The main Garden object used throughout the program
    static Garden garden = new Garden();

    // Queue to store commands read from the input file
    static Queue<String> commandQueue = new LinkedList<>();

    // Default delay time (in seconds) between processing commands
    private static double delay = 2;

    // Constants for graphical rendering
    private final static int TEXT_SIZE = 120; // Height of the text area for displaying commands
    private final static int RECT_SIZE = 7; // Size of basic garden grid rectangles
    private final static int RECT_SIZE_Plant = 10; // Size of plant representation on the grid

    // Variables representing the dimensions of the garden grid (in pixels)
    private static int SIZE_ACROSS;
    private static int SIZE_DOWN;

    /**
     * Main method to initialize the program.
     * Reads the input file, initializes the garden, and launches the JavaFX application.
     *
     * @param args Command-line arguments.
     * @throws FileNotFoundException If the input file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Load predefined plant types from external configuration
        GardenDatabase.addTypes();

        // Read the input file name from the console
        Scanner read = new Scanner(System.in);
        String fileName = read.next(); // Example: "myGarden.in"
        read.close();

        // Open the input file and read commands
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);

        // Process the garden configuration and commands from the input file
        while (myReader.hasNext()) {
            String lineString = myReader.nextLine();
            String[] line = lineString.split(" ");

            // Parse garden dimensions from input file
            if (line[0].equals("rows:")) {
                garden.setRows(Integer.valueOf(line[1]));
                setSizeDown(Integer.valueOf(line[1])); // Set vertical size of the grid
            } else if (line[0].equalsIgnoreCase("cols:")) {
                garden.setCols(Integer.valueOf(line[1]));
                garden.setLand(garden.getRows(), garden.getCols());
                setSizeAcross(Integer.valueOf(line[1])); // Set horizontal size of the grid
            } else if (line[0].equalsIgnoreCase("delay:")) {
                App.setDelay(Double.valueOf(line[1])); // Update delay time between commands
            } else {
                // Add other commands to the command queue for processing
                commandQueue.add(lineString);
            }
        }
        myReader.close();

        // Launch the JavaFX application to display the garden simulation
        launch(args);
    }

    /**
     * Starts the JavaFX application, setting up the main graphical interface.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // TextArea for displaying commands during simulation
        TextArea command = new TextArea();

        // GraphicsContext for drawing the garden grid and elements
        GraphicsContext gc = setupStage(primaryStage, command);

        // Show the primary stage
        primaryStage.show();

        // Start the garden simulation
        simulateGarden(gc, command);
    }

    /**
     * Processes commands in the queue to update the garden state and render the simulation.
     *
     * @param gc GraphicsContext for rendering the garden.
     * @param command TextArea for displaying commands.
     * @throws FileNotFoundException If an error occurs during command processing.
     */
    private void simulateGarden(GraphicsContext gc, TextArea command) throws FileNotFoundException {
        // Initial rendering of the garden grid
        for (int x = 0; x < garden.getRows(); x++) {
            for (int y = 0; y < garden.getCols(); y++) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        gc.setFill(Color.GRAY); // Default color for empty land
                        gc.fillRect((x * 5 + i) * 20, (y * 5 + j) * 20, RECT_SIZE, RECT_SIZE);
                    }
                }
            }
        }

        // PauseTransition to introduce delay between command executions
        PauseTransition wait = new PauseTransition(Duration.seconds(delay));

        // Define action to perform after each delay
        wait.setOnFinished((e) -> {
            if (!commandQueue.isEmpty()) {
                String line = commandQueue.poll(); // Retrieve the next command
                processCommand(line, gc, command); // Execute the command
                gardenDraw(gc); // Redraw the garden after command execution
                wait.playFromStart(); // Continue with the next command
            } else {
                wait.stop(); // Stop when all commands are processed
            }
        });
        wait.play(); // Start the simulation
    }

    /**
     * Processes a single command and updates the garden state accordingly.
     *
     * @param lineString The command string to process.
     * @param gc GraphicsContext for rendering.
     * @param command TextArea for logging executed commands.
     */
    private void processCommand(String lineString, GraphicsContext gc, TextArea command) {
        // Split the input command string into parts
        String[] line = lineString.split(" ");

        // Handle PLANT command to add a new plant to the garden at specified coordinates
        if (line[0].equalsIgnoreCase("plant")) {
            // Extract row and column from the coordinate format (e.g., "(1,2)")
            int row = Integer.parseInt(String.valueOf(line[1].charAt(1)));
            int col = Integer.parseInt(String.valueOf(line[1].charAt(3)));
            // Call the method to plant in the garden at the specified location
            garden.plantGarden(row, col, line[2]);
        }
        // Handle GROW command to grow plants by a specified amount
        else if (line[0].equalsIgnoreCase("GROW")) {
            // Extract the growth amount from the command
            int num = Integer.parseInt(line[1]);

            if (line.length == 2) {
                // Grow all plants by the specified amount
                garden.growAll(num);
            } else if (line[2].matches("\\(\\d+,\\d+\\)")) { // Check for coordinate format
                // Extract row and column from the coordinate format
                int row = Integer.parseInt(String.valueOf(line[2].charAt(1)));
                int col = Integer.parseInt(String.valueOf(line[2].charAt(3)));
                // Grow the plant at the specified position
                garden.growAtPosition(num, row, col);
            } else if (line[2].equalsIgnoreCase("flower") || line[2].equalsIgnoreCase("tree") || line[2].equalsIgnoreCase("fruit") || line[2].equalsIgnoreCase("vegetable")) {
                // Grow all plants of a specific class (e.g., "flower")
                garden.growByClass(num, line[2]);
            } else {
                // Grow plants of a specific type
                garden.growByType(num, line[2]);
            }
        }
        // Handle REMOVE commands (HARVEST, PICK, CUT, RIPE) to remove plants from the garden
        else if (line[0].equalsIgnoreCase("HARVEST") || line[0].equalsIgnoreCase("CUT") || line[0].equalsIgnoreCase("PICK") || line[0].equalsIgnoreCase("RIPE")) {
            if (line.length == 1) {
                // Remove all plants of the specified type
                garden.remove(line[0]);
            } else if (line[1].matches("\\(\\d+,\\d+\\)")) {
                // Extract row and column from the coordinate format
                int row = Integer.parseInt(String.valueOf(line[1].charAt(1)));
                int col = Integer.parseInt(String.valueOf(line[1].charAt(3)));
                // Remove the plant at the specified position
                garden.remove(row, col, line[0]);
            } else {
                // Remove plants of a specific type
                garden.remove(line[1]);
            }
        }

        // Append the executed command to the TextArea for logging
        command.appendText(lineString + "\n");
    }

    /**
     * Redraws the garden based on its current state.
     * Updates cell colors to reflect plant types or empty land.
     *
     * @param gc GraphicsContext for drawing.
     */
    private void gardenDraw(GraphicsContext gc) {
        // Retrieves the garden's color array, where each cell contains the color corresponding to the plant or empty land
        Color arr[][] = garden.createGarden();

        // Clears the canvas to prepare for redrawing the garden
        gc.clearRect(0, 0, SIZE_ACROSS, SIZE_DOWN);

        // Iterate through each cell in the garden
        for (int x = 0; x < garden.getRows() * 5; x++) {
            for (int y = 0; y < garden.getCols() * 5; y++) {
                if (arr[x][y] != null) {
                    // Get the color of the plant from the garden land array
                    Color color = garden.getLand()[x / 5][y / 5].getColor();
                    // Set the fill color and draw the rectangle representing the plant
                    gc.setFill(color);
                    gc.fillRect(y * 20, x * 20, RECT_SIZE_Plant, RECT_SIZE_Plant);
                } else {
                    // Set the fill color to gray for empty land
                    gc.setFill(Color.GRAY);
                    gc.fillRect(y * 20, x * 20, RECT_SIZE, RECT_SIZE);
                }
            }
        }
    }

    /**
     * Sets up the JavaFX scene, including the canvas for drawing and a TextArea for displaying commands.
     *
     * @param primaryStage The primary JavaFX stage.
     * @param command TextArea for showing executed commands.
     * @return GraphicsContext for the canvas, used for rendering.
     */
    public GraphicsContext setupStage(Stage primaryStage, TextArea command) {
        // StackPane for managing layout
        StackPane root = new StackPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(root);
        layout.setBottom(command);
        BorderPane.setMargin(command, new Insets(TEXT_SIZE, 10, 10, 10));

        // Initialize the canvas for drawing the garden
        Canvas canvas = new Canvas(SIZE_ACROSS, SIZE_DOWN);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set up the scene and the primary stage
        Scene scene = new Scene(layout, SIZE_ACROSS, SIZE_DOWN);
        primaryStage.setTitle("Garden Management Simulation");
        primaryStage.setScene(scene);

        return gc;
    }

    /**
     * Sets the delay time for executing commands in the simulation.
     *
     * @param delayTime The delay time in seconds.
     */
    public static void setDelay(double delayTime) {
        delay = delayTime;
    }

    /**
     * Sets the size of the garden grid's width (across).
     *
     * @param size The width in units.
     */
    public static void setSizeAcross(int size) {
        SIZE_ACROSS = size * 20;
    }

    /**
     * Sets the size of the garden grid's height (down).
     *
     * @param size The height in units.
     */
    public static void setSizeDown(int size) {
        SIZE_DOWN = size * 20;
    }
}
