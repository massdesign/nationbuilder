#!/usr/bin/python3
from PIL import Image
from nbwebservice.localMapService import cacheservice

class Background:
	width  = 32
	height = 32
	# basetile
	bt = None
	# Cacheservie
	cs	= None
	def __init__(self,basetile,cacheservice):
		print("background")
		self.bt = basetile
		self.cs = cacheservice
	def fillbackground(self,size):
		im = Image.new("RGB",(size*self.width,size*self.height),"white")
		bti = self.cs.getImageFile(self.bt)	
		for x in range(0, int(size*self.width),32):
			for y in range(0, int(size*self.height),32):
				im.paste(bti,(x,y))
		return im
	def create(self):
		for x in range(1,5):
			im = self.fillbackground(20*x)
			self.cs.saveImagePNG("testbackground_" + str(x),im)
			print("create background")	