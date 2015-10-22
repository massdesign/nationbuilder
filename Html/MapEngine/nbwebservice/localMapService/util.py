#!/usr/bin/python3

def ftos(f):
    return str(int(f))

def str2bool(v):
  trueresult = v.lower() in  ("yes", "true", "t", "1")
  falseresult = v.lower() in ("no","false","f","0")
  if trueresult is False and falseresult is False:
  	return None
  elif trueresult is True and falseresult is False:
  	return True
  elif trueresult is False and falseresult is True:
  	return False