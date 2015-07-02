
function MapDataBroker(parent,sectionWidth,sectionHeight,treshold,mapwidth,mapheight) {
	
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
	//console.log(this._parent.getMapWidth())
	this._currentMapWidth  = mapwidth;
	this._currentMapHeight = mapheight;
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

this._calculateMovement = function(treshold) {
	
		var multiplier = this._sectionWidth+1;
		result = []
		
		
	var currentTresholdX = Math.abs(this._parent.getMapData().getTresholdX())
 	var currentTresholdY = Math.abs(this._parent.getMapData().getTresholdY())
 	var xmove = this._parent.getMapData().getViewportX();
   var ymove = this._parent.getMapData().getViewportY();
   var prevxmove = this._parent.getMapData().getPrevViewportX();
   var prevymove = this._parent.getMapData().getPrevViewportY();
     

	if(this.newX == 0 && this.newY == 0)  {
		 this.newX = this._parent.getMapData().getStartPositionX();
		 this.newY = this._parent.getMapData().getStartPositionY();
	}

   if(xmove > prevxmove) {			
   
   
   	this.newX += (this._sectionWidth+1);//this._parent.getMapData().getViewportX();
   	this.newY -= this.yCorrection;
   	this.yCorrection = 0;
			
		// TODO: deze chunks moeten worden gegenereerd volgens een algoritme.. bij vergroten van de canvas gaan we tegen problemen aanlopen
		result.push(new SectionLocation(this.newX,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX+multiplier,this.newY))	
		result.push(new SectionLocation(this.newX+multiplier,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX-multiplier*2,this.newY))	
		result.push(new SectionLocation(this.newX-multiplier*2,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX-multiplier*2,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX,this.newY-multiplier))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY-multiplier))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY-multiplier))	
		result.push(new SectionLocation(this.newX-multiplier*2,this.newY-multiplier))
		result.push(new SectionLocation(this.newX+multiplier,this.newY-multiplier))	
			
		this.xCorrection += (treshold+1);
		this.xCurrentPosition += this._scrollAdjust;
   }
	else if(xmove < prevxmove) {
		this.newX -= (this._sectionWidth+1);
		this.newY -= this.yCorrection;
		this.yCorrection = 0;
		result.push(new SectionLocation(this.newX,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX,this.newY-multiplier))
		result.push(new SectionLocation(this.newX+multiplier,this.newY+multiplier))
		result.push(new SectionLocation(this.newX+multiplier,this.newY-multiplier))
		result.push(new SectionLocation(this.newX-multiplier,this.newY))
		result.push(new SectionLocation(this.newX-multiplier,this.newY-multiplier))
		result.push(new SectionLocation(this.newX-multiplier,this.newY+multiplier))
		result.push(new SectionLocation(this.newX+multiplier*2,this.newY))
		result.push(new SectionLocation(this.newX+multiplier*2,this.newY+multiplier))
		result.push(new SectionLocation(this.newX+multiplier*2,this.newY-multiplier))
		this.xCorrection -= (treshold+1);
		this.xCurrentPosition -= this._scrollAdjust;
	}
	
	if(ymove > prevymove) {	
		this.newY += (this._sectionHeight+1);
		this.newX -= this.xCorrection;
		this.xCorrection = 0;
		this.yCorrection += (treshold+1);
		
		result.push(new SectionLocation(this.newX+multiplier,this.newY))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY))	
		result.push(new SectionLocation(this.newX+multiplier,this.newY-multiplier))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY-multiplier))
		result.push(new SectionLocation(this.newX,this.newY-multiplier*2))	
		result.push(new SectionLocation(this.newX+multiplier,this.newY-multiplier*2))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY-multiplier*2))
		result.push(new SectionLocation(this.newX,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX+multiplier,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY+multiplier))	
	
		
		//this.xCurrentPosition = 0;
		this.yCurrentPosition += this._scrollAdjust;
		//console.log("we gaan naar onderen")	
	}	
	else if(ymove < prevymove) {
	
		this.newY -= (this._sectionHeight+1);
		this.newX -= this.xCorrection;
		this.yCorrection -= (treshold+1);
		this.xCorrection = 0;
		result.push(new SectionLocation(this.newX+multiplier,this.newY))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY))	
		result.push(new SectionLocation(this.newX+multiplier,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY+multiplier))	
		result.push(new SectionLocation(this.newX,this.newY-multiplier))	
		result.push(new SectionLocation(this.newX+multiplier,this.newY-multiplier))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY-multiplier))	
		result.push(new SectionLocation(this.newX-multiplier,this.newY+multiplier*2))	
		result.push(new SectionLocation(this.newX,this.newY+multiplier*2))	
		result.push(new SectionLocation(this.newX+multiplier,this.newY+multiplier*2))
		
		this.yCurrentPosition -= this._scrollAdjust;
	//	console.log("we gaan naar boven")			
	}
		result.push(new SectionLocation(this.newX,this.newY))
			console.log("newX" + this.newX)
	return result;	
	
}


this._getChunkDimensions = function () {
var currentLoadedTiles = this._parent.getMapData().getTiles();

	var highestX = 0;
	var highestY = 0;
	
	var lowestX = Number.MAX_VALUE;
	var lowestY = Number.MAX_VALUE;
	
	for(var i=0;i<currentLoadedTiles.length;i++) {
			if(currentLoadedTiles[i].xposition > highestX) {
				highestX = currentLoadedTiles[i].xposition;			
			}		
			if(currentLoadedTiles[i].yposition > highestY) {
				highestY = currentLoadedTiles[i].yposition;			
			}
			if(currentLoadedTiles[i].xposition < lowestX) {
				lowestX = currentLoadedTiles[i].xposition;			
			}	
			if(currentLoadedTiles[i].yposition < lowestY) {
				lowestY = currentLoadedTiles[i].yposition;	
			}
	}
	
	return new QuadTuple(lowestX,lowestY,highestX,highestY);
}

this._calculateZoomOut = function (zoomfactor) {

	result = []
	var sectionX1Position = this._sectionWidth+1;
	var sectionY1Position = this._sectionHeight+1;
	
	var sectionX2Position = this._sectionWidth+1;
	var sectionY2Position = this._sectionHeight+1;

	// NOTE: voor nu gaan we uit van een chunksize van 7 bij 7 en een canvas van 20*20
	// Delen door twee want de scaling is linear dus de helft
	var newsw = this._sectionWidth/2;
	var newsh = this._sectionHeight/2;
	
	chunkDimensions = this._getChunkDimensions();	
	

	
	var canvaswidth =  this._currentMapWidth*2;
	var canvasheight = this._currentMapHeight*2;
	
	
	var currentLoadedTiles = this._parent.getMapData().getTiles();
	var offsetTuple = this._parent.getMapTranslator().calculateOffset();
	
		
	if(this.newX == 0 && this.newY == 0)  {
		 this.newX = this._parent.getMapData().getStartPositionX();
		 this.newY = this._parent.getMapData().getStartPositionY();
	}

	var newXPosition = this.newX;
	var newYPosition = this.newY;
	console.log("newX: " + this.newX)
	
	var startX = this.newX - offsetTuple.getX();
	var startY = this.newY - offsetTuple.getY();
	var mapSize = this._parent.getMapTranslator().getRelativeMapSize(this._parent.getZoomFactor(),this._currentMapWidth,this._currentMapHeight)
	var stopX = startX + mapSize.getX()
	var stopY = startY + mapSize.getY()
	console.log("startY: " + startY)
	console.log("stopX: " + stopX)
	console.log("startX: " + startX)
	console.log("stopY: " + stopY)
	console.log("currentMapWidth: " + mapSize.getX());
	console.log("currentMapHeight: " + mapSize.getY());
	
	for(var x=startX;x<stopX;x +=   this._sectionWidth) {
		//var xIncrement = x+this._sectionWidth
		result.push(new SectionLocation(x,startY))
		for(var  y=startY;y<stopY;y += this._sectionHeight) { 
			result.push(new SectionLocation(x,y))
		}
	}

	
	return result;
}

this._initialLoader = function (x,y) {

	result = [];
	
	result.push(new SectionLocation(x+(this._sectionWidth+1),y));
	result.push(new SectionLocation(x-(this._sectionWidth+1),y));
	result.push(new SectionLocation(x,y+(this._sectionHeight+1)));
	result.push(new SectionLocation(x,y-(this._sectionHeight+1)));
	result.push(new SectionLocation(x+(this._sectionWidth+1),y+(this._sectionHeight+1)));
	result.push(new SectionLocation(x-(this._sectionWidth+1),y+(this._sectionHeight+1)));
	result.push(new SectionLocation(x-(this._sectionWidth+1),y-(this._sectionHeight+1)));
	result.push(new SectionLocation(x+(this._sectionWidth+1),y-(this._sectionHeight+1)));
	
	return result;
}
// TODO: width height hier ook refactoren naar this._sectionWidth en this._sectionHeight
this._fetchSection = function(width,height,xOuter,yOuter,callback,chain) {
			var currentContext = this;
			x2load = width * this._cacheSize;
			y2load = height * this._cacheSize;
			if(!this.isAlreadyFetched(xOuter, yOuter, x2load, y2load)) {
				this._mapservice.getMap(function (mapData) {
					if (typeof mapData !== 'undefined' && typeof mapData[0] !== 'undefined') {
					
					var newData = mapData[0]['layers'];
										
					for (i = 0; i < newData.length; i++) {
						// we are missing a layer.. add it to the list of layers that are already in cache
						if (typeof currentContext.data[i] == 'undefined') {
							currentContext.data.push(newData[i]);
							currentContext._parent.getMapData().setRenderOffset(0, i);
						}
						else {
							
							var currentLayer = currentContext.data[i].layer;
							
							var newLayer = newData[i].layer;
							currentContext._parent.getMapData().setRenderOffset(currentLayer.tiles.length, i);
							currentLayer.tiles = currentLayer.tiles.concat(newLayer.tiles)							

						}
					}
					currentContext._mapservice.getImages(function (imagedata) {
							currentContext.imageData = imagedata;
							callback(currentContext.imageData, currentContext.data)
							currentContext.xCounter = 0;
							currentContext.yCounter = 0;
						}
					);
					
					chain()
				}
				else {
					console.log("section fetch yielded no result")
					chain()
				}
				}, xOuter, yOuter, x2load, y2load);
			}
			else {
				console.log("section already fetched")
			this.xCounter = 0;
			this.yCounter = 0;	
			chain()	
			}	
	
}

this._fetchRecursive = function(sections,callback,pos) {

var currentContext = this;
 	this._fetchSection(this._sectionWidth,this._sectionHeight,		 	
			 	sections[pos].getX(),
			 	sections[pos].getY(),callback,//function() {
					function() {
						pos++; 	
						if(pos < sections.length)
						{
							currentContext._fetchRecursive(sections,callback,pos);
						}			
						
				});
}

// TODO: getInitialMapData and GetMapData are dependend on each other, consider loose coupling between the two or integrate more

/*
Get the initialscreen from where the user can scroll left or right
*/
this.getInitialMapData = function(x,y,callback) {

	var x1 = this._sectionWidth  * this._cacheSize;
	var y1 = this._sectionHeight * this._cacheSize;

	this.xStartPosition = x;
	this.yStartPosition = y;

	var currentContext = this;
	if (!this.isAlreadyFetched(x, y, x1, y1)) {

	this._mapservice.getMap(function (mapData) {
		var data = mapData[0]['layers'];
		//this.xOuter += width-1;
		//	this.yOuter += height-1;
		currentContext.data = data;
		currentContext._mapservice.getImages(function (imagedata) {
				currentContext.imageData = imagedata;
				var sections = currentContext._initialLoader(x,y)
				// NOTE: tijdelijk even recursive chunkloading uitgezet om het wat makkelijker te maken
			//	currentContext._fetchRecursive(sections,callback,0)
				callback(currentContext.imageData, currentContext.data)
			}

		);

	}, x, y, x1, y1);
}
	
	this.xOuter = x;
	this.yOuter = y;
}
this._getMapDataForMove = function(treshold,callback) {

		var currentContext = this;
		var multiplier = 2;
		var x1load,y1load,x2load,y2load	
		var xmove = this._parent.getMapData().getViewportX();
  	 	var ymove = this._parent.getMapData().getViewportY();
  		var prevxmove = this._parent.getMapData().getPrevViewportX();
  		var prevymove = this._parent.getMapData().getPrevViewportY();

	  if(Math.abs(this.xCounter) == treshold || Math.abs(this.yCounter) == treshold )
 	   {
		 	var sections = this._calculateMovement(treshold)
		 	this._fetchRecursive(sections,callback,0)
		}
		else 
		{
			this.xCounter = this._getCurrentScrollOffset(xmove, prevxmove, this.xCounter);
			this.yCounter = this._getCurrentScrollOffset(ymove, prevymove, this.yCounter);
		}	
}
this._getMapDataForZoomout = function(zoomfactor,callback) { 

var sections = this._calculateZoomOut(zoomfactor)
	 	this._fetchRecursive(sections,callback,0)

}
/*
Checks if the mapbroker needs to fetch new data, it does this by checking how much progress has been made and if the treshold is reached
*/
this.getMapData = function (treshold,callback,zoomfactor) {

		 if(typeof zoomfactor !== "undefined")
		 {
		 	console.log("We gaan er even wat tiles bijladen")
		 	this._getMapDataForZoomout(zoomfactor,callback)
		 	
		 }
		 else {
 			this._getMapDataForMove(treshold,callback)
		}
	}
}