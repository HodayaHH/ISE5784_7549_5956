package primitives;

import java.util.Objects;

public class Ray {
    final private Point head;
    final private Vector direction;

    //Parameter constructor
    public Ray(Point head, Vector direction) {
        this.head = head;
        if (direction.length() != 1) //Checking whether the length of the direction vector is different from 1
        {
            this.direction = direction.normalize(); //Normalizes the direction vector
        } else {
            this.direction = direction;
        }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(head, ray.head) && Objects.equals(direction, ray.direction);
    }

}
