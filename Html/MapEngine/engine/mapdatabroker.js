
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
		console.log("initial array that is loaded")
		console.log(data)
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

	return false;
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
 	    		
			 
 
 	    		if(currentContext.xOuter == this.xStartPosition && this.xCounter > 0) {
					 currentContext.xOuter = width * this._cacheSize + this._parent.getMapData().getStartPositionX();
	 				 currentContext.xCounter = 0;
	 				 this._parent.getMapData().setTresholdX(currentTresholdX+treshold)
	 			   }
				   
				else if(currentContext.yOuter == this.yStartPosition && this.yCounter > 0) {
					 currentContext.yOuter = height * this._cacheSize + this._parent.getMapData().getStartPositionY();
			       currentContext.yCounter = 0;
			       	this._parent.getMapData().setTresholdY(currentTresholdY+treshold)
					}
				else 	if(Math.abs(currentContext.xCounter) == treshold && currentContext.xCounter > 0)
				{
				   currentContext.xOuter += width*currentContext._cacheSize;
					this._parent.getMapData().setTresholdX(currentTresholdX + treshold)
				}
				else if(Math.abs(currentContext.yCounter) == treshold && currentContext.yCounter > 0)
				{
					currentContext.yOuter += height*currentContext._cacheSize;
		        	this._parent.getMapData().setTresholdY(currentTresholdY+treshold)
				}
				else 	if(Math.abs(currentContext.xCounter) == treshold && currentContext.xCounter < 0)
				{
				   currentContext.xOuter -= width*currentContext._cacheSize;
				   console.log("xouter set again")
			
				}
				else if(Math.abs(currentContext.yCounter) == treshold && currentContext.yCounter < 0)
				{
					
					currentContext.yOuter -= height*currentContext._cacheSize;
				}
			x2load = width * this._cacheSize;
			y2load = height * this._cacheSize;

			if(!this.isAlreadyFetched(this.xOuter, this.yOuter, x2load, y2load)) {
				this._mapservice.getMap(function (mapData) {
					var data = mapData[0]['layers'];
					console.log("old data length: " + data.length)
					for (i = 0; i < data.length; i++) {


						// we are missing a layer.. add it to the list of layers that are already in cache
						if (typeof currentContext.data[i] == 'undefined') {
							console.log("new layer found, adding it to the pool")
							console.log("layername " + data[i].layer.name)
							currentContext.data.push(data[i]);
							currentContext._parent.getMapData().setRenderOffset(0, i);
							console.log("the current state of the array")
							console.log(currentContext.data)
							//console.log(currentContext.data)
						}
						else {
							var currentLayer = currentContext.data[i].layer;
							var newLayer = data[i].layer;
							console.log(currentLayer)
							currentContext._parent.getMapData().setRenderOffset(currentLayer.tiles.length, i);
							currentLayer.tiles = currentLayer.tiles.concat(newLayer.tiles)
							for (ii = 0; ii < currentLayer.tiles.length; ii++) {
								if (ii == 25 || ii == 26) {
									console.log("current  tile=" + ii)
									console.log(currentLayer.tiles[ii].tile)
								}
							}
						}


						//	}

					}
					currentContext._mapservice.getImages(function (imagedata) {
							currentContext.imageData = imagedata;
							callback(currentContext.imageData, currentContext.data)
							var xmov = currentContext._parent.getMapData().getXMovement() + currentContext.xCounter;
							currentContext._parent.getMapData().setXMovement(xmov);
							currentContext.xCounter = 0;
							currentContext.yCounter = 0;
						}
					);

				}, this.xOuter, this.yOuter, x2load, y2load);
			}

		}
		else 
		{
			//console.log("values within treshold, don't get new data from the server")
			this.xCounter = this._getCurrentScrollOffset(xmove, prevxmove, this.xCounter);
			this.yCounter = this._getCurrentScrollOffset(ymove, prevymove, this.yCounter);
		}

	}
}