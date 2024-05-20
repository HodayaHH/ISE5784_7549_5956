package primitives;

import java.util.Objects;

public class Ray {
    final private Point head;
    final private Vector direction;

    /**
     * Parameter constructor
     * @param head
     * @param direction
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize(); //Normalizes the direction vector
    }

    //Override function toString
    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }

    //override equals function
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return  (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals (other.direction);
    }

}
