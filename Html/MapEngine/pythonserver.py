#!/usr/bin/python3
import http.server
import socketserver
from urllib.request import urlopen
from localMapService import mapservice
from localMapService import cacheservice
from localMapService import tileset
from localMapService import log

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
			protocol  = "http"
			dbserver = "localhost:3000"
			dbrequest = protocol + "://" + dbserver + self.path
			cservice = cacheservice.Cacheservice()
			content = ""
			if not cservice.isRequestInCache(self.path):
			 html = urlopen(dbrequest)
			 content = html.read()
			 cservice.saveFile(self.path.replace('/','_'),content.decode())

			else:
				content = cservice.getRequest(self.path.replace('/','_'))
				content = bytes(content,'utf-8')

			self.send_response(200)
			self.send_header("Content-type", "text/html")
			self.send_header("Content-length", len(content))
			self.end_headers()
			self.wfile.write(content)

Handler = MyRequestHandler
server = socketserver.TCPServer(('0.0.0.0', 8085), Handler)

server.serve_forever()
