function TileLayer(parentMap,loginstance)
{
  this.parentMap = parentMap
  this.loginstance = loginstance;
	this.init = function()
	{
		this._layer = new Kinetic.Layer();
   }
   	
   	
   
	// do a partial render
	
	this.partialRender = function (imagedata,data) {
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
   		 var tileLayertiles = tileLayer.tiles;
   		 
  		
    		for(var t=currentOffset;t<tileLayertiles.length;t++)
    		{
    			var tiles = tileLayer.tiles;
    			var tile = tiles[t].tile


    			xoffset = tile.xoffset
    			yoffset = tile.yoffset
				xposition = tile.xposition -  this.parentMap.getMapData().getStartPositionX()
				yposition = tile.yposition -  this.parentMap.getMapData().getStartPositionY()
    			image_id = tile.image_id

 			   tilesize = this.parentMap.getRelativeTilesize();
    			tilerequest =  "sx" + tilesize + "_" + tilesize + "_" + xoffset.toString()  + yoffset.toString()  + "ts_" + imagenames[image_id].split('.')[0];
    			source = "http://localhost:8083/ncache/" + tilerequest;


				tile.setImageUrl = function (imageUrl) {
					this.imageUrl = imageUrl;
				}
				tile.getImageUrl = function() {
					return this.imageUrl;
				}
				tile.setPosition = function(xposition,yposition) {
					this.xposition = xposition;
					this.yposition = yposition;
				}
				tile.getXPosition = function() {
					return this.xposition;
				}
				tile.getYPosition = function() {
					return this.yposition;
				}
				// NOTE: only for debugging purposes
				tile.setLayer = function (layer) {
					this.layer = layer;				
				}
				// NOTE: only for debugging purposes
				tile.getLayer = function () {
					return this.layer;	
				}
				tile.setImageUrl(source)
				tile.setPosition(xposition,yposition);
				tile.setLayer(tileLayer)
				renderList.push(tile);
    		}
    		this.parentMap.getMapData().setRenderOffset(tileLayertiles.length,i);
    			
    	}
		//console.log("length: " + renderList.length)
    	this.loadAllImages(renderList);
    			
	}   
   
   // do full render of the screen
	this.render = function(imagedata,data)
	{
		var imagenames = Array();
		var renderList = [];
		// TODO: twee for loopjes kunnen met elkaar gecombineerd worden
  	 	for(var i=0;i<imagedata.length;i++)
    	{
			imagenames[imagedata[i].id] = imagedata[i].name     	  
    	}
       	 data = this._sort(data);
    	for(var i=0;i<data.length;i++) {
   		var tileLayer = data[i].layer;
   		 var tileLayertiles = tileLayer.tiles;
    		for(var t=0;t<tileLayertiles.length;t++)
    		{
    			var tiles = tileLayer.tiles;
    			var tile = tiles[t].tile


    			xoffset = tile.xoffset
    			yoffset = tile.yoffset
    			xposition = tile.xposition - this.parentMap.getMapData().getViewportX()-this.parentMap.getMapData().getStartPositionX()
    			yposition = tile.yposition - this.parentMap.getMapData().getViewportY()-this.parentMap.getMapData().getStartPositionY()
    			image_id = tile.image_id
    			
   		   tilesize = this.parentMap.getRelativeTilesize();
    			tilerequest =  "sx" + tilesize + "_" + tilesize + "_" + xoffset.toString()  + yoffset.toString()  + "ts_" + imagenames[image_id].split('.')[0];
    			source = "http://localhost:8083/ncache/" + tilerequest;
    			
    			


				tile.setImageUrl = function (imageUrl) {
					this.imageUrl = imageUrl;
				}
				tile.getImageUrl = function() {
					return this.imageUrl;
				}
				tile.setPosition = function(xposition,yposition) {
					this.xposition = xposition;
					this.yposition = yposition;
				}
				tile.getXPosition = function() {
					return this.xposition;
				}
				tile.getYPosition = function() {
					return this.yposition;
				}
				// NOTE: only for debugging purposes
				tile.setLayer = function (layer) {
					this.layer = layer;				
				}
				// NOTE: only for debugging purposes
				tile.getLayer = function () {
					return this.layer;	
				}
				tile.setImageUrl(source)
				tile.setLayer(tileLayer)
				tile.setPosition(xposition,yposition);
				renderList.push(tile);
    		}	
    	}
    	this.loadAllImages(renderList);
		
	}
	 this.move = function() {
					var c = 0;
		   		var currentContext = this;
		var anim = new Kinetic.Animation(function(frame) {
		 					
		 	 				var tiles = currentContext.parentMap.getMapData().getTiles();
		 	 				var prevViewportX = currentContext.parentMap.getMapData().getPrevViewportX();
		 	 				var prevViewportY = currentContext.parentMap.getMapData().getPrevViewportY();
		 	 				var viewportX = currentContext.parentMap.getMapData().getViewportX();
	    					var viewportY = currentContext.parentMap.getMapData().getViewportY();
		    					
	    					
          		      var tilesize = currentContext.parentMap.getRelativeTilesize()
			 				for(i=0;i<tiles.length;i++) {
							 var viewportX = currentContext.parentMap.getMapData().getViewportX();
	    					 var viewportY = currentContext.parentMap.getMapData().getViewportY();
	    					 
	 
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
		for(i=0;i<renderList.length;i++)
		{

		}


		for(var i=0;i<renderList.length;i++) {
			var img = new Image();
			renderList[i].setTileHtmlImage = function(img) {
				this._htmlImage = img;

			}
			renderList[i].getTileHtmlImage = function() {
				return this._htmlImage;
			}
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
						renderList[i].setTileImage = function(image) {
							this._kineticImage = image;
						}
						renderList[i].getTileImage = function () {
						  return this._kineticImage;
						}
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
	   	//		var tiles = this.parentMap.getMapData().getTiles();
    }
	this.getLayer = function()
	{
		return this._layer;
	}

}