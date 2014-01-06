import pygame
from light import Light
from sphere import Sphere
from render import *
import time



def main():
   init =time.time()
   width = 800
   height = 600
   allSpheres = [Sphere(Vector(width/2,height/2,450), 50, 0xff0000),
                Sphere(Vector(200,450,250), 100, 0x00ff00)]

   #allLights = [Light(Vector(0,0,10), rgb(0xFFFFFFF)),
   allLights = [Light(Vector(400,300,270), rgb(0xFFFFFF))]

   background=pygame.display.set_mode((width, height))
   render(width,height,allSpheres,allLights,background)
   pygame.init()
   pygame.display.update()
   print "programtime: ",time.time() - init
   time.sleep(1203912)

if __name__=="__main__":
   main()
