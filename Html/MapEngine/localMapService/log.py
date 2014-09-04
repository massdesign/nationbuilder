#!/usr/bin/python3

global WARN
WARN = "WARN"
global ERR
ERR  = "ERROR"
global INFO
INFO = "INFO"


def log(message,level):
    if level == "WARN":
        print("WARNING: " + message)
    elif level == "ERROR":
        print("ERROR: " + message)
    elif level == "INFO":
        print("INFO: " + message)


def loginfo(message):
    log(message,INFO)


def logerror(message):
    log(message,ERR)

def logwarning(message):
    log(message,WARN)