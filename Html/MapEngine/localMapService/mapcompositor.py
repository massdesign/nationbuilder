#!/usr/bin/python3
from PIL import Image
class Mapcompositor:
	#TODO: hardcoded config, should be pulled from the backend. Not really important for now.. 
	mapwidth = 40
	mapheight = 80
	twidth = 32
	theight = 32
	map_image = "nan"
	dest_dir = "www"	
	image_name = "map.png"
	def __init__(self):
			print("Map compositor started")
			white = (255,255,255)
			self.map_image = Image.new("RGB",[self.twidth*self.mapwidth,self.theight*self.mapheight],white)
			
	def addChunk(self,offsetx,offsety,xposition,yposition,image):
		print("adding chunk to map_image")
		
	def createImage(self):
		self.map_image.save(self.dest_dir + "/" + self.image_name)
			
		
		
		
	
mcompositor = Mapcompositor()
mcompositor.createImage()


