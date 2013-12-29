from math import sin
class Vector:

   def __init__(self,x, y, z):
      self.x = x
      self.y = y
      self.z = z

   def getMagnitude(self):
      return (self.x**2 + self.y ** 2  + self.z ** 2)**0.5

   def getMag2(self):
      return self.x**2 + self.y ** 2  + self.z ** 2     

   def setScale(self, scale):
      self.x *= scale
      self.y *= scale
      self.z *= scale
      return self

   def norm(self):
      mag=self.getMagnitude()
      self.x /= mag
      self.y /= mag
      self.z /= mag
      return self
   

   def __add__(self,v1):
      vsum = Vector(self.x + v1.x, self.y + v1.y, self.z + v1.z)
      return vsum

   def __sub__(self,v1):
      vdiff = Vector(self.x - v1.x, self.y - v1.y, self.z - v1.z)
      return vdiff

   def __mul__(self,v1):
      return self.x*v1.x + self.y*v1.y + self.z*v1.z

   def __str__(self):
      return "x: " + str(self.x) + " y: " + str(self.y)  + " z: " + str(self.z)

def crossp(v1, v2):
   cross.x = v1.y*v2.z - v1.z*v2.y
   cross.y = v1.z*v2.x - v1.x*v2.z
   cross.z = v1.x*v2.y - v1.y*v2.x
   return cross

