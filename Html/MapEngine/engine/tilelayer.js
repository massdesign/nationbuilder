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
	
	//	console.log("data that will be rendered")
	//	console.log(data)
		var imagenames = Array();
   	var imgs = [];
    	var imagePos = [];
    	var imageURLs=[];
		// TODO: twee for loopjes kunnen met elkaar gecombineerd worden
   	for(var i=0;i<imagedata.length;i++)
    	{
			imagenames[imagedata[i].id] = imagedata[i].name     	  
    	}
       	 data = this._sort(data);
    	for(var i=0;i<data.length;i++) {
   		var tileLayer = data[i].layer;
		//console.log(this.parentMap.getMapData().getRenderOffset(i))
			var currentOffset = 0;
			if (typeof(this.parentMap.getMapData().getRenderOffset(i)) != "undefined")
    		{    		
    			currentOffset = this.parentMap.getMapData().getRenderOffset(i);
    		}
    		console.log("the current offset is" + currentOffset)
   		 var tileLayertiles = tileLayer.tiles;
    		for(var t=currentOffset;t<tileLayertiles.length;t++)
    		{
    			var tiles = tileLayer.tiles;
    			var tile = tiles[t].tile //+this.parentMap.getMapData().getRenderOffset(i)].tile
    			xoffset = tile.xoffset
    			yoffset = tile.yoffset
    			var xStartPosition = this.parentMap.getMapData().getViewportX()-this.parentMap.getMapData().getStartPositionX();
    			var xMovement = this.parentMap.getMapData().getXMovement();
    			xposition = tile.xposition - xStartPosition + xMovement;
    			yposition = tile.yposition - this.parentMap.getMapData().getViewportY()-this.parentMap.getMapData().getStartPositionY()
    			image_id = tile.image_id
    			
   		    tilesize = this.parentMap.getRelativeTilesize();
    			tilerequest =  "sx" + tilesize + "_" + tilesize + "_" + xoffset.toString()  + yoffset.toString()  + "ts_" + imagenames[image_id].split('.')[0];
    			source = "http://localhost:8083/ncache/" + tilerequest;
  	  	 		imageURLs.push(source);  	  	 		
  	  	 		imagePos.push([xposition,yposition]);
    		}	
    	} 	
		this.parentMap.getMapData().addImagePosition(imagePos,"POST");
    	this.loadAllImages(imgs,imagePos,imageURLs);   	
	
	}   
   
   // do full render of the screen
	this.render = function(imagedata,data)
	{
		var imagenames = Array();
   	var imgs = [];
    	var imagePos = [];
    	var imageURLs=[];
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
				tile.setImageUrl(source)
				tile.setPosition(xposition,yposition);
				renderList.push(tile);
  	  	 		imageURLs.push(source);  	  	 		
  	  	 		imagePos.push([xposition,yposition]);
    		}	
    	}
 		this.parentMap.getMapData().addImagePosition(imagePos,"POST");
    	this.loadAllImages(imgs,imagePos,imageURLs,renderList);
		
	}
	 this.move = function() {


	   		var currentContext = this;
			//var tile = currentContext.parentMap.getMapData().getTiles()[0];
		 //	console.log(tile)
		 var anim = new Kinetic.Animation(function(frame) {
		 					
		 					//var imageGrid = currentContext.parentMap.getMapData().getImageGrid();
		 					//var imagePos = currentContext.parentMap.getMapData().getImagePosition();
							//console.log(imageGrid)
			 				var tiles = currentContext.parentMap.getMapData().getTiles();

			 				for(i=0;i<tiles.length;i++) {
							 var viewportX = currentContext.parentMap.getMapData().getViewportX();
	    					 var viewportY = currentContext.parentMap.getMapData().getViewportY();
						//	 var currentX = 0;
						//	 var currentY = 0;

	    					 var currentX = tiles[i].getTileImage().getX();
	    					 var currentY = tiles[i].getTileImage().getY();
	    					  //currentContext.imagePos[2][0] =  currentContext.imagePos[2][0]

	    					  var newX =  tiles[i].getXPosition() * currentContext.parentMap.getRelativeTilesize();
	    					  //var newX =  imagePos[i][0] * currentContext.parentMap.getRelativeTilesize();
	    					  var newY =  tiles[i].getYPosition() * currentContext.parentMap.getRelativeTilesize();
	    					//  var newY =  imagePos[i][1] * currentContext.parentMap.getRelativeTilesize();

	    					 	newX = newX - (viewportX*currentContext.parentMap.getRelativeTilesize())
	    					 	newY = newY - (viewportY*currentContext.parentMap.getRelativeTilesize())
			    			tiles[i].getTileImage().setX(newX);
						    tiles[i].getTileImage().setY(newY);
       				   // 	imageGrid[i].setX(newX);
       				   // 	imageGrid[i].setY(newY);

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

	this.loadAllImages =   function(imgs,imagePos,imageURLs,renderList){


		//var imageGrid = [];
		var imagesOK = 0;
		var imUrlsLength = renderList.length;
		var currentContext = this;
		for(var i=0;i<renderList.length;i++) {
			var img = new Image();
			renderList[i].setTileHtmlImage = function(img) {
				this._htmlImage = img;

			}
			renderList[i].getTileHtmlImage = function() {
				return this._htmlImage;
			}
			renderList[i].setTileHtmlImage(img);

			console.log(renderList[i])
			img.onload = function () {
				imagesOK++;

				if (imagesOK >= imUrlsLength) {

					for (var i = 0; i < renderList.length; i++) {
						xpos = renderList[i].getXPosition() * currentContext.parentMap.getRelativeTilesize();
						ypos = renderList[i].getYPosition() * currentContext.parentMap.getRelativeTilesize();

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

					//	imageGrid[i] = img;
						//currentContext._imagegrid[i] = img
						currentContext._layer.add(img);
					}
					//currentContext.parentMap.getMapData().addImageGrid(imageGrid, "POST");
					currentContext._layer.draw();
				}
			};
			renderList[i].getTileHtmlImage().onerror = function () {
				console.log("image load failed");
			}
			renderList[i].getTileHtmlImage().crossOrigin = "anonymous";
			renderList[i].getTileHtmlImage().src = renderList[i].getImageUrl();
		}
		this.parentMap.getMapData().setTiles(renderList)


     /*   for (var i=0; i<imageURLs.length; i++) {
            var img = new Image();
            imgs.push(img);
            var imUrlsLength = imageURLs.length;
            var currentContext = this;
            var imagesOK = 0;

            var currentContext = this

            img.onload = function(){ 
                imagesOK++; 
              
                if (imagesOK>=imUrlsLength) {
              	 
      	 		 for(var i=0;i<imgs.length;i++){
          		 	  xpos = imagePos[i][0]*currentContext.parentMap.getRelativeTilesize();
          		 	  ypos = imagePos[i][1]*currentContext.parentMap.getRelativeTilesize();
           			 var img=new Kinetic.Image({
                	 	x:xpos,
                   	y:ypos,
                   	width:currentContext.parentMap.getRelativeTilesize(),
                	 	height:currentContext.parentMap.getRelativeTilesize(),
                	 	image:imgs[i],
                	 	draggable:false
           			 });
           			 imageGrid[i] = img;
           			 //currentContext._imagegrid[i] = img
            		currentContext._layer.add(img);
        			}
        			 currentContext.parentMap.getMapData().addImageGrid(imageGrid,"POST");
        			currentContext._layer.draw();
                }
            };
            img.onerror=function(){  console.log("image load failed");} 
            img.crossOrigin="anonymous";
            img.src =imageURLs[i];
        }*/

    }
	this.getLayer = function()
	{
		return this._layer;
	}

}