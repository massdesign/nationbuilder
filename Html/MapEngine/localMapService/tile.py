#!/usr/bin/python3
from PIL import Image
from localMapService import log
from localMapService import util


class Tile:
    _cservice = "nan"
    _size = 32
    _im = None

    def __init__(self, cservice, width, height, parentImage):
        self._cservice = cservice
        self._width = width
        self._height = height
        self._im = parentImage

    def __init__(self, cservice, parentImage):
        self._cservice = cservice
        self._im = parentImage

    def create(self, tilesetName, x, y):
        global cropped_image
        newy = y * self._size
        newx = x * self._size
        nodename = "sx" + str(self._size) + "_" + str(self._size) + "_" + str(x) + str(y) + tilesetName
        crop_rect = (newx, newy, newx + self._size, newy + self._size)
        cropped_image = self._im.crop(crop_rect)
        #cropped_image.show()
        if not self._cservice.isFileInCache(nodename):
            self._cservice.saveImagePNG(nodename, cropped_image)
        global newSize
        newSize = self._size/2
        while newSize > 1:
            log.loginfo("basesize: " + util.ftos(self._size) + " newSize:" + util.ftos(newSize))
            nodename = "sx" + str(int(newSize)) + "_" + str(int(newSize)) + "_" + str(x) + str(y) + tilesetName
            print(nodename)
            if not self._cservice.isFileInCache(nodename):
                size = x,y
                basewidth = int(newSize)
                img = cropped_image
                wpercent = (basewidth/float(img.size[0]))
                hsize = int((float(img.size[1])*float(wpercent)))
                resizedimage = img.resize((basewidth,hsize), Image.ANTIALIAS)
                #resizedimage.show()
                self._cservice.saveImagePNG(nodename, resizedimage)
            newSize /= 2

    def resize(self,x,y):
        if not self._cservice.isFileInCache(nodename):
            size = x,y
            self._cservice.saveImagePNG(nodename, cropped_image)
            self._im.thumbnail(size, Image.ANTIALIAS)