#!/usr/bin/python3
from PIL import Image
class TileSet:
	#fullpath = "/home/patrick/SVN/NationBuilder/Html/MapEngine/Localpython"
	width = 32
	height = 32 
	_filename = "nan"
	_cservice = "nan"
	def __init__(self,filename,cservice):
		self._filename = filename	
		self._cservice = cservice
		print("initializing tileset")
	def unpack(self):
		#im = Image.open(self.fullpath + "/" + self._filename)
		im = Image.open(self._filename)
		
		#cropped_image = im.crop(crop_rect)
		ts_width, ts_height = im.size
		
		maxbredth =  ts_width/self.width
		maxdepth =   ts_height/self.height
		
		fname =  self._filename.split('/')[1].split('.')[0]
		for x in range(0,int(maxbredth+1)):
			for y in range(0,int(maxdepth+1)):
				
				#print(nodename)
				if (y*self.height) <= ts_height:
					nodename = str(x) + str(y) + fname
					newy=  y*self.height
					newx = x*self.width
					# TODO: not sure if mapping is corect.. depends on x first or y first.. gotta check
					crop_rect = (newx,newy,newx+self.width,newy+self.height)
					cropped_image = im.crop(crop_rect)
					# TODO: if we leave print out it does not work.. there seems to be a timing issue
					print(crop_rect)	
					if not self._cservice.isFileInCache(nodename):
						self._cservice.saveImagePNG(nodename,cropped_image)	
						
		#cropped_image.save("test","png")		
		print("unpacking tileset")
		
		
