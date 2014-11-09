function TileLayer(parentMap,loginstance)
{
  this.parentMap = parentMap
  this.loginstance = loginstance;
  // TODO: get rid of arrays that are not much of use anymore
  // array that keeps track of all loaded kineticjs objects
  this._imagegrid = [];
  this. imagePos = []
	this.init = function()
	{
		this._layer = new Kinetic.Layer();
   }
   	
	this.render = function(imagedata,data)
	{
		var imagenames = Array();
   	var imgs = [];
    	//var imagePos = [];
    	var imageURLs=[];

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
    			xposition = tile.xposition - this.parentMap.getMapData().getViewportX()
    			yposition = tile.yposition - this.parentMap.getMapData().getViewportY()
    			image_id = tile.image_id
    			
   		    tilesize = this.parentMap.getRelativeTilesize();
    			tilerequest =  "sx" + tilesize + "_" + tilesize + "_" + xoffset.toString()  + yoffset.toString()  + "ts_" + imagenames[image_id].split('.')[0];
    			source = "http://localhost:8083/ncache/" + tilerequest;
  	  	 		imageURLs.push(source);  	  	 		
  	  	 		this.imagePos.push([xposition,yposition]);
    		}	
    	}
    	

    	this.loadAllImages(imgs,this.imagePos,imageURLs);   			
		
	}
	 this.doAnimation = function() { 
	    var currentContext = this;

		 var anim = new Kinetic.Animation(function(frame) {
		 	
		 			

							for(i=0;i<currentContext._imagegrid.length;i++) {	
							 var viewportX = currentContext.parentMap.getMapData().getViewportX();
	    					 var viewportY = currentContext.parentMap.getMapData().getViewportY();
	    					 var currentX = currentContext._imagegrid[i].getX();
	    					 var currentY = currentContext._imagegrid[i].getY();
	    					 //currentContext.imagePos[2][0] =  currentContext.imagePos[2][0]
	    					 var newX =  currentContext.imagePos[i][0] * currentContext.parentMap.getRelativeTilesize();
	    					 var newY =  currentContext.imagePos[i][1] * currentContext.parentMap.getRelativeTilesize();
	    					 newX = newX + (viewportX*currentContext.parentMap.getRelativeTilesize())
	    					 newY = newY + (viewportY*currentContext.parentMap.getRelativeTilesize())
       					 currentContext._imagegrid[i].setX(newX);
       				    currentContext._imagegrid[i].setY(newY);
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
                    console.log("found currentIndex is: " + currentIndex)
                    
                    result[currentCounter] = data[i];
                    currentIndex++;
                    currentCounter++;
                    indexfound = true
                }
            }
            if(result.length == data.length) {
                console.log("done")
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
           			 currentContext._imagegrid[i] = img
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