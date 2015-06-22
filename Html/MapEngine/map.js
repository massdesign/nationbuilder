function Map(javascript_console,applicationName)
{
		this._mapData = new MapData();
		this._gridLayer = new GridLayer(this, javascript_console);
		this._tileLayer = new TileLayer(this,javascript_console);
		this._itemLayer = new ItemLayer(this,javascript_console);
		this._selectLayer = new SelectLayer(this,javascript_console);
		this._mapDataBroker = new  MapDataBroker(this,Config.CHUNK_WIDTH,Config.CHUNK_HEIGHT,2);
		this._militaryService = new MilitaryService();
		this._mapTranslator = new MapTranslator(this);
		
	

		
		this._angularBridge = new AngularBridge();
		this._angularBridge.setController(applicationName);
		this.layers = [];
		//this._context = null;
		this.jsconsole = javascript_console
		 	
 		// TODO: hardcoded config, should be pulled from the backend. Not really important for now.. 
	  	this._g_mapWidth = Config.MAP_WIDTH;
    	this._g_mapHeight = Config.MAP_HEIGHT;
        // TODO: replace width/height with tilesize we only support symmetrical tiles
    	this._g_tileWidth = Config.TILE_WIDTH;
    	this._g_tileHeight = Config.TILE_HEIGHT;
    	
      this._g_tilesize = Config.TILE_SIZE;
    	this._g_xoffset = 0;
    	this._g_yoffset = 0;
    	// standard we are zoomed in at level 5, so, we can go zoom out max 5 steps
      this._zoomfactor = 5;
    	this._mapDataBroker = new  MapDataBroker(this,Config.CHUNK_WIDTH,Config.CHUNK_HEIGHT,2,this._g_mapWidth,this._g_mapHeight);


      this._imagedata = isNaN;
      this._data = isNaN;
    	
    	this.layers.push(this._tileLayer);
    	this.layers.push(this._selectLayer);
    	this.layers.push(this._gridLayer);
    	this.layers.push(this._itemLayer);
    	
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
    this.getStage = function() {
	 return this.stage;    
    }
	this.enableGrid = function() {
		this._gridLayer.enableGrid();
	}
	this.disableGrid = function() {
		this._gridLayer.disableGrid();
	}
    this.getTileValue = function(x,y,axis)
    { 
    	return this._g_tileValues[x][y][axis];
    }
    // TODO: this is crappy.. why do we need two parameters?? and find an decent naming scheme for it.
    this.setImageData = function(imagedata,data){
        this._imagedata = imagedata;
        this._data = data
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
    this.getTileSize = function() {
        return this._g_tilesize;
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
 	 this.getMapTranslator = function () {
		return this._mapTranslator; 	 
 	 }

 	 this.getAngularBridge = function() {

		return this._angularBridge; 	 
 	 }
    this.zoomIn = function()
    {
    	this._zoomfactor += 1;
    	var objectToScale = this.stage;	
    	
    	

		objectToScale.setScale({
            x: objectToScale.getScale().x*2,
            y: objectToScale.getScale().y*2
        });    
                objectToScale.draw();
    }
    
    this.zoomOut = function()
    {			// increases with zoom
    			this._zoomfactor -= 1;
 				var objectToScale = this.stage;
 				console.log(objectToScale); 
 				var currentContext = this;
 					var tiles = this.getMapData().getTiles();
 						var anim = new Kinetic.Animation(function(frame) {
							console.log(objectToScale.getScale()); 							
 					  this.stop()
     				},this._selectLayer);
					anim.start()
								
				 objectToScale.setScale({
            	x: objectToScale.getScale().x/2,
            	y: objectToScale.getScale().y/2
       		 });  
								this._mapDataBroker.getMapData(null,function(imageData,data) {
					currentContext._tileLayer.renderTiles(imageData,data,false)    			
					},this._zoomfactor);
				//}

           objectToScale.draw();    
    }
 	this.getMapData = function() {
		return this._mapData; 	
 	}
     this.getZoomlevel = function() {
         return this._zoomlevel;
     }
    this.getRelativeTilesize = function() {

    //    return  Math.ceil(this.getTileSize()/this.getZoomlevel());
		return this.getTileSize();
    }

	this.init = function()
	{
	 	var currentContext = this;
	   this.getMapData().setStartPositionX(10);
		this.getMapData().setStartPositionY(20);
   	 this.stage = new Kinetic.Stage({
    	    container: 'container',
    	    width: currentContext._g_tileWidth* currentContext._g_mapWidth ,
     	     height: currentContext._g_tileHeight * currentContext._g_mapHeight
   	 });
   	 
   	 console.log("getScale");
   	 console.log(this.stage.getScale())

      this._g_tileValues = this._createArray(this._g_mapWidth+1,this._g_mapHeight+1);

	
		var startX = this.getMapData().getStartPositionX();
		var startY = this.getMapData().getStartPositionY();
		
		for(i=0;i<this.layers.length;i++)  {
			this.layers[i].init();
			this.stage.add(this.layers[i].getLayer());
		}   
		this._mapDataBroker.getInitialMapData(startX,startY,function(imageData,data) {
		 currentContext.setImageData(imageData,data);
		 if(currentContext._tileLayer != null) {
       currentContext._tileLayer.renderTiles(imageData,data,true)
    	 }
    	 
		});
		this._militaryService.getMilitaryStrongholds(function(data) {
			if(currentContext._itemLayer != null) {
				currentContext._itemLayer.renderItems(data)
			} 
		});
		
   }
   this.move = function () {
   			var currentContext = this;
				this._mapDataBroker.getMapData(1,function(imageData,data) {
					currentContext._tileLayer.renderTiles(imageData,data,false)
					
					
					    			
		});
		// NOTE: volgorde is hier belangrijk.. de _tilelayer moet eerst gemoved worden.. dan pas de select layer.. heeft te maken met getMapdata.getClickedTile() en getViewportPosition
		this._tileLayer.move();
		this._itemLayer.move();
		this._selectLayer.move();
		
		//this.layers[0].move()
		this
   }
   this.drawItem = function (item) {
   
    this._itemLayer.renderItem(item);
   }
}
