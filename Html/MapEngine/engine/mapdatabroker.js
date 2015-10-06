
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
		//console.log("we gaan naar rechts")   
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
		//console.log("we gaan naar links")	
		this.xCorrection -= (treshold+1);
		this.xCurrentPosition -= this._scrollAdjust;
	}
	
	if(ymove > prevymove) {	
		this.newY += (this._sectionHeight+1);
		this.newX -= this.xCorrection;
		this.xCorrection = 0;
		this.yCorrection += (treshold+1);
		//result.push(new SectionLocation(this.newX-this.xCurrentPosition,newY-this.yCurrentPosition))
		
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
	//	result.push(new SectionLocation(this.newX-this.xCurrentPosition,this.newY-this.yCurrentPosition))
	
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
				
				// TODO: deze call wordt dus vervangen voor een stel secties die al opgehaald zijn met een query en daarna toegevoegd  worden aan de lijst van gerenderde  tiles
				this._mapservice.getMap(function (mapData) {
									
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

// TODO: getMapData and GetMapData are dependend on each other, consider loose coupling between the two or integrate more

/*
Get the initialscreen from where the user can scroll left or right
*/
this.getInitialMapData = function(x,y,callback) {

	//var x1 = this._sectionWidth  * this._cacheSize;
//	var y1 = this._sectionHeight * this._cacheSize;

	//this.xStartPosition = x;
	//this.yStartPosition = y;

	var currentContext = this;
//	if (!this.isAlreadyFetched(x, y, x1, y1)) {
	
	this._mapservice.fetchSections(currentContext._initialLoader(x,y),function(mapData) {
		var data = mapData[0]['layers'];
		//console.log("fetch sections result")
	//	console.log(data)
	
		//console.log(data)
		currentContext.data = data;
				currentContext._mapservice.getImages(function (imagedata) {
				currentContext.imageData = imagedata;
			//	var sections = currentContext._initialLoader(x,y)
				//sections = currentContext._cherryPickChunkLoading(sections)
				//currentContext._fetchRecursive(sections,callback,0)
				//currentContext._mapservice.fetchSections(sections,function(data) {
				
					//console.log("hij doet het")				
				//});
				callback(currentContext.imageData, currentContext.data)
			}

		);
	})
	
	/*this._mapservice.getMap(function (mapData) {
		var data = mapData[0]['layers'];
		//this.xOuter += width-1;
		//	this.yOuter += height-1;
		currentContext.data = data;
		currentContext._mapservice.getImages(function (imagedata) {
				currentContext.imageData = imagedata;
				var sections = currentContext._initialLoader(x,y)
				//sections = currentContext._cherryPickChunkLoading(sections)
				//currentContext._fetchRecursive(sections,callback,0)
				//currentContext._mapservice.fetchSections(sections,function(data) {
				
					//console.log("hij doet het")				
				//});
			//	callback(currentContext.imageData, currentContext.data)
			}

		);

	}, x, y, x1, y1);*/
//}
	
//	this.xOuter = x;
//	this.yOuter = y;
}


// NOTE: mogelijk dat deze functie in met de merge van NB-46 verplaatst moet worden 
/*
	cherry pick the sections needed for showing non background map tiles
*/
/*this._cherryPickChunkLoading = function(sections) { 

console.log("sections that will be loaded")
console.log(sections)

/*this._mapservice.markSectionsForFetching(sections,
function(data) {
	
	console.log("callback werkt")
	console.log(data)
});
	return sections;
}*/

/*
Checks if the mapbroker needs to fetch new data, it does this by checking how much progress has been made and if the treshold is reached
*/
this.getMapData = function (treshold,callback,first) {


			var currentContext = this;
			var multiplier = 2;
			var x1load,y1load,x2load,y2load	
			var xmove = this._parent.getMapData().getViewportX();
   	 	var ymove = this._parent.getMapData().getViewportY();
   		var prevxmove = this._parent.getMapData().getPrevViewportX();
   		var prevymove = this._parent.getMapData().getPrevViewportY();
   		
   		var startX = this._parent.getMapData().getStartPositionX();
			var startY = this._parent.getMapData().getStartPositionY();

 	    if(Math.abs(this.xCounter) == treshold || Math.abs(this.yCounter) == treshold )
 	    {
 	    	 var sections = [] 
 	    	 if(first) {
 	    	  sections = this._initialLoader(startX,startY)
 	    	 }
 	    	 else {	
 	    	  sections = this._calculateMovement(treshold)	 
			 }
	
				// Determine of all the sections that are calculated which one of these need to be fetched and which ones are background only		 	
				console.log("treshold = " + treshold)
			// 	this._fetchRecursive(sections,callback,0)
			 	this._mapservice.fetchSections(sections,function(mapData) {
					var data = mapData[0]['layers'];
					currentContext.data = data;
					currentContext._mapservice.getImages(function (imagedata) {
					currentContext.imageData = imagedata;
					callback(currentContext.imageData, currentContext.data)
			}

		);
	})
			 	
			 	

			 	
		 }
		else 
		{
			//console.log("values within treshold, don't get new data from the server")
			this.xCounter = this._getCurrentScrollOffset(xmove, prevxmove, this.xCounter);
			this.yCounter = this._getCurrentScrollOffset(ymove, prevymove, this.yCounter);
		}	

	}
}