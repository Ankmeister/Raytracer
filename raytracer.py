from PIL import Image
from math import *
import pygame, sys
from pygame.locals import *
from vector import Vector
from Sphere import Sphere


def main():
   pygame.init()
   background=pygame.display.set_mode((600,600))
   #Simple drawing test atm
   while True:
      for i in range(255):
         for j in range(255):
            background.set_at((i,j), 0xFFFFFF)
      pygame.display.update()

if __name__=="__main__":
   main()
