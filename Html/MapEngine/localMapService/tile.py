#!/usr/bin/python3
from PIL import Image


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
        # http://stackoverflow.com/questions/273946/how-do-i-resize-an-image-using-pil-and-maintain-its-aspect-ratio
        print("creating tileset")
        if not self._cservice.isFileInCache(nodename):
            self._cservice.saveImagePNG(nodename, cropped_image)
        global newSize
        newSize = self._size/2
        while newSize > 0:
            nodename = "sx" + str(newSize) + "_" + str(newSize) + "_" + str(x) + str(y) + tilesetName

            newSize /= 2
            if not self._cservice.isFileInCache(nodename):
                size = x,y
                self._im.thumbnail(size, Image.ANTIALIAS)
           #     self._cservice.saveImagePNG(nodename, cropped_image)

            break

    def resize(self,x,y):
        if not self._cservice.isFileInCache(nodename):
            size = x,y
            self._cservice.saveImagePNG(nodename, cropped_image)
            self._im.thumbnail(size, Image.ANTIALIAS)