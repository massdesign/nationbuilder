#!/usr/bin/python3

from nbwebservice.configService import getconfig
from nbwebservice.localMapService import util
from nbwebservice.localMapService import background
from nbwebservice.localMapService import cacheservice

config = getconfig.ConfigReader("/home/patrick/Git/nationbuilder/Html/MapEngine/config/config.js")
cs  =  cacheservice.Cacheservice()
value = config.getProperty("STATIC_BACKGROUND_PATTERN")
bg = background.Background(value,cs)
bg.create()
