#!/usr/bin/python3
from PIL import Image
from localMapService import tile
from localMapService import log


class TileSet:
    # fullpath = "/home/patrick/SVN/NationBuilder/Html/MapEngine/Localpython"
    width = 32
    height = 32
    _filename = "nan"
    _cservice = "nan"
    _tiles = []

    def __init__(self, filename, cservice):
        self._filename = filename
        self._cservice = cservice
        print("initializing tileset")

    def unpack(self):
        #im = Image.open(self.fullpath + "/" + self._filename)
        im = Image.open(self._filename)

        #cropped_image = im.crop(crop_rect)
        ts_width, ts_height = im.size

        maxbredth = ts_width / self.width
        maxdepth = ts_height / self.height

        fname = self._filename.split('/')[1].split('.')[0]
        for x in range(0, int(maxbredth + 1)):
            for y in range(0, int(maxdepth + 1)):
                #print(nodename)
                if (y * self.height) <= ts_height:
                    newTile = tile.Tile(self._cservice, im)
                    newTile.create(fname, x, y);
                    self._tiles.append(newTile)
        #cropped_image.save("test","png")
        print("unpacking tileset")

        def getTiles(self):
            print("getting tiles")