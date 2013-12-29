from vector import *

class Ray:
   def __init__(self,direction, origin):
      self.direction = direction.norm()
      self.origin = origin
