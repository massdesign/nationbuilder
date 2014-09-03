#!/usr/bin/python3
import http.server
import socketserver
from localMapService import mapservice
from localMapService import cacheservice
from localMapService import tileset

class MyRequestHandler(http.server.SimpleHTTPRequestHandler):	
	def createMapCache(self):
		mservice = mapservice.MapsService()
		print(mservice)
		cservice = cacheservice.Cacheservice()
		mapdata =  mservice.getImages()
		for element in mapdata:
			image = 'nan'
			if not cservice.isFileInCache("ts_" + element['name']):
				image = mservice.getImageFile(element['url'])
				cservice.saveFile("ts_" + element['name'],image)
			else:
				image = cservice.getImageFile("ts_" + element['name'])	

			tset = tileset.TileSet(cservice.getFilePath("ts_" + element['name']),cservice)
			tset.unpack()
	def do_GET(self):
		if self.path == '/install':
			self.path = '/install.html'
			self.createMapCache()
			return http.server.SimpleHTTPRequestHandler.do_GET(self)
		elif self.path == '/app':
			self.path = '/mapengine.html'
			return http.server.SimpleHTTPRequestHandler.do_GET(self)
		elif self.path == '/':
			self.path = '/index.html'
			return http.server.SimpleHTTPRequestHandler.do_GET(self)			
		elif self.path == '/test':
	 		self.path = '/test.html'
	 		return http.server.SimpleHTTPRequestHandler.do_GET(self)

		else:
			return http.server.SimpleHTTPRequestHandler.do_GET(self)

Handler = MyRequestHandler
server = socketserver.TCPServer(('0.0.0.0', 8083), Handler)

server.serve_forever()
