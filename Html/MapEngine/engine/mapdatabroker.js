
function MapDataBroker(parent) {
	
	this._mapservice = new MapService();
	
	this.mapData = []
	this._parent = parent
	this.xCounter = 0;
	this.yCounter = 0;
	

this._hasChanged = function(move,prevmove) {
   	  // no change
   	  if(prevmove == move)
   	  {
   	  	return 0;
        }
        // clicked to the right 
        else if(prevmove < move)
        {
        	return -1;
        }
        // clicked to the left 
        else if(prevmove > move)
        {
        	return 1;
        
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
this.getMapData = function (treshold,x,y,width,height,callback) {



var currentContext = this
 var x1load,y1load,x2load,y2load
 
		 var xmove = this._parent.getMapData().getViewportX();
   	 var ymove = this._parent.getMapData().getViewportY();
   	 var prevxmove = this._parent.getMapData().getPrevViewportX();
   	 var prevymove = this._parent.getMapData().getPrevViewportY();
   	 
   	 
   	 this.xCounter = this._getCurrentScrollOffset(xmove,prevxmove,this.xCounter);
 	    this.yCounter = this._getCurrentScrollOffset(ymove,prevymove,this.yCounter);
 	    
 	    if(this.xCounter == treshold || this.yCounter == treshold)
 	    {

 			
			// if there is something on the left that is worth loading do it 
			if(x >= width*2 && y >=height*2)	
			{
			// the window that we need to prefetch goes also left (-x) and up (-y)			
			}
			else 
			{
 				console.log("hier komt ie nooit")
  				x2load=width*2;
  				y2load=height*2;
			}	

			this._mapservice.getMap(function(mapData) {
 					console.log("width: " + width)
 					console.log("height: " + height)
					var data = mapData[0]['layers'];
					currentContext._mapservice.getImages(function(imagedata) {
 					console.log(imagedata)
 					console.log(data)	
					callback(imagedata,data)
					}
				);

			},x,y,x2load,y2load);

		}
		else 
		{
			console.log("xcounter: " + this.xCounter + " ycounter" + this.yCounter) 
			console.log("values within treshold, don't get new data from the server")
		}
	}
}