import java.util.ArrayList;

public class GameHelper {
    private static final String alphabet = "abcdefg";
    private final int gridSize = 49;
    private final int [] grid = new int[gridSize];
    private int comCount = 0;

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphCells = new ArrayList<>();
        String[] alphacoords = new String[comSize];         //holds 'f6' type coords
        String temp;                                        //temporary String for concat
        int [] coords = new int[comSize];                   //current candidate coords
        int attempts = 0;                                   //current attempts
        boolean success = false;                            //flag to find good location
        int location;                                       //current starting location

        comCount++;                                         //nth dot com to place
        int incr = 1;                                       //horizontal increment
        int gridLength = 7;

        if ((comCount % 2) == 0) {                          // if even dot com
            incr = gridLength;                              // place vertically
        }

        while (!success & attempts++ < 200) {               // main search loop
            location = (int) (Math.random() * gridSize);    // get random starting point
            //System.out.print(" try " + location);
            int x = 0;                                      // nth position in dotcom to place
            success = true;                                 // assume success
            while (success && x < comSize) {                // look for adjacent unused spots
                if (grid[location] == 0) {                  // if not already used

                    coords[x++] = location;                 // save location
                    location += incr;                       // try 'next' adjacent
                    if (location >= gridSize) {             // out of bounds - 'bottom'
                        success = false;                    // failure
                    }
                    // FIXED: Added (incr == 1) to prevent vertical ships from failing in Col A
                    if (x > 0 && (location % gridLength == 0) && incr == 1) { // out of bounds - right edge
                        success = false;                    // failure
                    }
                } else {                                    // found already used location
                    // System.out.print(" used " + location);
                    success = false;                        // failure
                }
            }
        }
        int x = 0;                                          // turn location into alpha coords
        int row;
        int column;
        // System.out.println("\n");
        while (x < comSize) {
            grid[coords[x]] = 1;                            // mark master grid pts. as 'used'
            row = coords[x] / gridLength;                   // get row value // Integer Division: 48 / 7 = 6 (Row 6)
            column = coords[x] % gridLength;                // get numeric column value // Modulo: 48 % 7 = 6 (Column index 6)
            temp = String.valueOf(alphabet.charAt(column)); // convert to alpha
            alphCells.add(temp.concat(Integer.toString(row)));
            x++;
            System.out.print(" coord "+x+" = " + alphCells.get(x-1));
        }
        System.out.println("\n");
        return alphCells;
    }
}