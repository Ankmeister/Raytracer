from vector import Vector

class Sphere:
   def __init__(self,center,rad, color):
      self.rad = rad
      self.center = center
      self.color = color
      self.d = 0
      self.normal = Vector(0,0,0)


   def isHit(self):
      return self.d < self.rad
