#!/usr/bin/python3
import os
import shutil
import numpy
from PIL import Image
from localMapService import log


class Cacheservice:
    fullpath = "/home/patrick/Thuis/Git/nationbuilder/Html/MapEngine/"
    cachedir = "ncache"

    def __init__(self):
        self._createcache()
        print("MapsService started")

    def saveFile(self, filename, data):
        print("File saved: " + self.cachedir + "/" + filename)
        with open(self.cachedir + "/" + filename, 'wb') as out_file:
            for chunk in data.iter_content(1024):
                out_file.write(chunk)

    def saveImagePNG(self, filename, image):
        filepath = self.cachedir + "/" + filename
        log.loginfo("writing file: " + filepath)
        image.save(self.cachedir + "/" + filename, "png")

    def isFileInCache(self, filename):
        return os.path.isfile(self.fullpath + "/" + self.cachedir + "/" + filename)

    def getImageFile(self, filename):
        filepath = self.cachedir + "/" + filename
        return Image.open(filepath)

    def getFilePath(self, filename):
        return self.cachedir + "/" + filename

    def getTextFile(self, filename):
        print("file loaded")

    def _createcache(self):
        if not os.path.exists(self.cachedir):
            os.makedirs(self.cachedir)
