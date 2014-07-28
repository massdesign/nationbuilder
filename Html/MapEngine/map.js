function Map(javascript_console)
{
		this.layers = [];
		
		this.jsconsole = javascript_console
 		// TODO: hardcoded config, should be pulled from the backend. Not really important for now.. 
	  	this._g_mapWidth = 40;
    	this._g_mapHeight = 40;
    	this._g_tileWidth = 32
    	this._g_tileHeight = 32
    	this._g_xoffset = 0;
    	this._g_yoffset = 0;
    	
    	this.layers.push(new TileLayer(this,javascript_console));
    	this.layers.push(new SelectLayer(this,javascript_console));
    	this.layers.push(new GridLayer(this,javascript_console));
		this._createArray = function(x,y) {	
   		var result = new Array(x);
    		for(var i=0;i<y;i++)
    		{
 		  		result[i] = new Array(5);   
 		  		for(var j=0;j<y;j++)
 		  		{
 		   		result[i][j] = new Array(2);
 		  		} 		  
    		}
    		
    		return result;
    	}
       

		
    	this._showMousePos = function(mousePos,context)
    	{
    		context.font = '12pt Calibri';
     		context.fillStyle = 'black';
      	var message = 'Mouse position: ' + mousePos.x + ',' + mousePos.y;
     		context.fillText(message, 10, 25);
   	}	 
      this._showSelectedTile = function showSelectedTile(x,y)
    	{
    		// TODO: make the declarion of context consistent.. 
      	this._context.font = '12pt Calibri';
     		this._context.fillStyle = 'black';
      	var message = 'Mouse position: ' + x + ',' + y;
     		this._context.fillText(message, 220, 25);
	 	}
    this.getStage = function() {
	 return this.stage;    
    }
    this.getTileValue = function(x,y,axis)
    { 
    	return this._g_tileValues[x][y][axis];
    }
    this.setTileValue = function(x,y,axis,value) {
    	
    	this._g_tileValues[x][y][axis] = value;
 	 }
    this.getTileHeight = function() {
      return this._g_tileHeight;
    }
    this.getTileWidth = function() {
     return this._g_tileWidth;
    }
    this.getMapWidth = function() {
    	 return this._g_mapWidth;
 	 }
 	 this.getMapHeight = function () {
		return this._g_mapWidth; 	 
 	 }
 	 this.getXOffset = function() {
		return this._g_xoffset;
 	 }
 	 this.getYOffset = function() {
		return this._g_yoffset;
 	 }
 	 
    /*this._createGrid = function ()
    {
    	 var result  = new Kinetic.Layer();
		 var currentx = 0;
		 var currenty = 0;
						
		for(var x=0;x<this._g_mapWidth;x++)
		{
			currenty = 0;
					
				
			for(var y=0;y<this._g_mapHeight;y++)
			{
				points = [currentx+this._g_xoffset, currenty+this._g_yoffset, currentx+this._g_xoffset,currenty+this._g_tileHeight+this._g_yoffset];
				  var blackLine = new Kinetic.Line({
        					points:  points,
       					 stroke: 'black',
      					  strokeWidth: 1,
       					 lineCap: 'round',
       					 lineJoin: 'round'
     					 });

   		 	result.add(blackLine);
			  this._g_tileValues[x][y][0] = currentx + this._g_xoffset;
			  this._g_tileValues[x][y][1] = currenty + this._g_yoffset;
			  currenty = y*this._g_tileHeight;		 
			}
			
			 currentx = x*this._g_tileWidth;
		}
		
		 var currentx = 0;
		 var currenty = 0;
		for(var y=0;y<this._g_mapHeight;y++)
		{
			for(var x=0;x<this._g_mapWidth;x++)
			{
				points = [currentx+this._g_xoffset, currenty+this._g_yoffset,currentx+this._g_tileWidth+this._g_xoffset,currenty+this._g_yoffset];
			  			  var blackLine = new Kinetic.Line({
        					points:  points,
       					 stroke: 'black',
      					  strokeWidth: 1,
       					 lineCap: 'round',
       					 lineJoin: 'round'
     					 });
     		  	result.add(blackLine);
			  currentx = x*this._g_tileWidth;
			}
			 currenty = y*this._g_tileHeight;
		}
		
		return result;
    } */
    /*
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
            currentContext.maplayer.add(img);
        }
        currentContext.maplayer.draw();
                }
            };
            img.onerror=function(){  console.log("image load failed");} 
            img.crossOrigin="anonymous";
            img.src =imageURLs[i];
        }      
    }*/
	this.init = function()
	{
       // create the Kinetic stage and layer
  	  	 //	this._stage = new Kinetic.Stage({
    	  //  container: 'container',
    	  //  width: 1500,
     	 //  height: 1200
   	// }); 
   	 // duplicaat om het even te laten werken
   	 this.stage = new Kinetic.Stage({
    	    container: 'container',
    	    width: 1500,
     	   height: 1200
   	 }); 
   	 
    	 
    	 //this.maplayer = new Kinetic.Layer();
   	 
   
      this._g_tileValues = this._createArray(this._g_mapWidth,this._g_mapHeight);   
      var canvas = document.getElementById('myCanvas');
		//this.stage.add(this.maplayer);
		//this.stage.add(this._createGrid());
		for(i=0;i<this.layers.length;i++)  {
			this.layers[i].init();
			this.stage.add(this.layers[i].getLayer());
		}   	
 		var currentObject = this;

      this._context = canvas.getContext('2d');
		
   }
   this.render = function(imagedata,data) {

   	/*var imagenames = Array();
   	var imgs = [];
    	var imagePos = [];
    	var imageURLs=[];
    	*/

		for(i=0;i<this.layers.length;i++)  {
			this.layers[i].render(imagedata,data);	
		}  
		/*
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
    	}*/
 
    	//this.loadAllImages(imgs,imagePos,imageURLs);   	
   }   
}
var map = new Map(console);