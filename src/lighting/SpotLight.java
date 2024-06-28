package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity, Point position) {
        super(intensity, position);
    }
    @Override
    public SpotLight setKC(double KC) {
       super.setKC(KC);
       return this;
    }
    @Override
    public SpotLight setKL(double KL) {
        super.setKC(KL);
        return this;
    }
    @Override
    public SpotLight setKQ(double KQ) {
        super.setKC(KQ);
        return this;
    }
    @Override
    public Color getIntensity(Point p) {
        Color pointLightIntensity = super.getIntensity(p);
        double factor = Math.max(0, direction.dotProduct(getL(p)));
        return pointLightIntensity.scale(factor);
    }

}
