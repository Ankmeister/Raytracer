from PIL import Image
from math import *
import pygame, sys
from pygame.locals import *
from vector import Vector
from shapes import *
from ray import *

def main():
   height = 400
   width = 400

   Sphere1 = Shape(Vector(200,200,100), 100)

   pygame.init()
   background=pygame.display.set_mode((height, width))
   for x in range(height):
      for y in range(width):

         curray = Ray(Vector(0,0,1),Vector(x,y,0))
         t = curray.direction * (Sphere1.position - curray.origin)
         xclose = curray.origin + curray.direction.setScale(t)
         print xclose
         
         if (abs((xclose - Sphere1.position).getMagnitude()) > Sphere1.rad):
            continue
         else:
            background.set_at((x,y), 0xFFFFFF)
   pygame.display.update()
   while True:
      pass

if __name__=="__main__":
   main()
