function MapTranslator(parentMap) {

 this.parentMap = parentMap;
 
 this.move = function(currentLayer,items) {
					var c = 0;
		   		var currentContext = this;
		var anim = new Kinetic.Animation(function(frame) {
		 					
		 	 				
		 	 				var mapData = currentContext.parentMap.getMapData();
		 	 				//var tiles = items; //currentContext.parentMap.getMapData().getTiles();
		 	 				var prevViewportX = mapData.getPrevViewportX();
		 	 				var prevViewportY = mapData.getPrevViewportY();
		 	 				var viewportX = mapData.getViewportX();
	    					var viewportY = mapData.getViewportY();
		    					
	    					
          		      var tilesize = currentContext.parentMap.getRelativeTilesize()
			 				for(i=0;i<items.length;i++) {
							 
	    					 var currentX = items[i].getTileImage().getX() 
	    					 var currentY = items[i].getTileImage().getY()
	    					   
	  						 var newX = 0;
							 var newY = 0;		
							
										
							if(prevViewportX > viewportX) {
								newX = currentX + tilesize;		
								items[i].getTileImage().setX(newX);	
								//console.log("go right")
		 	 				}
		 	 				else if(prevViewportX < viewportX)
		 	 				{
		 	 					newX = currentX - tilesize;	
		 	 					items[i].getTileImage().setX(newX);			
		 	 					//console.log("go left")
		 	 				} 
		 	 				
							if(prevViewportY > viewportY) {
								newY = currentY + tilesize;
								 items[i].getTileImage().setY(newY);
								 //console.log("go down")
							}		 	 		
							else if(prevViewportY < viewportY) {
								newY = currentY - tilesize;		
								 items[i].getTileImage().setY(newY);
								 //console.log("go up")
							}
       					 }
				 
       					this.stop()
     						 }, currentLayer	);
		anim.start()
	 }
}