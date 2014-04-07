public class Vector {
    public double x,y,z;

    public Vector(double x,double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getMagnitude() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector scale(double i) {
        return new Vector(x*i, y*i, z*i);
    }

    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z); 
    }

    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z); 
    }

    public Vector norm() {
        double len = this.getMagnitude();
        return this.scale(1/len);
    }

    public static Vector cross(Vector v1,Vector v2) {
        return new Vector(
                v1.y*v2.z - v1.z*v2.y,
                v1.z*v2.x - v1.x*v2.z,
                v1.x*v2.y - v1.y*v2.x);
    }

    public static double dot(Vector v1,Vector v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z; 
    }

    public Vector neg() {
        return new Vector(
                -this.x,
                -this.y,
                -this.z);
    }

    @Override
    public String toString() {
        return Double.toString(this.x) + " "  + 
               Double.toString(this.y) + " "  + 
               Double.toString(this.z);
    }
}

