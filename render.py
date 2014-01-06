from vector import *
from ray import *
from light import Light
from math import sqrt
import time

def render(width,height,allSpheres,allLights,background):
   for x in range(width):
      for y in range(height):
         init = time.time()
         for sphere in allSpheres:
            raydirection = Vector((2.0*x/width)-1.0, ((2.0*y/height)-1.0) * float(height)/width, 1)
            primRay = Ray(raydirection, Vector(x,y,0))
            primRay.t = primRay.direction * (sphere.center - primRay.origin)
            xclose = primRay.origin + primRay.direction.mul(primRay.t)
            sphere.d = (xclose - sphere.center).getMagnitude()

            print (time.time() - init) * 800*600
            if (sphere.isHit()):
               reflectR = getReflection(primRay, sphere)
               color = Vector(0,0,0)
               for l in allLights:
                  light_dir = (reflectR.origin - l.origin).norm()
                  color += calculate_color(rgb(sphere.color),sphere.normal, light_dir, l.color)
               background.set_at((x,y), hexcolor(clamp(color)) )
            else:
               continue

def getReflection(Rin, sphere):
   a = sqrt(sphere.rad**2 - sphere.d**2)
   xhit = Rin.origin + Rin.direction.mul(Rin.t - a)
   sphere.normal = (((xhit - sphere.center).norm()))#.mul(0.5)).add(0.5)).norm()
   return Ray(Rin.direction + (sphere.normal.mul(2)).mul(sphere.normal * Rin.direction), xhit) 
   
def calculate_color(in_rgb_c, normal, light_dir, light_col):
   lambert_term = max((-(light_dir)*normal), 0.0);
   diffuse_term = in_rgb_c.mul(lambert_term)#*(light_col)
   color = Vector(0,0,0)
   color += (in_rgb_c.mul(0.2)) + (diffuse_term)
   return color

def rgb(HEX):
   HEX = str(hex(HEX))[2:]
   HEX = HEX.zfill(6)
   codes = map(lambda x:int(x,16), (HEX[0:2], HEX[2:4], HEX[4:6]))
   return Vector(codes[0],codes[1],codes[2])

def hexcolor(RGB):
   total = 0
   total += int(RGB.x) << 16
   total += int(RGB.y) << 8
   total += int(RGB.z)
   return total

def clamp(value):
    return Vector(min(value.x,255),min(value.y,255),min(value.z,255))
