from math import sqrt
import pygame
from light import Light
from shapes import *
from ray import *
from time import sleep

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

def calculate_color(in_rgb_c, normal, light_dir, light_col):
   lambert_term = max((-(light_dir)*normal), 0.0);
   diffuse_term = in_rgb_c.mul(lambert_term)#*(light_col)
   color = Vector(0,0,0)
   color += (in_rgb_c.mul(0.2)) + (diffuse_term)
   return color
   

def clamp(value):
    return Vector(min(value.x,255),min(value.y,255),min(value.z,255))

def main():
   width = 400
   height = 300

   allSpheres = [Shape(Vector(width/2,height/2,50), 50, 0xff0000),
                Shape(Vector(100,100,350), 100, 0x00ff00)]
   allLights = [Light(Vector(0,1000,10), rgb(0xFFFFFFF))]
                #Light(Vector(1000,1000,30), rgb(0xFFFFFF))]

   pygame.init()
   background=pygame.display.set_mode((width, height))
   for x in range(width):
      for y in range(height):
         for s in allSpheres:
            raydirection = Vector((2.0*x/width)-1.0, ((2.0*y/height)-1.0) * float(height)/width, 1)
            primRay = Ray(raydirection, Vector(x,y,0))
            t = primRay.direction * (s.center - primRay.origin)
            xclose = primRay.origin + primRay.direction.mul(t)
            d = (xclose - s.center).getMagnitude()
            if (d > s.rad):
               continue
            else:
            #If it hits
               a = sqrt(s.rad**2 - d**2)
               xhit = primRay.origin + primRay.direction.mul(t - a)
               normal = (((xhit - s.center).norm()))#.mul(0.5)).add(0.5)).norm()

               #Rr = Ri + (2*N*- dot(N,V)
               reflectRay = Ray(primRay.direction + (normal.mul(2)).mul(normal * primRay.direction), xhit) 
               color = Vector(0,0,0)
               for l in allLights:
                  print l.origin
                  light_dir = (xhit - l.origin).norm()
                  color += calculate_color(rgb(s.color),normal, light_dir, l.color)
               background.set_at((x,y), hexcolor(clamp(color)) )
   pygame.display.update()
   print "Done"
   sleep(1203912)

if __name__=="__main__":
   main()
