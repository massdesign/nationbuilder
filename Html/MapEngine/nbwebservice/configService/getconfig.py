#!/usr/bin/python3
import re

class ConfigReader:
    filelocation = ""
    def __init__(self,filelocation):
        self.filelocation = filelocation
    def getProperty(self,propname):
        file = open(self.filelocation, 'r')
        for line in file:
            re.compile("Config.*")
            print (line)