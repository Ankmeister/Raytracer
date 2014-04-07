import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;

public class Raytracer {
    public Raytracer() {
        final int height = 600;
        final int width = 800;
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        JLabel PicLabel = new JLabel(new ImageIcon(image));

        //Sphere[] allSpheres = 
            //{new Sphere(new Vector(height, width/2, 100000), 80000, new Vector(120, 255, 222)),
             //new Sphere(new Vector(0,900,3000), 1000, new Vector(120, 100, 255)),
             //new Sphere(new Vector(500,500,170), 150, new Vector(255,0,0))};

        ArrayList<Sphere> allSpheres = new ArrayList<Sphere>() {{
            add(new Sphere(new Vector(height, width/2, 100000), 80000, new Vector(120, 255, 222)));
            //add(new Sphere(new Vector(300,500,1000), 700, new Vector(120, 100, 255)));
            //add(new Sphere(new Vector(500,500,170), 150, new Vector(255,0,0)));
            add(new Sphere(new Vector(height/2, width/2,1000), 700, new Vector(120, 100, 255)));
            add(new Sphere(new Vector(height/2, width/2,230), 65, new Vector(120, 100, 255)));
            add(new Sphere(new Vector(height/2, 200 + width/2,160), 40, new Vector(255,0,0)));
            }};

        Light[] allLights = {new Light(new Vector(width,height, -400), new Vector(255, 255, 255))};
                             //new Light(new Vector(width,height,0), new Vector(255, 255, 255))};

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
                    Ray primRay = new Ray(raydirection, new Vector(x,y,0));
                    ArrayList<Sphere> test = (ArrayList<Sphere>) allSpheres.clone();
                    test.remove(sphere);
                    if (sphere.intersect(primRay)) {
                        Ray reflect = sphere.getReflection(primRay);
                        Vector color = new Vector(0,0,0);
                        for (Light light : allLights) {
                        Vector light_dir = Vector.sub(reflect.origin, light.origin).norm();
                            color = Vector.add(color, calculate_color(sphere.color, sphere.normal, light_dir));
                            for (Sphere foo : test) {
                                //shadow
                                if (foo.intersect(new Ray(Vector.sub(reflect.origin,light.origin), light.origin)) &&
                                    Vector.sub(reflect.origin, light.origin).getMagnitude() > Vector.sub(foo.center, light.origin).getMagnitude()) {
                                   color = sphere.color.scale(0.2);
                                }
                            }
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
