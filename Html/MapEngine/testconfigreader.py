#!/usr/bin/python3

from nbwebservice.configService import getconfig

config = getconfig.ConfigReader("/home/patrick/Git/nationbuilder/Html/MapEngine/config/config.js")

config.getProperty("test1234")