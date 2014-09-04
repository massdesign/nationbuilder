function TileLayer(parentMap,loginstance)
{
  this.parentMap = parentMap
  this.loginstance = loginstance;
  
  
	this.init = function()
	{
		this._layer = new Kinetic.Layer();
   }
   	
	this.render = function(imagedata,data)
	{
		var imagenames = Array();
   	var imgs = [];
    	var imagePos = [];
    	var imageURLs=[];

   	for(var i=0;i<imagedata.length;i++)
    	{
			imagenames[imagedata[i].id] = imagedata[i].name     	  
    	}
        // TODO: sort layers
        data = this._sort(data);
    //    console.log(data[1].layer.zindex);
      //  console.log(data)
    	for(var i=0;i<data.length;i++) {
   		var tileLayer = data[i].layer;

   		 var tileLayertiles = tileLayer.tiles;
    		for(var t=0;t<tileLayertiles.length;t++)
    		{
    			var tiles = tileLayer.tiles;
    			
    			var tile = tiles[t].tile
    			xoffset = tile.xoffset
    			yoffset = tile.yoffset
    			xposition = tile.xposition
    			yposition = tile.yposition
    			image_id = tile.image_id
    			
   		        tilesize = this.parentMap.getRelativeTilesize();
    			tilerequest =  "sx" + tilesize + "_" + tilesize + "_" + xoffset.toString()  + yoffset.toString()  + "ts_" + imagenames[image_id].split('.')[0];
    			source = "http://localhost:8083/ncache/" + tilerequest;
  	  	 		imageURLs.push(source);  	  	 		
  	  	 		imagePos.push([xposition,yposition]);
    		}	
    	}
    	
    	this.loadAllImages(imgs,imagePos,imageURLs);   			
		
	}
    this._sort = function(data)
    {
        result = [];
        var currentIndex = 0;
        var done =  false;
        while(!done) {
            for (var i = 0; i < data.length; i++) {
                if(data[i].layer.zindex == currentIndex)
                {
                    console.log("found")
                    result[currentIndex] = data[i];
                    currentIndex++;
                }
            }
            if(result.length == data.length) {
                console.log("done")
                done = true
            }


        }
        console.log(result)
        return result;
    }

	this.loadAllImages =   function(imgs,imagePos,imageURLs){
        for (var i=0; i<imageURLs.length; i++) {
            var img = new Image();
            imgs.push(img);
            var imUrlsLength = imageURLs.length;
            var currentContext = this;
            var imagesOK = 0;

            var currentContext = this

            img.onload = function(){ 
                imagesOK++; 
              
                if (imagesOK>=imUrlsLength) {
                
                   // start(currentContext,imgs,imagePos);
          		 for(var i=0;i<imgs.length;i++){
           			 var img=new Kinetic.Image({
                	 x:imagePos[i][0]*currentContext.parentMap.getRelativeTilesize(),
                         y:imagePos[i][1]*currentContext.parentMap.getRelativeTilesize(),
                     width:currentContext.parentMap.getRelativeTilesize(),
                	 height:currentContext.parentMap.getRelativeTilesize(),
                	 image:imgs[i],
                	 draggable:false
            });
            currentContext._layer.add(img);
        }
        currentContext._layer.draw();
                }
            };
            img.onerror=function(){  console.log("image load failed");} 
            img.crossOrigin="anonymous";
            img.src =imageURLs[i];
        }      
    }
	this.getLayer = function()
	{
		return this._layer;
	}

}