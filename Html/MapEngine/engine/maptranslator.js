function MapTranslator(parentMap) {

 this.parentMap = parentMap;
 
 // translates position of a given X,Y coordinate set to the current
 this.translatePosition = function (currentX,currentY) {
 	var mapData = this.parentMap.getMapData();
  	var viewportX = mapData.getViewportX();
	var viewportY = mapData.getViewportY();
 	
 
	
	return new Coordinate(currentX+viewportX,currentY+viewportY);	
 }
 this.normalizePosition = function (currentX,currentY) {
 	var mapData = this.parentMap.getMapData();
  	var viewportX = mapData.getViewportX();
	var viewportY = mapData.getViewportY();
 	
	
	return new Coordinate(currentX-viewportX,currentY-viewportY);
 
 }

 this.calculateOffset = function () {
	
var mapWidth =	Config.MAP_WIDTH;
var mapHeight = Config.MAP_HEIGHT;

var chunkWidth = Config.CHUNK_WIDTH;
var chunkHeight = Config.CHUNK_HEIGHT;

xOffset =  Math.floor(mapWidth/2)-Math.floor(chunkWidth/2);
yOffset =  Math.floor(mapHeight/2)-Math.floor(chunkHeight/2);

return new XYTuple(xOffset,yOffset);
	
 } 
 
 this.move = function(currentLayer,items) {
					var c = 0;
		   		var currentContext = this;
		var anim = new Kinetic.Animation(function(frame) {
		 					
		 	 				var mapData = currentContext.parentMap.getMapData();
		 	 				var prevViewportX = mapData.getPrevViewportX();
		 	 				var prevViewportY = mapData.getPrevViewportY();
		 	 				var viewportX = mapData.getViewportX();
	    					var viewportY = mapData.getViewportY();
	    					
	    					console.log("viewportX: " + viewportX);
	    					console.log("viewportY: " + viewportY);
		    					
	    					
          		      var tilesize = currentContext.parentMap.getRelativeTilesize()
			 				for(i=0;i<items.length;i++) {
							 
	    					 var currentX = items[i].getX() 
	    					 var currentY = items[i].getY()
	    					   
	  						 var newX = 0;
							 var newY = 0;		
													
							if(prevViewportX > viewportX) {
								newX = currentX + tilesize;		
								items[i].setX(newX);	
								//console.log("go right")
		 	 				}
		 	 				else if(prevViewportX < viewportX)
		 	 				{
		 	 					newX = currentX - tilesize;			
		 	 					items[i].setX(newX);			
		 	 					//console.log("go left")
		 	 				} 
		 	 				
							if(prevViewportY > viewportY) {
								newY = currentY + tilesize;
								 items[i].setY(newY);
								 //console.log("go down")
							}		 	 		
							else if(prevViewportY < viewportY) {
								newY = currentY - tilesize;		
								 items[i].setY(newY);
								 //console.log("go up")
							}
       					 }
				 
       					this.stop()
     						 }, currentLayer	);
		anim.start()
	 }
}