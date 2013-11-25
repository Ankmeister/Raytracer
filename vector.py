from math import sin
class Vector:

   def __init__(self,x, y, z):
      self.x = x
      self.y = y
      self.z = z

   def length(self):
      return (self.x**2 + self.y ** 2  + self.z ** 2)**0.5

   def setScale(self, scale):
      self.x *= scale
      self.y *= scale
      self.z *= scale

   def __add__(self,v1):
      vsum = Vector(self.x + v1.x, self.y + v1.y, self.z + v1.z)
      return vsum

   def __sub__(self,v1):
      vdiff = Vector(self.x - v1.x, self.y - v1.y, self.z - v1.z)
      return vdiff

   def __mul__(self,v1):
      return self.x*v1.x + self.y*v1.y + self.z*v1.z
   
def crossp(v1, v2):
   cross.x = v1.y*v2.z - v1.z*v2.y
   cross.y = v1.z*v2.x - v1.x*v2.z
   cross.z = v1.x*v2.y - v1.y*v2.x
   return cross

