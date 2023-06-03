
    package exe.ex3;

    import org.junit.jupiter.api.Test;

    import java.util.Arrays;

    import static org.junit.jupiter.api.Assertions.*;

       public class MapTest {
//
//           /**
//            * This test verifies the correctness of the floodFill method. It creates two maps (map1 and map2),
//            * performs the flood fill operation on map1, and then asserts that map1 is equal to map2.
//            */
//           @Test
//           void TestFloodFill() {
//               int[][] map1 = {
//                       {0, -1, -1,0},
//                       {-1, 0, 0,-1},
//                       {-1, 0, 0,-1}
//               };
//               int[][] map2 = {
//                       {0, -1, -1,0},
//                       {-1, 3, 3,-1},
//                       {-1, 3, 3,-1}
//               };
//               floodFill(map1,2,2,0,3);
//               assertArrayEquals(map1,map2);
//
//           }
//           /**
//            This test checks the functionality of the isValidMove method.
//            It creates a map (map1) and asserts that the move to position (2, 2) is valid.
//            */
//           @Test void TestIsValidMove(){
//               int[][] map1 = {
//                       {0, -1, -1,0},
//                       {-1, 0, 0,-1},
//                       {-1, 0, 0,-1}
//               };
//               assertTrue(isValidMove(map1,2,2));
//           }
//
//
//
///**
// This test validates the correctness of the fill method. It creates two maps (map1 and map2), creates a new Map object (map3), performs the flood fill operation on map1,
// and then asserts that the result of fill method on position (2, 2) is equal to map2.
// */
//           @Test
//           void TestFill() {
//               int[][] map1 = {
//                       {0, -1, -1,0},
//                       {-1, 0, 0,-1},
//                       {-1, 0, 0,-1}
//               };
//               int[][] map2 = {
//                       {0, -1, -1,0},
//                       {-1, 3, 3,-1},
//                       {-1, 3, 3,-1}
//               };
//               Map2D map3 = new Map(map1);
//               floodFill(map1,2,2,0,3);
//               Pixel2D s1= new Index2D(2,2);
//
//               assertEquals(fill(s1,3),map2);
//
//           }

/**
Test if a pixel that is inside the map asserts true.
 */

           @Test
           void isInside() {
                   int[][] map = {
                           {0, 0, 0},
                           {0, 0, 0},
                           {0, 0, 0}
                   };
                   Map2D map2 = new Map(map);


                   Pixel2D p1 = new Index2D(1, 1);
                   assertTrue(map2.isInside(p1));
           }

           @Test
           void isCyclicTest() {//tests if the map is cyclic which this one is.
               Map a1 = new Map(3, 3, 0);
               assertTrue(a1.isCyclic());
           }

           @Test
           void isCyclic1Test() {//test if the map is cyclic even though it has obstacles on both sides of it
               Map a1 = new Map(3, 3, 0);
               a1.setPixel(0, 0, -1);
               a1.setPixel(2, 2, -1);
               assertTrue(a1.isCyclic());
           }

           @Test
           public void isNeighborsNotCyclicTest() {//checking 2 vertically neighbored points.
               Map a1 = new Map(3, 3, -1);
               Pixel2D p1 = new Index2D(2, 3);
               Pixel2D p2 = new Index2D(2, 4);
               assertTrue(a1.isNeighbors(p1, p2));
           }


           @Test
           void isNeighborsNotCyclic2Test() {//checking 2 horizontally neighbored points.
               Map a1 = new Map(4, 4, -1);
               Pixel2D p1 = new Index2D(0, 1);
               Pixel2D p2 = new Index2D(0, 2);
               assertTrue(a1.isNeighbors(p1, p2));
           }

           @Test
           void isNeighborsCyclic1Test() {//checking on a cyclic map
               Map a1 = new Map(3, 3, 0);
               Pixel2D p1 = new Index2D(0, 1);
               Pixel2D p2 = new Index2D(2, 1);
               assertTrue(a1.isNeighbors(p1, p2));
           }


           @Test
           public void isNeighborsCyclic2Test() {//checking neighbors on a cyclic map

               Map a1 = new Map(3, 3, 0);
               Pixel2D p1 = new Index2D(1, 0);
               Pixel2D p2 = new Index2D(1, 2);
               assertTrue(a1.isNeighbors(p1, p2));


           }
/**
 t creates a map (a1) and performs the allDistance operation with a pixel (a) and obstacle color -1.
 It then compares the obtained distances with the expected distances from a pre-defined map (a2).
 */
           @Test
           void allDistanceTest() {
               Pixel2D a = new Index2D(2, 2);
               Map a1 = new Map(4, 4, 0);
               a1.setPixel(0, 1, -1);
               a1.setPixel(1, 0, -1);
               a1.setPixel(2, 0, -1);
               a1.setPixel(2, 1, -1);
               a1.setPixel(2, 3, -1);
               a1.setPixel(2, 2, -2);
               Map2D a3 = a1.allDistance(a, -1);
               Map a2 = new Map(4, 4, 0);
               a2.setPixel(0, 0, -1);
               a2.setPixel(0, 1, -1);
               a2.setPixel(0, 2, 2);
               a2.setPixel(0, 3, 3);
               a2.setPixel(1, 0, -1);
               a2.setPixel(1, 1, 2);
               a2.setPixel(1, 2, 1);
               a2.setPixel(1, 3, 2);
               a2.setPixel(2, 0, -1);
               a2.setPixel(2, 1, -1);
               a2.setPixel(2, 2, 0);
               a2.setPixel(2, 3, -1);
               a2.setPixel(3, 0, 3);
               a2.setPixel(3, 1, 2);
               a2.setPixel(3, 2, 1);
               a2.setPixel(3, 3, 2);


               for (int i = 0; i < 4; i++) {
                   for (int j = 0; j < 4; j++) {

                       assertEquals(a3.getPixel(i, j), a2.getPixel(i, j));


                   }

               }


           }

               @Test
               void shortestPathTest() {
                   Map a2 = new Map(4, 4, 0);
                   a2.setPixel(0, 1, -1);
                   a2.setPixel(1, 0, -1);
                   a2.setPixel(0, 2, -1);
                   a2.setPixel(2, 0, -1);
                   a2.setPixel(2, 1, -1);
                   a2.setPixel(2, 2, 0);
                   a2.setPixel(2, 3, -1);
                   Pixel2D p1= new Index2D(2,2);
                   Pixel2D p2= new Index2D(0,3);

                   Map2D a3 = a2.allDistance(p1,-1);
                   Pixel2D[] shortestP =a3.shortestPath(p1,p2,-1);
                   Pixel2D p4= new Index2D(2,2);
                   Pixel2D p5= new Index2D(1,2);
                   Pixel2D p6= new Index2D(0,2);
                   Pixel2D p7= new Index2D(1,3);
                   Pixel2D p8= new Index2D(0,3);

                   Pixel2D[]expectedP1= {p4,p5,p6,p8};
                   Pixel2D[]expectedP2= {p4,p5,p7,p8};
                   boolean t1=Arrays.equals(shortestP, expectedP1);
                   boolean t2=Arrays.equals(shortestP, expectedP2);


                   assertTrue(t1 || t2);


               }
       }

