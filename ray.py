from vector import *

class Ray:
   def __init__(self,direction, origin):
      """t is the scalefactor at the ray's hitpoint with a sphere"""
      self.direction = direction.norm()
      self.origin = origin
      self.t = 0
