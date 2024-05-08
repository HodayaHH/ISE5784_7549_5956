package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO) )
        {
            throw new IllegalArgumentException(""); // אי אפשר וקטור האפס
        }
    }


    public Vector(Double3 double3) {
        super(double3);
        if (double3.equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("zero vector"); // אי אפשר וקטור האפס
        }

    }

    public Vector add(Vector v1) {
        return new Vector(xyz.add(v1.xyz));
    }

//    public Vector  scale (Double3 p1){
//        return new Vector()
//
//    }


}
