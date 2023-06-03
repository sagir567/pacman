package exe.ex3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 *
 * @author boaz.benmoshe
 */

public class Map implements Map2D {

    private int[][] _map;

    private boolean _cyclicFlag = true;

    /**
     * Constructs a w*h 2D raster map with an init value v.
     *
     * @param w width of the map
     * @param h height of the map
     * @param v init value
     */
    public Map(int w, int h, int v) {
        init(w, h, v);
    }

    /**
     * Constructs a square map (size*size).
     *
     * @param size
     */
    public Map(int size) {
        this(size, size, 0);
    }

    /**
     * Constructs a map from a given 2D array.
     *
     * @param data
     */
    public Map(int[][] data) {
        init(data);
    }

    @Override
    public void init(int w, int h, int v) {
        int[][] Matrix = new int[w][h];//new 2D array to contain the deep copy
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {//the itration over the copy and insertion of init value.
                Matrix[i][j] = v;
            }
            this._map = Matrix;//changing this map to be the copy.


        }
    }

    @Override
    public void init(int[][] arr) {
        if (arr == null | arr.length == 0) {//if arr empty throw exception.
            throw new RuntimeException("Invalid Input");
        } else {
            for (int i = 1; i < arr.length; i++) {
                if ((arr[i].length != arr[i - 1].length) | arr[i - 1].length == 0) {//if ragged array throw exception.
                    throw new RuntimeException("Invalid Input");
                }


            }
            this._map = arr;//changing this map to arr.
        }
    }


    @Override
    public int[][] getMap() {
        int[][] ans = null;//init of ans as empty 2D array
        int rows = this._map.length;
        int coll = this._map[0].length;
        int[][] copiedArray = new int[rows][coll];//making a deep copy with same height and width.
        for (int i = 0; i < rows; i++) {
            System.arraycopy(this._map[i], 0, copiedArray[i], 0, coll);//iteration and insertion of this map
                                                                                     //to the copied array
        }
        ans = copiedArray;

        return ans;//return deep copy
    }

    @Override

    public int getWidth() {//get width of this map
        return this._map.length;
    }

    @Override

    public int getHeight() {//get height of this map
        return this._map[0].length;
    }

    @Override

    public int getPixel(int x, int y) {//get pixel at a specific xy
        return this._map[x][y];
    }

    @Override

    public int getPixel(Pixel2D p) {//extract the pixel color from a Pixel2D object
        return this.getPixel(p.getX(), p.getY());
    }

    @Override

    public void setPixel(int x, int y, int v) {//from the pixel's x and y coordinates change pixel to be different color.
        this._map[x][y] = v;
    }

    @Override

    public void setPixel(Pixel2D p, int v) {//set specific pixel's color.
        this._map[p.getX()][p.getY()] = v;
    }

    @Override
    /**
     * Fills this map with the new color (new_v) starting from p.
     * https://en.wikipedia.org/wiki/Flood_fill
     */
    public int fill(Pixel2D p, int new_v) {
        int ans = 0;
        int[][] a2= getMap();//save map in a2.
        floodFill(this._map,p.getX(),p.getY(),getPixel(p),new_v);// use the floodFill algorithm to change to paint the map.
        for (int i = 0; i <this._map.length ; i++)
        {                                      //count number of pixels changed.
            for (int j = 0; j <this._map[0].length ; j++) {
               if(this._map[i][j]!=a2[i][j])
               {
                   ans+=1;
               }
            }

        }
        return ans;
    }

    @Override
    /**
     * BFS like shortest the computation based on iterative raster implementation of BFS, see:
     * https://en.wikipedia.org/wiki/Breadth-first_search
     */
    public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
        Pixel2D[] ans = null;
        Map2D map1 = new Map(_map);
        Map2D map2 = map1.allDistance(p1, obsColor);
        // Check if p1 or p2 are unreachable or have the same distance
        if(map2.getPixel(p2)==-1||map2.getPixel(p1)==-1||map2.getPixel(p2)==map2.getPixel(p1))
            return null;
        else {
            int distance = map2.getPixel(p2);
            ans = new Pixel2D[distance + 1]; // +1 to include both p1 and p2 in the path
            Pixel2D current = p2;
            // Reconstruct the path from p2 to p1 using the distances from map2

            for (int i = distance; i >= 0; i--) {
                ans[i] = current;
                int[] dx = {0, 0, -1, 1};
                int[] dy = {-1, 1, 0, 0};
                int currX = current.getX();
                int currY = current.getY();

                // Explore the four possible directions: up, down, left, right
                for (int j = 0; j < 4; j++) {
                    int newX = currX + dx[j];
                    int newY = currY + dy[j];
                    Pixel2D neighbor = new Index2D(newX, newY);

                    // Check if the neighbor is a valid move and has a distance of (i-1)
                    if (isValidMove(map2.getMap(), newX, newY) && map2.getPixel(neighbor) == (i - 1)) {
                        current = neighbor;
                        break;
                    }
                }
            }
        }

        return ans;
    }

    /**
     * a simple implementation of floodFill as presented in "https://en.wikipedia.org/wiki/Flood_fill"
     */
    public void floodFill(int[][] map, int x, int y, int targetColor, int replacementColor) {
        Pixel2D a = new Index2D(x, y);
        if (!isInside(a) || map[x][y] != targetColor || map[x][y] == replacementColor) {//if the pixel is not inside,
                                            // or the pixel's target is already changed, or if the target color is not the
                                             //color we want to change stop the recursive function.
            return;
        }
        map[x][y] = replacementColor;//change the color to the replacement color.
        floodFill(map, x + 1, y, targetColor, replacementColor);//fill right
        floodFill(map, x - 1, y, targetColor, replacementColor);//fill left
        floodFill(map, x, y + 1, targetColor, replacementColor);//fill down
        floodFill(map, x, y - 1, targetColor, replacementColor);//fill up
    }

    @Override
    /////// add your code below ///////
    public boolean isInside(Pixel2D p) {
        int x = p.getX();//x and y coordinates of p
        int y = p.getY();
        int row = this._map.length;
        int coll = this._map[0].length;
        if (x >= row | y >= coll) {//if the x and y coordinates are larger than the max options of coordinates return false.
            return false;
        }
        return true;
    }


    @Override

    public boolean isNeighbors(Pixel2D p1, Pixel2D p2) {//neighbor checking algorithm.
        int sumP1XY = p1.getX() + p1.getY();//sum of x and y of p1
        int sumP2XY = p2.getX() + p2.getY();//sum of x and y of p2
        if (!isCyclic()) {//if the map is not cyclic

            if (Math.abs(sumP1XY - sumP2XY) == 1) { //make sure the abs value of the difference is 1,
                return true;
            }

        } else {//if cyclic
            int width = getWidth(); // Replace getWidth() with the actual width of the map
            int height = getHeight(); // Replace getHeight() with the actual height of the map

            // Check if p1 is at the left edge and p2 is at the right edge
            if (p1.getX() == 0 && p2.getX() == width - 1 && p1.getY() == p2.getY()) {
                return true;
            }

            // Check if p1 is at the right edge and p2 is at the left edge
            if (p1.getX() == width - 1 && p2.getX() == 0 && p1.getY() == p2.getY()) {
                return true;
            }

            // Check if p1 is at the top edge and p2 is at the bottom edge
            if (p1.getY() == 0 && p2.getY() == height - 1 && p1.getX() == p2.getX()) {
                return true;
            }

            // Check if p1 is at the bottom edge and p2 is at the top edge
            if (p1.getY() == height - 1 && p2.getY() == 0 && p1.getX() == p2.getX()) {
                return true;
            }


        }
        return false;
    }




    @Override
    /**
     *this function checks if the map is cyclic.
     */
    public boolean isCyclic() {
        int rows = this._map.length;
        int cols = this._map[0].length;
        for (int j = 0; j < cols; j++) {//checks if the top row is connected to the bottom row
            if (this._map[0][j] == 0 && this._map[rows - 1][j] == 0) {
                return true;
            }
        }//if in any instance two polar lines of the map have a passage between them return true

        // checks if the left row is connected to the right row
        for (int i = 0; i < rows; i++) {
            if (this._map[i][0] == 0 && this._map[i][cols - 1] == 0) {
                return true;
            }
        }// otherwise false.

        return false;
    }

    @Override
    /////// add your code below ///////
    public void setCyclic(boolean cy) {//set map to be cyclic
        if (isCyclic() == true) {
            cy = true;
        }
        cy = false;
    }


    ///// add your code below ///////
    public Map2D allDistance(Pixel2D start, int obsColor) {
        int rows = this._map.length;
        int cols = this._map[0].length;
        int[][] distances = new int[rows][cols];


        for (int i = 0; i < rows; i++) {// Initialize all distances as -1 (indicating unreachable)
            for (int j = 0; j < cols; j++) {
                distances[i][j] = -1;
            }
        }

        distances[start.getX()][start.getY()] = 0;  // / Set the distance of the start pixel to 0


        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start.getX(), start.getY()});

        // Perform Breadth-First Search (BFS)
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currX = current[0];
            int currY = current[1];

            // Explore the four possible directions: up, down, left, right
            for (int i = 0; i < 4; i++) {
                int newX = currX + dx[i];
                int newY = currY + dy[i];

                // Check if the neighbor is a valid move and has not been visited yet
                if (isValidMove(this._map, newX, newY) && distances[newX][newY] == -1) {
                    distances[newX][newY] = distances[currX][currY] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }

        return new Map(distances);
    }


    private static boolean isValidMove(int[][] grid, int x, int y) {
        int rows = grid.length;
        int cols = grid[0].length;
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] != -1;//if slot it inaccesible return false otherwise true.
    }
    /**
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the pixel Object at that location in the map.
     */

}

