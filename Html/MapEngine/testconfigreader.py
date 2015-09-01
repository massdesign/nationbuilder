#!/usr/bin/python3

from nbwebservice.configService import getconfig
from nbwebservice.localMapService import util

config = getconfig.ConfigReader("/home/patrick/Git/nationbuilder/Html/MapEngine/config/config.js")

value = config.getProperty("STATIC_BACKGROUND_PATTERN")
print(value)
