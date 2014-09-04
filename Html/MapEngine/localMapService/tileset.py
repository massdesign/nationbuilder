#!/usr/bin/python3
from PIL import Image
from localMapService import tile
from localMapService import log


class TileSet:
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
        global im
        im = Image.open(self._filename)
        ts_width, ts_height = im.size

        maxbredth = ts_width / self.width-1
        maxdepth = ts_height / self.height-1
        log.loginfo("width: " + str(maxbredth) + " height: " + str(maxdepth))
        fname = self._filename.split('/')[1].split('.')[0]
        for x in range(0, int(maxbredth + 1)):
            for y in range(0, int(maxdepth + 1)):
                if (y * self.height) <= ts_height:
                    log.loginfo(" processing tile X:" + str(x) + " Y: " + str(y))
                    newTile = tile.Tile(self._cservice, im)
                    newTile.create(fname, x, y);
                    self._tiles.append(newTile)
        print("unpacking tileset")

        def getTiles(self):
            print("getting tiles")