package renderer;

import lighting.LightSource;
import primitives.Color;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

public class SimpleRayTracer extends RayTracerBase {
    /**
     * Constructor for SimpleRayTracer.
     *
     * @param scene The scene to be rendered.
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Abstract method to trace a ray in the scene and determine its color.
     *
     * @param ray The ray to be traced
     * @return The color of the intersection point of the ray in the scene
     */
    @Override
    public Color traceRay(Ray ray) {

        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null
                ? scene.background
                : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * Calculate the color of a point.
     * For now, this method only returns the ambient light color of the scene.
     *
     * @param geoPoint the point for which to calculate the color
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(geoPoint, ray));
    }


    /**
     * Calculate the local effects of lighting on a point.
     *
     * @param gp  the point on the geometry
     * @param ray the ray that hit the point
     * @return the color at the point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection().normalize();
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;

        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();

        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = Util.alignZero(n.dotProduct(l));

            if (nl * nv > 0 && unshaded(gp, lightSource, l, n, nl)) {   //**
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(
                        iL.scale(calcDiffusive(material, nl)
                                .add(calcSpecular(material, n, l, nl, v)))
                );
            }

        }
        return color;
    }

    /**
     * Calculate the specular component of the Phong model.
     *
     * @param material The material of the object.
     * @param n        The normal vector to the surface at the hit point.
     * @param l        The vector from the light source to the hit point.
     * @param nl       Dot product of the normal vector and the vector l.
     * @param v        The vector from the hit point towards the camera.
     * @return The specular component as a Double3.
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl)).normalize(); // reflection direction  לפי הנוסחה
        double vr = Util.alignZero(v.dotProduct(r));

        if (vr >= 0) {
            return Double3.ZERO; // no specular reflection
        }

        double specular = Math.pow(Math.max(0, -vr), material.nShininess);//Calculated according to Fong's light return model
        return material.Ks.scale(specular);
    }

    /**
     * Calculate the diffusive component of the Phong model.
     *
     * @param material The material of the object.
     * @param nl       Dot product of the normal vector and the vector l.
     * @return The diffusive component as a Double3.
     */
    private Double3 calcDiffusive(Material material, double nl) {

        return material.Kd.scale(Math.abs(nl));
    }


    private static final double EPS = 0.1;

    /**
     * Check if a point is unshaded by any objects.
     *
     * @param gp    The point on the geometry.
     * @param light The light source.
     * @param l     The vector from the light source to the hit point.
     * @param n     The normal vector to the surface at the hit point.
     * @param nl    Dot product of the normal vector and the vector l.
     * @return True if the point is unshaded, false otherwise.
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? EPS : -EPS);
        Point point = gp.point.add(epsVector);
        Ray ray = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersectionsHelper(ray);
        return intersections == null;


//        if (intersections==null)return true;
//        else{
//            for (GeoPoint intersection : intersections) {
//                if (point.distance(ray.getHead())>light.getDistance(point))
//                {
//                    return false; // יש צל
//                }
//            }
//            return true; // אין צל
//        }
    }
}


