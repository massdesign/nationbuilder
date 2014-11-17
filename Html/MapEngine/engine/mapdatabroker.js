
function MapDataBroker(parent) {
	
	this._mapservice = new MapService();
	this._cacheSize = 2;
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

	}, x,y, x1, y1);
	
	this.xOuter = x;
	this.yOuter = y;
}
/*
Checks if the mapbroker needs to fetch new data, it does this by checking how much progress has been made and if the treshold is reached
*/
this.getMapData = function (treshold,width,height,callback) {

		console.log(this.xCounter)
		console.log(this.yCounter)
	 	var currentContext = this;
		var x1load,y1load,x2load,y2load
 
		var xmove = this._parent.getMapData().getViewportX();
   	 	var ymove = this._parent.getMapData().getViewportY();
   		var prevxmove = this._parent.getMapData().getPrevViewportX();
   		var prevymove = this._parent.getMapData().getPrevViewportY();
	
 	    
 	    if(Math.abs(this.xCounter) == treshold || Math.abs(this.yCounter) == treshold)
 	    {
 	    		if(currentContext.xOuter == this.xStartPosition && this.xCounter > 0) {
					 currentContext.xOuter = width * this._cacheSize + this._parent.getMapData().getStartPositionX();
	 				console.log("initial set x")	
	 				 currentContext.xCounter = 0;
				   }
				   
				else if(currentContext.yOuter == this.yStartPosition && this.yCounter > 0) {
					 currentContext.yOuter = height * this._cacheSize + this._parent.getMapData().getStartPositionY();
					 console.log("initial set y")
					 		currentContext.yCounter = 0;
					}
				else 	if(Math.abs(currentContext.xCounter) == treshold && currentContext.xCounter > 0)
				{
				   currentContext.xOuter += width*currentContext._cacheSize;
				   console.log("xouter set again")
			
				}
				else if(Math.abs(currentContext.yCounter) == treshold && currentContext.yCounter > 0)
				{
					
					currentContext.yOuter += height*currentContext._cacheSize;
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


			this._mapservice.getMap(function(mapData) {
					var data = mapData[0]['layers'];
					for(i=0;i<currentContext.data.length;i++) {
						var currentLayer = currentContext.data[i].layer;
						if(data.length > i){
							var newLayer = data[i].layer;
							//console.log(newLayer.tiles)
							//console.log("previouslength" + currentLayer.tiles.length)
							currentLayer.tiles = currentLayer.tiles.concat(newLayer.tiles)
							//console.log("currentlength" + currentLayer.tiles.length)
							}
											
						}
						console.log(currentContext.data)			
					//console.log(currentContext.data[0].layer.tiles)					
					//currentContext.data = data;
					currentContext._mapservice.getImages(function(imagedata) {
							currentContext.imageData = imagedata;
						callback(currentContext.imageData,currentContext.data)
					    currentContext.xCounter = 0;
						currentContext.yCounter = 0;
					}
				);

			},this.xOuter,this.yOuter,x2load,y2load);

		}
		else 
		{

			//console.log("values within treshold, don't get new data from the server")
			this.xCounter = this._getCurrentScrollOffset(xmove, prevxmove, this.xCounter);
			this.yCounter = this._getCurrentScrollOffset(ymove, prevymove, this.yCounter);
		}

	}
}