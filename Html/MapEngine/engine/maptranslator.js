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
	// TODO: deze functie wordt nog niet overal gebruikt..
	this.calculateOffset = function () {

		var mapWidth = Config.MAP_WIDTH;
		var mapHeight = Config.MAP_HEIGHT;

		var chunkWidth = Config.CHUNK_WIDTH;
		var chunkHeight = Config.CHUNK_HEIGHT;

		xOffset = Math.floor(mapWidth / 2) - Math.floor(chunkWidth / 2);
		yOffset = Math.floor(mapHeight / 2) - Math.floor(chunkHeight / 2);

		return new XYTuple(xOffset, yOffset);

	}
	this.getRelativeMapSize = function (zoomfactor, mapWidth, mapHeight) {

		var m = 2;
		var rw = mapWidth;
		var rh = mapHeight;
		while (zoomfactor > 0) {
			rw = rw * 2
			rh = rh * 2
			zoomfactor--;
		}
		return new XYTuple(rw, rh)
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
	    								
          		      var tilesize = currentContext.parentMap.getRelativeTilesize()
			 				for(i=0;i<items.length;i++) {
							 
	    					 var currentX = items[i].getX() 
	    					 var currentY = items[i].getY()
	    					   
	  						 var newX = 0;
							 var newY = 0;		
													
							if(prevViewportX > viewportX) {
								newX = currentX + tilesize;		
								items[i].setX(newX);	
						
		 	 				}
		 	 				else if(prevViewportX < viewportX)
		 	 				{
		 	 					newX = currentX - tilesize;			
		 	 					items[i].setX(newX);			
		 	 		
		 	 				} 
		 	 				
							if(prevViewportY > viewportY) {
								newY = currentY + tilesize;
								 items[i].setY(newY);
					
							}		 	 		
							else if(prevViewportY < viewportY) {
								newY = currentY - tilesize;		
								 items[i].setY(newY);
				
							}
       					 }
				 
       					this.stop()
     						 }, currentLayer	);
		anim.start()
	 }
}