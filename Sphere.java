import java.awt.Color;

public class Sphere {
    public Vector center;
    public Vector color;
    public Vector normal;
    public int rad;
    //t is the scale to find out the closest point where it passes
    private double t;
    //d is the closest point where the ray "passes" the sphere
    private double d;

    public Sphere(Vector center,int rad, Vector color) {
        this.center = center;
        this.rad = rad;
        this.color = color;
    }

    public boolean intersect(Ray ray) {
        t = Vector.dot(ray.direction, Vector.sub(this.center, ray.origin));
        Vector xclose = Vector.add(ray.origin, ray.direction.scale(t));
        d = Vector.sub(xclose, this.center).getMagnitude();
        return d < rad;
    }

    public Ray getReflection(Ray Rin) {
        double a = Math.sqrt(Math.pow(this.rad, 2) - Math.pow(d, 2));
        Vector xhit = Vector.add(Rin.origin, Rin.direction.scale(t-a));
        normal = Vector.sub(xhit, this.center).norm();
        return new Ray
            (Vector.add(Rin.direction, normal.scale(2).scale(Vector.dot(normal, Rin.direction))),
             xhit);
    }
}

