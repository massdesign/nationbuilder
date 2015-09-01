#!/usr/bin/python3
import requests
import json
from nbwebservice.localMapService import log

class MapsService:

	#service = '192.168.1.6:3000'	
	service = 'localhost:3000'
	def __init__(self):
			log.loginfo("MapsService started")
	def getImages(self):
			r = requests.get('http://' + self.service + "/images.json")
			return json.loads(r.text)
			
	def getImageFile(self,url):
			r = requests.get('http://' + self.service + '/images/' + url, stream=True)
			return r

			
	
