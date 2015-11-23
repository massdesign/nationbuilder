function TileLayer(parentMap,loginstance)
{
  this.parentMap = parentMap
  this._host = "localhost:8083"
  this.loginstance = loginstance;
  this._layer = new Kinetic.Layer();


   this.renderTiles = function (imagedata,data) {

   	var imagenames = Array();
  		var renderList = [];
   	for(var i=0;i<imagedata.length;i++)
    	{
			imagenames[imagedata[i].id] = imagedata[i].name
    	}
       	 data = this._sort(data);
		var layerOffset = 0;
    			// skip de eerste layer als static background true is
		if(Config.RENDER_STATIC_BACKGROUND)      {
			layerOffset = 1;
		}
    	for(var i=layerOffset;i<data.length;i++) {
   		var tileLayer = data[i].layer;
   		 var tileLayertiles = tileLayer.tiles;
   		 for(var t=0;t<tileLayertiles.length;t++)
    		 {
    			var tiles = tileLayer.tiles;
    			var tile = tiles[t].tile
				xoffset = tile.xoffset
    			yoffset = tile.yoffset

    			xposition = tile.xposition - this.parentMap.getMapData().getStartPositionX()-this.parentMap.getMapData().getViewportX();
				yposition = tile.yposition - this.parentMap.getMapData().getStartPositionY()-this.parentMap.getMapData().getViewportY();

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

    	}
    	this.loadAllImages(renderList);

		}
	 this.move = function() {

	 	this.parentMap.getMapTranslator().move(this._layer,this.parentMap.getMapData().getTiles());
	 }
    this._sort = function(data)
    {
    	console.log("_sort");
    	console.log(data)
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

		console.log("renderList length" + renderList.length)

		for(var i=0;i<renderList.length;i++) {
			var img = new Image();
			renderList[i].setTileHtmlImage(img);
			img.onload = function () {
				imagesOK++;

				if (imagesOK >= imUrlsLength) {

					for (var i = 0; i < renderList.length; i++) {
						var xpos= 0,ypos=0;

							xpos =	renderList[i].getXPosition() * currentContext.parentMap.getRelativeTilesize();//-tresholdX;
						ypos = 	renderList[i].getYPosition() * currentContext.parentMap.getRelativeTilesize();//-tresholdY;

						if(!renderList[i].getRendered()) {

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
							renderList[i].setRendered(true)
						}

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