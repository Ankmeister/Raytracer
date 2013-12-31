from math import sqrt
import pygame
from light import Light
from sphere import *
from ray import *
from time import sleep
from render import *


def clamp(value):
    return Vector(min(value.x,255),min(value.y,255),min(value.z,255))

def main():
   width = 400
   height = 300
   allSpheres = [Sphere(Vector(width/2,height/2,50), 50, 0xff0000),
                Sphere(Vector(100,100,350), 100, 0x00ff00)]

   allLights = [Light(Vector(0,1000,10), rgb(0xFFFFFFF))]
                #Light(Vector(1000,1000,30), rgb(0xFFFFFF))]

   background=pygame.display.set_mode((width, height))
   render(width,height,allSpheres,allLights,background)
   pygame.init()
   pygame.display.update()
   print "Done"
   sleep(1203912)

if __name__=="__main__":
   main()
