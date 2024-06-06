package primitives;

import java.util.Objects;

/**
 * class representing a Ray
 */
public class Ray {
    // The starting point of the ray.
    final private Point head;
    // The direction vector of the ray.
    final private Vector direction;

    /**
     * Constructs a Ray with the specified head point and direction vector.
     *
     * @param head the starting point of the ray
     * @param direction the direction vector of the ray
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
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    /**
     * Returns the head point of the Ray.
     *
     * @return the head point of the Ray
     */
    public Point getHead() {
        return head;
    }

    /**
     * Returns the direction vector of the Ray.
     *
     * @return the direction vector of the Ray
     */
    public Vector getDirection() {
        return direction;
    }

    public Point getPoint(double t)
    {
      return head.add(direction.scale(t));
    }
}
