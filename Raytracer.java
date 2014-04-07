import java.awt.*;
import java.awt.image.*;
import javax.swing.*;


public class Raytracer {
    public double t;
    public double d;
    public Vector normal;
    
    public Raytracer() {
        int height = 600;
        int width = 800;
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        JLabel PicLabel = new JLabel(new ImageIcon(image));

        Sphere[] allSpheres = 
            {new Sphere(new Vector(height, width/2, 100000), 80000, new Vector(120, 255, 222)),
             new Sphere(new Vector(0,900,3000), 1000, new Vector(120, 100, 255)),
             new Sphere(new Vector(500,500,170), 150, new Vector(255,0,0))};

        Light[] allLights = {new Light(new Vector(1600,200,0), new Vector(255, 255, 255)),
                             new Light(new Vector(width,height,0), new Vector(255, 255, 255))};

        panel.add(PicLabel);
        panel.setPreferredSize(new Dimension(height,width));
        frame.setSize(width,height);
        frame.add(panel);
        frame.setVisible(true);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (Sphere sphere : allSpheres) {
                    Vector raydirection = new Vector(
                            (2.0*x/width)-1.0,
                           ((2.0*y/height)-1.0) * height/width,
                            1);
                    //d is the closest point where the ray "passes" the sphere
                    //t is the scale to find out the closest point where it passes
                    Ray primRay = new Ray(raydirection, new Vector(x,y,0));
                     t = Vector.dot(primRay.direction, Vector.sub(sphere.center, primRay.origin));
                    Vector xclose = Vector.add(primRay.origin, primRay.direction.scale(t));
                     d = Vector.sub(xclose, sphere.center).getMagnitude();
                    //if it hits
                    if (d < sphere.rad) {
                        Ray reflect = getReflection(primRay, sphere);
                        Vector color = new Vector(0,0,0);
                        for (Light light : allLights) {
                        Vector light_dir = Vector.sub(reflect.origin, light.origin).norm();
                            color = Vector.add(color, calculate_color(sphere.color, normal, light_dir));
                        }
                        image.setRGB(x,y, hexcolor(clamp(color))); 
                    }
                }
            }
        }
        frame.repaint();
        System.out.println("slut");
    }


   public Vector calculate_color(Vector in_rgb_c, Vector normal, Vector light_dir) {
       Vector l = light_dir.norm();
       double lambert_term = Math.max(Vector.dot(l.neg(), normal), 0);
       Vector diffuse_term = in_rgb_c.scale(lambert_term);
       return Vector.add(diffuse_term, in_rgb_c.scale(0.2));
   }

    public Ray getReflection(Ray Rin, Sphere sphere) {
        double a = Math.sqrt(Math.pow(sphere.rad, 2) - Math.pow(d, 2));
        Vector xhit = Vector.add(Rin.origin, Rin.direction.scale(t-a));
        normal = Vector.sub(xhit, sphere.center).norm();
        return new Ray
            (Vector.add(Rin.direction, normal.scale(2).scale(Vector.dot(normal, Rin.direction))),
            xhit);
    }

    public Vector clamp(Vector v) {
        return new Vector(Math.min(v.x,255),Math.min(v.y,255),Math.min(v.z,255));
    }


    public int hexcolor(Vector RGB) {
        int total = 0;
        total += Math.round(RGB.x) << 16; 
        total += Math.round(RGB.y) << 8; 
        total += Math.round(RGB.z);
        return total;
    }

    public static void main(String[] args) {
        new Raytracer();
    }
}




    //public int lolol(int x, int y, int a) {
        ////double ans = (x*x - 1)*(x - 2)*(x-2) / (y*y+2);
        ////double ans = Math.pow(Math.log(35* y + x), 2)* 1500 + Math.sin(x + 5000) * 1500+ a;
        ////double ans = Math.cos(Math.sin(Math.cos(Math.tan(Math.sin(x*y))))) * x + a ;
        ////double ans = Math.sin(Math.cos(Math.tan(Math.sin(Math.pow(x,y*0.0015))))) * x + 0x5500 ;
        ////double ans = Math.sin(Math.cos(Math.tan(Math.sin(Math.pow(x,y*0.0015))))) * x + a ;
        //double ans = Math.abs(Math.pow(x-y*x,y*0.1)) + 0.1*Math.abs(Math.pow(y-x,x*0.1));
        //return (int) ans;
    //}
