function TileLayer(parentMap,loginstance)
{
  this.parentMap = parentMap
  this._host = "localhost:8083"
  this.loginstance = loginstance;
	this.init = function()
	{
		this._layer = new Kinetic.Layer();
   }
   	
   	
   this.renderTiles = function (imagedata,data,first) {
   	
   	var imagenames = Array();
  		var renderList = [];
   	for(var i=0;i<imagedata.length;i++)
    	{
			imagenames[imagedata[i].id] = imagedata[i].name
    	}
       	 data = this._sort(data);
    	for(var i=0;i<data.length;i++) {
   		var tileLayer = data[i].layer;
			 var currentOffset = this.parentMap.getMapData().getRenderOffset(i);
			 if (typeof currentOffset == 'undefined') {
				 currentOffset = 0;		 
			 }
   		 var tileLayertiles = tileLayer.tiles;

   		 for(var t=currentOffset;t<tileLayertiles.length;t++)
    		 {
    			var tiles = tileLayer.tiles;
    			var tile = tiles[t].tile
				xoffset = tile.xoffset
    			yoffset = tile.yoffset
				if(first) {
						
    					xposition = tile.xposition - this.parentMap.getMapData().getViewportX()-this.parentMap.getMapData().getStartPositionX()
    					yposition = tile.yposition - this.parentMap.getMapData().getViewportY()-this.parentMap.getMapData().getStartPositionY()
    					image_id = tile.image_id
				}
				else {
					
    					xposition = tile.xposition -  this.parentMap.getMapData().getStartPositionX()-this.parentMap.getMapData().getViewportX();
						yposition = tile.yposition -  this.parentMap.getMapData().getStartPositionY()-this.parentMap.getMapData().getViewportY();
    							
				}
						image_id = tile.image_id	
 			   		tilesize = this.parentMap.getRelativeTilesize();
    					tilerequest =  "sx" + tilesize + "_" + tilesize + "_" + xoffset.toString()  + yoffset.toString()  + "ts_" + imagenames[image_id].split('.')[0];
    					source = "http://" + this._host + "/ncache/" + tilerequest;
    					// tot aan hier
						var newTile  = new Tile(tile)
    			
    			newTile.setImageUrl(source)
    			newTile.setPosition(xposition,yposition);
				renderList.push(newTile);
    		}	
   		 
   		 
    		this.parentMap.getMapData().setRenderOffset(tileLayertiles.length,i);
    			
    	}
    	this.loadAllImages(renderList);
		
		}   	
	 this.move = function() {
					var c = 0;
		   		var currentContext = this;
		var anim = new Kinetic.Animation(function(frame) {
		 					
		 	 				
		 	 				var mapData = currentContext.parentMap.getMapData();
		 	 				var tiles = currentContext.parentMap.getMapData().getTiles();
		 	 				var prevViewportX = mapData.getPrevViewportX();
		 	 				var prevViewportY = mapData.getPrevViewportY();
		 	 				var viewportX = mapData.getViewportX();
	    					var viewportY = mapData.getViewportY();
		    					
	    					
          		      var tilesize = currentContext.parentMap.getRelativeTilesize()
			 				for(i=0;i<tiles.length;i++) {
							 
	    					 var currentX = tiles[i].getTileImage().getX() 
	    					 var currentY = tiles[i].getTileImage().getY()
	    					   
	  						 var newX = 0;
							 var newY = 0;		
							
										
							if(prevViewportX > viewportX) {
								newX = currentX + tilesize;		
								tiles[i].getTileImage().setX(newX);	
								//console.log("go right")
		 	 				}
		 	 				else if(prevViewportX < viewportX)
		 	 				{
		 	 					newX = currentX - tilesize;	
		 	 					tiles[i].getTileImage().setX(newX);			
		 	 					//console.log("go left")
		 	 				} 
		 	 				
							if(prevViewportY > viewportY) {
								newY = currentY + tilesize;
								 tiles[i].getTileImage().setY(newY);
								 //console.log("go down")
							}		 	 		
							else if(prevViewportY < viewportY) {
								newY = currentY - tilesize;		
								 tiles[i].getTileImage().setY(newY);
								 //console.log("go up")
							}
       					 }
				 
       					this.stop()
     						 }, currentContext._layer);
		anim.start()
	 }
    this._sort = function(data)
    {
    		var failsafe = 200
        result = [];
        var currentIndex = 0;
        var currentCounter = 0;
        var done =  false;
        var indexfound = false
        while(!done) {
            for (var i = 0; i < data.length; i++) {
     	
                if(data[i].layer.zindex == currentIndex)
                {                 
                    result[currentCounter] = data[i];
                    currentIndex++;
                    currentCounter++;
                    indexfound = true
                }
            }
            if(result.length == data.length) {
                done = true
            }
            else  {
            	
            		if(!indexfound) {
            			console.log("skipping index: " + currentIndex)
            			currentIndex++; 
            			indexfound  = false 
            		}						          
            }
        }
        return result;
       
    }

	this.loadAllImages =   function(renderList){

		var imagesOK = 0;
		var imUrlsLength = renderList.length;
		var currentContext = this;

		for(var i=0;i<renderList.length;i++) {
			var img = new Image();
			renderList[i].setTileHtmlImage(img);
			img.onload = function () {
				imagesOK++;

				if (imagesOK >= imUrlsLength) {

					for (var i = 0; i < renderList.length; i++) {
						var tresholdX = 0;
						var tresholdY = 0;
						var xpos= 0,ypos=0;

						tresholdX = currentContext.parentMap.getMapData().getTresholdX() * currentContext.parentMap.getRelativeTilesize();
						tresholdY = currentContext.parentMap.getMapData().getTresholdY() * currentContext.parentMap.getRelativeTilesize();		
						xpos =	renderList[i].getXPosition() * currentContext.parentMap.getRelativeTilesize()-tresholdX;
						ypos = 	renderList[i].getYPosition() * currentContext.parentMap.getRelativeTilesize()-tresholdY;
																
						var img = new Kinetic.Image({
							x: xpos,
							y: ypos,
							width: currentContext.parentMap.getRelativeTilesize(),
							height: currentContext.parentMap.getRelativeTilesize(),
							image: renderList[i].getTileHtmlImage(),
							draggable: false
						});
						renderList[i].setTileImage(img)
						currentContext._layer.add(img);
					}
					currentContext._layer.draw();
				}
			};
			renderList[i].getTileHtmlImage().onerror = function () {
				console.log("image load failed");
			}
			renderList[i].getTileHtmlImage().crossOrigin = "anonymous";
			renderList[i].getTileHtmlImage().src = renderList[i].getImageUrl();
		}
		var oldRenderlist = this.parentMap.getMapData().getTiles();
		for(i=0;i<renderList.length;i++)
		{
			oldRenderlist.push(renderList[i])
		}
		this.parentMap.getMapData().setTiles(oldRenderlist)
	   	
    }
	this.getLayer = function()
	{
		return this._layer;
	}

}