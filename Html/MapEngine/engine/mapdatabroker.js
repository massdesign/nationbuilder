
function MapDataBroker(parent) {
	
	this._mapservice = new MapService();
	this._cacheSize = 1;
	this.mapData = []
	this._parent = parent
	this.xCounter = 0;
	this.yCounter = 0;
	this.xOuter = 0;
	this.yOuter = 0;
	this.xStartPosition = 0;
	this.yStartPosition = 0;
	this.imageData = [];
	this.data = [];

	this.requestCache = [];
	

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
// TODO: getInitialMapData and GetMapData are dependend on each other, consider loose coupling between the two or integrate more

/*
Get the initialscreen from where the user can scroll left or right
*/
this.getInitialMapData = function(x,y,width,height,callback) {

	var x1 = width * this._cacheSize;
	var y1 = height * this._cacheSize;

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
				callback(currentContext.imageData, currentContext.data)
			}
		);

	}, x, y, x1, y1);
}
	
	this.xOuter = x;
	this.yOuter = y;
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

this._calculateMovement = function(newX,newY,treshold) {
	
	
		console.log("treshold in calculatemovement: " + treshold)
		var multiplier = treshold*2
		result = []
		
	  function SectionLocation(X,Y)  {		
		this.X = X;
		this.Y = Y;
		console.log("X = " + X)
			
		this.getX = function () {
			return this.X;		
		}		
		this.getY = function () {
			return this.Y;		
		}
	}	
	
	var currentTresholdX = Math.abs(this._parent.getMapData().getTresholdX())
 	var currentTresholdY = Math.abs(this._parent.getMapData().getTresholdY())
 	var xmove = this._parent.getMapData().getViewportX();
   var ymove = this._parent.getMapData().getViewportY();
   var prevxmove = this._parent.getMapData().getPrevViewportX();
   var prevymove = this._parent.getMapData().getPrevViewportY();
   
   if(xmove > prevxmove) {		
		result.push(new SectionLocation(newX,newY+multiplier))	
		result.push(new SectionLocation(newX+multiplier,newY))	
		result.push(new SectionLocation(newX+multiplier,newY+multiplier))	
		result.push(new SectionLocation(newX-multiplier,newY+multiplier))	
		
   	//this._parent.getMapData().setTresholdX(currentTresholdX+1)
		console.log("we gaan naar rechts")   
   }
	else if(xmove < prevxmove) {
		result.push(new SectionLocation(newX,newY+multiplier))	
		result.push(new SectionLocation(newX,newY-multiplier))
		result.push(new SectionLocation(newX+multiplier,newY+multiplier))
		result.push(new SectionLocation(newX+multiplier,newY-multiplier))
		
		//this._parent.getMapData().setTresholdX(currentTresholdX+1)
		console.log("we gaan naar links")	
	}
	
	if(ymove > prevymove) {
		result.push(new SectionLocation(newX+multiplier,newY))	
		result.push(new SectionLocation(newX-multiplier,newY))	
		result.push(new SectionLocation(newX+multiplier,newY-multiplier))	
		result.push(new SectionLocation(newX-multiplier,newY-multiplier))	

		console.log("we gaan naar onderen")	
	}	
	else if(ymove < prevymove) {
		result.push(new SectionLocation(newX+multiplier,newY))	
		result.push(new SectionLocation(newX-multiplier,newY))	
		result.push(new SectionLocation(newX+multiplier,newY+multiplier))	
		result.push(new SectionLocation(newX-multiplier,newY+multiplier))	
		console.log("we gaan naar boven")			
	}
	
	return result;	
	
}

this._fetchSection = function(width,height,xOuter,yOuter,callback,chain) {
			var currentContext = this;
			x2load = width * this._cacheSize;
			y2load = height * this._cacheSize;
			if(!this.isAlreadyFetched(xOuter, yOuter, x2load, y2load)) {
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
/*
Checks if the mapbroker needs to fetch new data, it does this by checking how much progress has been made and if the treshold is reached
*/
this.getMapData = function (treshold,width,height,callback) {


			var currentContext = this;
			
			var x1load,y1load,x2load,y2load	
			var xmove = this._parent.getMapData().getViewportX();
   	 	var ymove = this._parent.getMapData().getViewportY();
   		var prevxmove = this._parent.getMapData().getPrevViewportX();
   		var prevymove = this._parent.getMapData().getPrevViewportY();
   		
	
 	 
 	    if(Math.abs(this.xCounter) == treshold || Math.abs(this.yCounter) == treshold )
 	    {
 	    		var currentTresholdX = this._parent.getMapData().getTresholdX()
 	    		var currentTresholdY = this._parent.getMapData().getTresholdY()
 	    	 	
 	    	 	var newX = this._parent.getMapData().getStartPositionX()+this._parent.getMapData().getViewportX()*2;
			 	var newY = this._parent.getMapData().getStartPositionY()+this._parent.getMapData().getViewportY()*2;	    		
			 	var sections = this._calculateMovement(newX,newY,treshold)
			 	this._fetchSection(width,height,		 	
			 	newX,
			 	newY,callback,
					function() {			 	
   					console.log("De tweede in de ketting")
   					currentContext._fetchSection(width,height,sections[0].getX(),sections[0].getY(),callback,function () 	{	
							console.log("De derde in de ketting")
							
							currentContext._fetchSection(width,height,sections[1].getX(),sections[1].getY(),callback,function() {
								console.log("De vierde in de ketting")
								currentContext._fetchSection(width,height,sections[2].getX(),sections[2].getY(),callback,function() {
									console.log("De vijfde in de ketting")
									currentContext._fetchSection(width,height,sections[3].getX(),sections[3].getY(),callback,function() {
										console.log("De zesde in de ketting")
									});
								})										
							});
						
					});
					}
			 	);
 				//this._fetchSection(width,height,this._parent.getMapData().getStartPositionX()+this._parent.getMapData().getViewportX()*2+2,this._parent.getMapData().getStartPositionY()+this._parent.getMapData().getViewportY()*2,callback)			
 			

		}
		else 
		{
			//console.log("values within treshold, don't get new data from the server")
			this.xCounter = this._getCurrentScrollOffset(xmove, prevxmove, this.xCounter);
			this.yCounter = this._getCurrentScrollOffset(ymove, prevymove, this.yCounter);
		}

	}
}