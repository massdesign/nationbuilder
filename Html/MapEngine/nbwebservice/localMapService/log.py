#!/usr/bin/python3
import os.path
global WARN
WARN = "WARN"
global ERR
ERR  = "ERROR"
global INFO
INFO = "INFO"


def log(message,level):
    result = ""
    if level == "WARN":
        result = "WARNING: " + message
    elif level == "ERROR":
        result = "ERROR: " + message
    elif level == "INFO":
        result = "INFO: " + message
    
    if os.path.isfile('ps.log') is not True:
    	file = open('ps.log', 'w+')
    else: 
      file = open('ps.log', 'a') 	
    file.write(result + "\n")
    
    file.close()
    print(result)

def loginfo(message):
    log(message,INFO)


def logerror(message):
    log(message,ERR)

def logwarning(message):
    log(message,WARN)