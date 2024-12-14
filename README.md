# Virtual Garden Management Program

## Overview

This program simulates a virtual garden where users can perform various gardening tasks like planting, growing, harvesting, picking, and cutting plants. The user interacts with the garden through a series of commands, and the program reflects the state of the garden accordingly.

## Features

- **Planting**: Place different types of plants in specific positions in the garden grid.
- **Growth Simulation**: Simulate plant growth by specifying growth amounts.
- **Harvesting**: Remove vegetables from the garden.
- **Picking**: Remove flowers from the garden.
- **Cutting**: Remove trees from the garden.
- **Garden Display**: Print the current state of the garden to visualize plant positions and statuses.

## Program Code Description
## You can view the prototype of the program 
https://drive.google.com/file/d/1u0Rw0HzOWSHD1Xejssub-3XUlhWXgKaE/view .

```java
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
