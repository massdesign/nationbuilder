function Map(javascript_console,applicationName)
{
		this._mapData = new MapData();
		this._gridLayer = new GridLayer(this, javascript_console);
		this._tileLayer = new TileLayer(this,javascript_console);
		this._itemLayer = new ItemLayer(this,javascript_console);
		this._mapDataBroker = new  MapDataBroker(this,7,7,2);
		this._militaryService = new MilitaryService();
		this._mapTranslator = new MapTranslator(this);
		
		this._angularBridge = new AngularBridge();
		this._angularBridge.setController(applicationName);
		this.layers = [];
		this._context = null;
		this.jsconsole = javascript_console
		 	
 		// TODO: hardcoded config, should be pulled from the backend. Not really important for now.. 
	  	this._g_mapWidth = 20;
    	this._g_mapHeight = 20;
        // TODO: replace width/height with tilesize we only support symmetrical tiles
    	this._g_tileWidth = 32;
    	this._g_tileHeight = 32;
        this._g_tilesize = 32;
    	this._g_xoffset = 0;
    	this._g_yoffset = 0;
      this._zoomlevel = 1;


      this._imagedata = isNaN;
      this._data = isNaN;
    	
    	this.layers.push(this._tileLayer);
    	this.layers.push(new SelectLayer(this,javascript_console));
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
        if(this._zoomlevel < 16)
        {
            this._zoomlevel *= 2;
            this.init();
            this.render();
        }

    }
    this
    this.zoomOut = function()
    {
        if(this._zoomlevel != 1) {
            this._zoomlevel /= 2;
            this.init();
            this.render();
        }
    }
 	this.getMapData = function() {
		return this._mapData; 	
 	}
     this.getZoomlevel = function() {
         return this._zoomlevel;
     }
    this.getRelativeTilesize = function() {

        return  Math.ceil(this.getTileSize()/this.getZoomlevel());

    }

	this.init = function()
	{
	 	var currentContext = this;
	   this.getMapData().setStartPositionX(20);
		this.getMapData().setStartPositionY(30);
   	 this.stage = new Kinetic.Stage({
    	    container: 'container',
    	    width: currentContext._g_tileWidth* currentContext._g_mapWidth ,
     	     height: currentContext._g_tileHeight * currentContext._g_mapHeight
   	 });

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
		this.layers[0].move()
   }
   this.drawItem = function (item) {
   
    this._itemLayer.renderItem(item);
   }
   /*this.render = function() {
		console.log("render will be called")
		for(i=0;i<this.layers.length;i++)  {
			this.layers[i].render(this._imagedata,this._data);
		}  
   }*/
   this.getCanvas = function () {
   	return this._context;
   }   
}