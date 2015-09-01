#!/usr/bin/python3
import re
from nbwebservice.localMapService import util
class ConfigReader:
    filelocation = ""
    def __init__(self,filelocation):
        self.filelocation = filelocation
    def getProperty(self,propname):
        result = None
        file = open(self.filelocation, 'r')
        for line in file:
            compregex = re.compile("Config.*")
            result  = compregex.match(line)
            if result is not None:
             kvp = result.group(0).split('=',1)
             if kvp[0].strip() == str("Config." + propname): 
             	result = kvp[1].strip(';')
             	break;
        # check if it is a boolean		
        result =  result.replace(' ','')
        if util.str2bool(result) is not None:
        		# it is boolean
       		result = util.str2bool(result)
        # Voor nu gaan we er vanuit dat als het geen boolean is dan is het een string		
        return result.replace("\"","")