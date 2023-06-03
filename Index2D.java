package exe.ex3;
import java.lang.Math;
/**
 This class is the implementation of the attributes of Pixel2D presented in the interface Pixel2D,
 as a single Index;
 */

public class Index2D implements Pixel2D {

    private int _x, _y;// initialization of the members of Index2D class;

    public Index2D() {//when Index2D is initiated without entries, the default is 0,0;
        this(0, 0);
    }

    public Index2D(int x, int y) {//when Index2D is initiated with params x and y, the members of the
                                   //class become x and y;
        _x = x;
        _y = y;
    }

    public Index2D(Pixel2D t) {//when index2D is initiated with a pixel2D object (t)
                               // the members of the class become the x and y of t.
        this(t.getX(), t.getY());
    }

    @Override
    public int getX() {//function to receive the x coordinate of a 2D point in the map;
        return _x;
    }

    @Override
    public int X() {
        return 0;
    }

    @Override
    public int getY() {//function to receive the y coordinate of a 2D point in the map;
        return _y;
    }

    public double distance2D(Pixel2D t) {//function to calculate the Euclidean distance between 2 pixels in the map;
        double ans = 0;//initialize ans to be 0;
        if(t==null) {
        throw new
                RuntimeException("Invalid Input");// if the object is null return invalid input;
        }
        double DeltaXpow = Math.pow(t.getX() - this._x, 2);//
        double DeltaYpow = Math.pow(t.getY() - this._y, 2);//
        ans = Math.sqrt(DeltaXpow + DeltaYpow);//Calculate the distance between the points using pythagoras theorem;
        return ans;
    }

    @Override
    public String toString() {//function to represent a pixel's coordinates as a string;
        return getX() + "," + getY();
    }




    @Override
    public boolean equals(Object t) {//this function checks if two 2D points in the map are equal;

        if (t != null && t instanceof Pixel2D) {//if the object isn't empty, and is a Pixel2D object instance
            Pixel2D p = (Pixel2D) t;//cast the object to be a total Pixel2D object rather than an instance
            if (p.getX() == this._x && p.getY() == this._y) {//if it has the same x y coordinates return true;
                return true;
            }

        }//otherwise false;
        return false;


    }



}

