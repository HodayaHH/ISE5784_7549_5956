package primitives;

// class representing a vector
public class Vector extends Point {
    //Parameter constructor
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO) )
        {
            throw new IllegalArgumentException(""); // אי אפשר וקטור האפס
        }
    }

    // Parameter constructor
    public Vector(Double3 double3) {
        super(double3);
        if (double3.equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("zero vector"); // אי אפשר וקטור האפס
        }

    }

    //adding of 2 vectors (returns a new vector)
    public Vector add(Vector v1) {
        return new Vector(xyz.add(v1.xyz));
    }

    //Multiplies a vector by a number - a scalar (returns a new vector)
    public Vector  scale (double rhs){
        return new Vector(xyz.scale(rhs));
    }

    //function that multiplies a vector by a number
    public double dotProduct(Vector v1){
        return this.xyz.d1*v1.xyz.d1 +this.xyz.d2*v1.xyz.d2 +this.xyz.d3*v1.xyz.d3;
    }

    //function that calculates a vector product
    public Vector crossProduct (Vector v1) {
        return new Vector(this.xyz.d2*v1.xyz.d3-this.xyz.d3*v1.xyz.d2,
                          this.xyz.d3*v1.xyz.d1-this.xyz.d1*v1.xyz.d3 ,
                          this.xyz.d1*v1.xyz.d2-this.xyz.d2*v1.xyz.d1);
    }

    // Calculation of the squared length of the vector - (|vector|^2) = vector^2
    public double lengthSquared(){
        return xyz.d1*xyz.d1+xyz.d2*xyz.d2+xyz.d3*xyz.d3;
    }

    //Calculate the length of the vector
    public double length(){
        return Math.sqrt(lengthSquared());
    }

    //A normalization method that returns a new normalized vector - (a unit vector in the same direction as
    // the original vector)
    public Vector normalize(){
        double l1= length();
        return  new Vector(xyz.d1/l1,xyz.d2/l1,xyz.d3/l1);
    }

}
