public class Ray {
    public Vector direction, origin;

    public Ray(Vector direction, Vector origin) {
        this.direction=direction.norm();;
        this.origin=origin;
    }
}

