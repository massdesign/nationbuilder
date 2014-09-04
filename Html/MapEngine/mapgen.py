#!/usr/bin/python3
from localMapService import mapservice
from localMapService import cacheservice
from localMapService import tile
from localMapService import tileset
from localMapService import log

mservice = mapservice.MapsService()
cservice = cacheservice.Cacheservice()
mapdata = mservice.getImages()

for element in mapdata:
    if not cservice.isFileInCache("ts_" + element['name']):
        print("file not found in cache, retrieving file from server")
        image = mservice.getImageFile(element['url'])
        cservice.saveFile("ts_" + element['name'], image)
        tset = tileset.TileSet(cservice.getFilePath("ts_" + element['name']), cservice)
        tset.unpack()
    else:
        image = cservice.getImageFile("ts_" + element['name'])
        tset = tileset.TileSet(cservice.getFilePath("ts_" + element['name']), cservice)
        tset.unpack()



