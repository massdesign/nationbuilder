#!/usr/bin/python3
import mapservice
import cacheservice
import tileset

mservice = mapservice.MapsService()
cservice = cacheservice.Cacheservice()
mapdata =  mservice.getImages()

for element in mapdata:
	if not cservice.isFileInCache("ts_" + element['name']):
		image = mservice.getImageFile(element['url'])
		print(image.status_code)
		cservice.saveFile("ts_" + element['name'],image)
	else:
		image = cservice.getImageFile("ts_" + element['name'])	
	tset = tileset.TileSet(cservice.getFilePath("ts_" + element['name']),cservice)
	tset.unpack()
		



