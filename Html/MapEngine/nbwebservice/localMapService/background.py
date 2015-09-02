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
	def create(self):
		im = Image.new("RGB",(20*self.width,20*self.height),"white")
		#base tile image
		bti = self.cs.getImageFile(self.bt)	
		for x in range(0, int(20*self.width),32):
			for y in range(0, int(20*self.height),32):
				im.paste(bti,(x,y))
		self.cs.saveImagePNG("testbackground",im)
		print("create background")	