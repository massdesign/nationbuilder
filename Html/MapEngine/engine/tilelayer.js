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
    			
   		
    			tilerequest = xoffset.toString()  + yoffset.toString()  + "ts_" + imagenames[image_id].split('.')[0];		
    			source = "http://localhost:8083/ncache/" + tilerequest;
  	  	 		imageURLs.push(source);  	  	 		
  	  	 		imagePos.push([xposition,yposition]);
    		}	
    	}
    	
    	this.loadAllImages(imgs,imagePos,imageURLs);   			
		
	}
	
	this.loadAllImages =   function(imgs,imagePos,imageURLs){
        for (var i=0; i<imageURLs.length; i++) {
            var img = new Image();
            imgs.push(img);
            var imUrlsLength = imageURLs.length;
            var currentContext = this;
            var imagesOK = 0;
            img.onload = function(){ 
                imagesOK++; 
              
                if (imagesOK>=imUrlsLength) {
                
                   // start(currentContext,imgs,imagePos);
          		 for(var i=0;i<imgs.length;i++){
           			 var img=new Kinetic.Image({
                	 x:imagePos[i][0]*32,
                	 y:imagePos[i][1]*32,
                	 width:32,
                	 height:32,
                	 image:imgs[i],
                	 draggable:true
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