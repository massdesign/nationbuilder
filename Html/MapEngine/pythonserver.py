#!/usr/bin/python3
import http.server
import socketserver
import cgi
from urllib.request import Request
from urllib.request import urlopen
from nbwebservice.localMapService import mapservice
from nbwebservice.localMapService import cacheservice
from nbwebservice.localMapService import tileset
from nbwebservice.localMapService import log
from nbwebservice.localMapService import background
from nbwebservice.configService import getconfig
from subprocess import call

class MyRequestHandler(http.server.SimpleHTTPRequestHandler):	
	commonConfigFile = "/home/patrick/Git/nationbuilder/Html/MapEngine/config/config.js"
	def createMapCache(self):
		
		bg = None
		mservice = mapservice.MapsService()
		print(mservice)
		cservice = cacheservice.Cacheservice()
		config = getconfig.ConfigReader(self.commonConfigFile)
		if config.getProperty("RENDER_STATIC_BACKGROUND") is True:
			log.loginfo("generating static background in 20x20x32 for tilemap")
			bg = background.Background(config.getProperty("STATIC_BACKGROUND_PATTERN"),cservice)
			
				
		mapdata =  mservice.getImages()
		for element in mapdata:
			image = 'nan'
			if not cservice.isFileInCache("ts_" + element['name']):
				image = mservice.getImageFile(element['url'])
				cservice.saveBinaryFile("ts_" + element['name'],image)
			else:
				image = cservice.getImageFile("ts_" + element['name'])	

			tset = tileset.TileSet(cservice.getFilePath("ts_" + element['name']),cservice)
			tset.unpack()
			if bg is not None:
				bg.create()
	def do_POST(self):

		content_len = int(self.headers['content-length'])
		post_body = self.rfile.read(content_len)
		protocol  = "http"
		dbserver = "localhost:3000"
		dbrequest = protocol + "://" + dbserver + self.path
		postrequest = Request(dbrequest,post_body)
		postrequest.get_method = lambda: 'POST'
		postrequest.add_header("Content-type", "application/json")
		
		requestresponse = urlopen(postrequest)
		content = requestresponse.read()
		self.send_response(requestresponse.getcode())
		self.send_header("Content-type", "application/json")
		self.send_header("Content-length", len(content))
		self.end_headers()
		self.wfile.write(content)
		log.loginfo(dbrequest)
		#return http.server.SimpleHTTPRequestHandler.do_POST(self)
	def do_GET(self):
		disableCache = True
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
		elif self.path == '/resetdb':
			self.path = '/resetdb.html'
			call(["./resetdb.sh"])
			return http.server.SimpleHTTPRequestHandler.do_GET(self)
		elif self.path == '/createdb':
			self.path = '/createdb.html'
			call(["./createdb.sh"])
			return http.server.SimpleHTTPRequestHandler.do_GET(self)
		elif self.path == '/deploydb':
			self.path = '/deploydb.html'
			call(["./deploydb.sh"])
			return http.server.SimpleHTTPRequestHandler.do_GET(self)
		else:
			protocol  = "http"
			dbserver = "localhost:3000"
			dbrequest = protocol + "://" + dbserver + self.path
			cservice = cacheservice.Cacheservice()
			content = ""
			# TODO: assets opnemen als een data die tijdens install mee moet komen 
			if self.path.endswith("js") or ("ncache" in self.path) or self.path.endswith("css") or ("assets" in self.path):
				log.loginfo("non cacheable resource requested")
				return http.server.SimpleHTTPRequestHandler.do_GET(self)
			if disableCache:
				log.loginfo("cacheservice disabled")
				html = urlopen(dbrequest)
				content = html.read()
			elif not cservice.isRequestInCache(self.path):
			 log.loginfo("resource not found in cache")
			 html = urlopen(dbrequest)
			 content = html.read()
			 cservice.saveStringFile(self.path.replace('/','_'),content.decode())
			else:
				content = cservice.getRequest(self.path.replace('/','_'))
				content = bytes(content,'utf-8')

			self.send_response(200)
			self.send_header("Content-type", "application/json")
			self.send_header("Content-length", len(content))
			self.end_headers()
			self.wfile.write(content)

Handler = MyRequestHandler
server = socketserver.TCPServer(('0.0.0.0', 8083), Handler)

server.serve_forever()
