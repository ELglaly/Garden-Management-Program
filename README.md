# Garden Management Program

This program allows users to manage a virtual garden by planting, growing, harvesting, picking, and cutting various types of plants. The program supports multiple commands to interact with the garden and provides output reflecting the state of the garden.

## How to Use the Program

### Input File Format
- Each command must be on a separate line.
- Commands are **case-sensitive** and must be entered exactly as specified in the examples below.
- Coordinates for plotting (x, y) are zero-indexed.
- The garden grid allows for a maximum width of 80 characters, accommodating a maximum of 16 plots across.

## Commands

### 1. PLANT (x,y) `<type>`
- Example: `PLANT (0,0) rose`
- This command plants the specified type of plant in the garden at the given coordinates (x,y).

### 2. PRINT
- Example: `PRINT`
- Prints the entire garden to standard output.

### 3. GROW `<num>`
- Example: `GROW 1`
- Increases the growth of all plants in the garden by the specified number of times.

### 4. GROW `<num>` (x,y)
- Example: `GROW 1 (2,3)`
- Grows the plant located at (x,y) by the specified number of times.

### 5. GROW `<num>` `<type>`
- Example: `GROW 1 rose`
- Grows all plants of the specified type by the specified number of times.

### 6. HARVEST
- Removes all vegetables from the garden.

### 7. HARVEST (x,y)
- Example: `HARVEST (2,3)`
- Harvests the vegetable located at (x,y) if it is a vegetable.

### 8. HARVEST `<type>`
- Example: `HARVEST tomato`
- Harvests all vegetables of the specified type from the garden.

### 9. PICK
- Removes all flowers from the garden.

### 10. PICK (x,y)
- Example: `PICK (2,3)`
- Picks the flower located at (x,y) if it is a flower.

### 11. PICK `<type>`
- Example: `PICK rose`
- Picks all flowers of the specified type from the garden.

### 12. CUT
- Removes all trees from the garden.

### 13. CUT (x,y)
- Example: `CUT (2,3)`
- Cuts the tree located at (x,y) if it is a tree.

### 14. CUT `<type>`
- Example: `CUT PINE`
- Cuts all trees of the specified type from the garden.

## Notes
- The program will print error messages for invalid operations, for example: `Can't grow there.`

## Author
- **[Your Name]**

## Date
- **[Current Date]**
