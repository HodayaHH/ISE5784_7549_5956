package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    protected Point position;
    private double kC=1.0;
    private double kL=0.0;
    private double kQ=0.0;

    public PointLight setKC(double KC) {
        this.kC = KC;
        return this;
    }

    public PointLight setKL(double KL) {
        this.kL = KL;
        return this;
    }

    public PointLight setKQ(double KQ) {
        this.kQ = KQ;
        return this;
    }

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        double attenuation = kC + kL * d + kQ * d * d;
        return getIntensity().reduce((int) attenuation);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
