package primitives;

import java.util.Objects;

public class Ray {
    final private Point head;
    final private Vector direction;

    public Ray(Point head, Vector direction) {
        this.head = head;
        if (direction.length()!=1)
        {
            this.direction = direction.normalize();//בדיקה שהנורמל מנורמל אחרת לנרמל
        }
        else
        {
            this.direction= direction;
        }

    }

    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(head, ray.head) && Objects.equals(direction, ray.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, direction);
    }
}
