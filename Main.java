package exe.ex3;

public class Main {
    public static void main(String[] args) {
        int[][] a1 = {{0, -1, 0, 0},
                {-1, 0, 0, 0},
                {-1, -1, -2, -1},
                {0, 0, 0, 0}
        };



        Pixel2D a3 = new Index2D(2, 2);
        Map2D a2 = new Map(a1);
        Map2D a4 = a2.allDistance(a3, -1);
        int[][] result = a4.getMap();

        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}