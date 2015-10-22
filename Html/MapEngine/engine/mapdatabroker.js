
function MapDataBroker(parent,sectionWidth,sectionHeight,treshold) {
	
	this._mapservice = new MapService();
	this._treshold = treshold;
	this._cacheSize = 1;
	this.mapData = []
	this._parent = parent
	this.xCounter = 0;
	this.yCounter = 0;
	this.xStartPosition = 0;
	this.yStartPosition = 0;
	this.xCurrentPosition = 0;
	this.yCurrentPosition = 0;
	this.xCorrection = 0;
	this.yCorrection = 0;
	this.imageData = [];
	this.data = [];
	this._sectionWidth = sectionWidth;
	this._sectionHeight = sectionHeight;
	this.requestCache = [];
	this.newX = 0;
	this.newY = 0;
	
	
	

this._hasChanged = function(move,prevmove) {
   	  // no change
   	  if(prevmove == move)
   	  {
   	  	return 0;
        }
        // clicked to the right 
        else if(prevmove < move)
        {
        	return 1;
        }
        // clicked to the left 
        else if(prevmove > move)
        {
        	return -1;
        
        } 
}
this._getCurrentScrollOffset = function(move,prevmove,current) {

			var c = current;
       var change =	this._hasChanged(move,prevmove)
   	
		  if(change != 0)
		  {
		    if(change > 0) 
		    {
		    	c++;
		    }
		    else 
			 {
			 	c--;	
			 }
		  }  
		  return c;
}

this.isAlreadyFetched = function(x1,y1,x2,y2) {
	x2 += x1
	y2 += y1
	var found = false;
	// check if we go out of bounds.. if so return that we already found this set of tiles and don't need to fetch it
	if(x1 >= 0 && y1 >= 0 ) 
	{	
	for(i=0;i<this.requestCache.length;i++) {

		if(this.requestCache[i].getX1() == x1 && this.requestCache[i].getY1() == y1
			&& this.requestCache[i].getX2() == x2 && this.requestCache[i].getY2() == y2)
		{
			found = true;
			break;
		}

	}
	
	if(!found) {
	
	var RequestEntry = function (x1,y1,x2,y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;		
		this.getX1 = function () {
			return this.x1;
		}	
		this.getY1 = function () {
			return this.y1;
		}
		this.getX2 = function () {
			return this.x2;	
		}
		this.getY2 = function () {
			return this.y2;	
		}
		this.setX1 = function (x1) {
			this.x1 = x1;	
		}
		this.setX2 = function (x2) {
			this.x2 = x2;	
		}
		this.setY1 = function (y1) {
			this.y1 = y1;	
		}
		this.setY2 = function (y2) {
		this.y2 = y2;	
		}
	 }
	 	this.requestCache.push(new RequestEntry(x1,y1,x2,y2))		
	}

}
else {
	found = true;
}
	return found;
}

/*
Checks if the mapbroker needs to fetch new data, it does this by checking how much progress has been made and if the treshold is reached
*/
// testmethode voor nieuwe movement calculaties
this._calculateMovementTest = function() { 
	 var sections = []
	var currentTresholdX = Math.abs(this._parent.getMapData().getTresholdX())
 	var currentTresholdY = Math.abs(this._parent.getMapData().getTresholdY())
 	var xmove = this._parent.getMapData().getViewportX();
   var ymove = this._parent.getMapData().getViewportY();
   var prevxmove = this._parent.getMapData().getPrevViewportX();
   var prevymove = this._parent.getMapData().getPrevViewportY();
   
	// TODO: dit in een config bestand zetten.. 19 slaat op de breedt van de viewport..   tellen begint bij 0 vandaar 19 viewport is namelijk 20 breed 
   var x1 = 0;
   var y1 = 0;
   var x2 = x1+19;
   var y2 = y1+19;
   

 
   if(xmove > prevxmove) {	
    this.newX += 1;
	 console.log("go right")	
	}	
	else if(xmove < prevxmove) {
	 this.newX -= 1;
	 console.log("go left")	
	}
	if(ymove > prevymove) {
	 console.log("go down")	
	 this.newY += 1;
	}
	else if(ymove < prevymove) {
	 this.newY -= 1;
	 console.log("go up")	
	}


	if(this.newX == 0 && this.newY == 0)  { 
	 	console.log("first")	
	 	this.newX = this._parent.getMapData().getStartPositionX();
   	this.newY = this._parent.getMapData().getStartPositionY();
	}
		 sections.push(new SectionLocation(x1+Math.abs(this.newX),y1+Math.abs(this.newY)));	 
	 sections.push(new SectionLocation(x2+Math.abs(this.newX),y2+Math.abs(this.newY)));
	 return sections;
}

this.getMapData = function (callback) {


			var currentContext = this;
			var x1load,y1load,x2load,y2load	
			var xmove = this._parent.getMapData().getViewportX();
   	 	var ymove = this._parent.getMapData().getViewportY();
   		var prevxmove = this._parent.getMapData().getPrevViewportX();
   		var prevymove = this._parent.getMapData().getPrevViewportY();
   			  
 	    	 sections = this._calculateMovementTest();
 	    	 console.log(sections)

			 	this._mapservice.fetchSections(sections,function(mapData) {
					var data = mapData[0]['layers'];
					console.log("fetched tiles")
					console.log(mapData);
					currentContext.data = data;
					currentContext._mapservice.getImages(function (imagedata) {
					currentContext.imageData = imagedata;
					callback(currentContext.imageData, currentContext.data)
			}

		);
	})
	}
}