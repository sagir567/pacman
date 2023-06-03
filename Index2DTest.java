package exe.ex3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 Test Class for Index2D
 */

public class Index2DTest {


    public static Pixel2D p = new Index2D(0,0);

    /**
     Tests Index2D initiates 0 entries as 0,0
     */
    @Test
    public void testIndex2D1(){
        Pixel2D t = new Index2D();
        assertTrue(p.equals(t))  ;
    }
    /**
     Test that when Index2D is initiated with params x and y, the members of the
     //object become x and y;
     */
    @Test
    public void testIndex2D2(){
        Pixel2D t = new Index2D(2,6);
        assertTrue(t.getX()==2&&t.getY()==6)  ;
    }
    /**
      /when index2D is initiated with a pixel2D object (t)
    // the members of the object become the x and y of t.
     */
    @Test
    public void testIndex2D3(){
        Pixel2D t = new Index2D(p);
        assertTrue(t.getX()==0&&t.getY()==0)  ;
    }

    /**
     Tests that GetX and GetY retrieve the correct coordinates from the Pixel2D object.
     */
    @Test
    public void testGetCoordinates(){
        Pixel2D t = new Index2D(2,6);
        assertTrue(t.getX()==2&&t.getY()==6)  ;
    }

    /**
     Tests Distance function for simple case;
     */


    @Test
    public void testDistance(){
        Pixel2D t = new Index2D(3,4);
      assertTrue(p.distance2D(t)==5)  ;
    }
    /**
     Tests Distance between two same points == 0;
     */
    @Test
    public void testDistance2(){
        Pixel2D t = new Index2D(0,0);
        assertTrue(p.distance2D(t)==0)  ;
    }
    /**
     Tests if a (x,y) coordinate is presented as "x,y"
     */
    @Test
    public void testToString(){
        Pixel2D t = new Index2D(8,4);
        assertEquals("8,4",t.toString())  ;
    }
    /**
     Tests if a (-x,-y) coordinate is presented as "-x,-y"
     */
    @Test
    public void testToString2(){
        Pixel2D t = new Index2D(-8,-4);
        assertEquals("-8,-4",t.toString())  ;
    }
    /**
     Tests if two same points are equal according to Equals;
     */
    @Test
    public void testEquals(){
        Pixel2D t = new Index2D(0,0);
        assertTrue(p.equals(t))  ;
    }
    /**
     Tests if two equal points have the same distance from the 0,0 axis of the map;
     */
    @Test
    public void testEquals2(){
        Pixel2D t = new Index2D(3,4);
        Pixel2D h = new Index2D(3,4);
        assertTrue(h.equals(t))  ;
        assertTrue(p.distance2D(t)==p.distance2D(h));
    }


}
