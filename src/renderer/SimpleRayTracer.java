package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

public class SimpleRayTracer extends RayTracerBase {
    private static final double EPS = 0.1;
    /**
     * Maximum recursion level for color calculations. This limits the depth of recursion to avoid infinite loops
     * and improve performance.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * Minimum attenuation coefficient for color calculations. If the coefficient is smaller than this value,
     * the recursion stops. This helps to ignore negligible contributions and improve performance.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

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
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    private static final Double3 INITIAL_K = new Double3(1.0);
    /**
     * Calculates the color at a given intersection point considering local and global lighting effects.
     *
     * @param geoPoint The geometric point of intersection.
     * @param ray      The ray that hit the intersection point.
     * @return The color at the intersection point including ambient, local, and global lighting effects.
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Recursive method to calculate the color at a given intersection point considering local and global lighting effects.
     *
     * @param gp    The geometric point of intersection.
     * @param ray   The ray that hit the intersection point.
     * @param level The current recursion level.
     * @param k     The attenuation coefficient for color calculations.
     * @return The color at the intersection point including local and global lighting effects.
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray,k);
        return level == 1 ? color :
                color.add(calcGlobalEffects(gp, ray, level, k));

    }
    /**
     * Calculates the global lighting effects (reflection and refraction) for a given intersection point.
     *
     * @param gp    The geometric point of intersection.
     * @param ray   The ray that hit the intersection point.
     * @param level The current recursion level.
     * @param k     The attenuation coefficient for color calculations.
     * @return The color at the intersection point including global lighting effects.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Vector v=ray.getDirection();
        Vector n =gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp, v, n), material.kR, level, k)
                .add(calcGlobalEffect(constructRefractedRay(gp, v, n), material.kT, level, k));

    }
    /**
     * Calculates the color contribution from reflection or refraction for a given ray.
     *
     * @param ray The ray to calculate reflection or refraction.
     * @param kx  The attenuation coefficient for the current effect.
     * @param level The current recursion level.
     * @param k The overall attenuation coefficient for color calculations.
     * @return The color contribution from reflection or refraction.
     */
    private Color calcGlobalEffect(Ray ray,Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        return gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }
    /**
     * Finds the closest intersection point of a ray with the geometries in the scene.
     *
     * @param ray The ray for which intersections are to be found.
     * @return The closest intersection point.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersectionPoints = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersectionPoints);
    }
    /**
     * Constructs a reflected ray from a given intersection point and incident ray direction.
     *
     * @param geoPoint The intersection point.
     * @param v        The incident ray direction.
     * @param n        The normal vector at the intersection point.
     * @return The reflected ray.
     */
    private Ray constructReflectedRay(GeoPoint geoPoint, Vector v, Vector n) {
        double vn = v.dotProduct(n);
        if (Util.isZero(vn)) return null; // No reflection if the direction is perpendicular to the normal
        Vector r = v.subtract(n.scale(2 * vn)).normalize(); // Calculate the reflection direction
        if(n.dotProduct(v)>0)
            return new Ray(geoPoint.point, r, n.scale(-1));
        return new Ray(geoPoint.point, r, n); // Create a ray with point displacement
    }
    /**
     * Constructs a refracted ray from a given intersection point, incident ray direction, and normal vector.
     *
     * @param geoPoint The intersection point.
     * @param v        The incident ray direction.
     * @param n        The normal vector at the intersection point.
     * @return The refracted ray.
     */
    private Ray constructRefractedRay(GeoPoint geoPoint, Vector v, Vector n) {
        if(n.dotProduct(v)>0)
            return new Ray(geoPoint.point, v, n);
        return new Ray(geoPoint.point, v, n.scale(-1)); // Create a ray with point displacement in the opposite direction of the normal
    }

    /**
     * Determines the transparency level of a point on a geometry with respect to a light source.
     *
     * @param gp the point on the geometry
     * @param light the light source
     * @param l the vector from the point to the light source
     * @param n the normal vector to the surface at the point
     * @return the transparency level as a Double3
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {//good:)

        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nv < 0 ? EPS : -EPS);
        Point point = gp.point.add(epsVector);
        Ray ray = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections==null)return Double3.ONE;

        Double3 ktr = Double3.ONE;

        double lightDistance = light.getDistance(point);
        for (GeoPoint intersection : intersections) {
            if (intersection.point.distance(point) <= lightDistance) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO; // If transparency is too low, return zero
            }
        }
        return ktr;

    }

    /**
     * Calculate the local effects of lighting on a point.
     *
     * @param gp  the point on the geometry
     * @param ray the ray that hit the point
     * @return the color at the point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {//good:)
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection().normalize();
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;

        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();

        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = Util.alignZero(n.dotProduct(l));

            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, l, n,nv);
                if (!ktr.lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)
                                    .add(calcSpecular(material, n, l, nl, v)))
                    );
                }
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

}


