function Map(javascript_console,applicationName)
{
		this._mapData = new MapData();
		this._angularBridge = new AngularBridge();
		this._angularBridge.setController(applicationName);
		this.layers = [];
		this._context = null;
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
 	 this.getAngularBridge = function() {

		return this._angularBridge; 	 
 	 }
 	this.getMapData = function() {
		return this._mapData; 	
 	}

	this.init = function()
	{
   	 // duplicaat om het even te laten werken
   	 this.stage = new Kinetic.Stage({
    	    container: 'container',
    	    width: 1500,
     	   height: 1200
   	 }); 
   	 
   	
      this._g_tileValues = this._createArray(this._g_mapWidth,this._g_mapHeight);   
      var canvas = document.getElementById('myCanvas');
		for(i=0;i<this.layers.length;i++)  {
			this.layers[i].init();
			this.stage.add(this.layers[i].getLayer());
		}   	
 		var currentObject = this;

      this._context = canvas.getContext('2d');
		
   }
   this.render = function(imagedata,data) {

		for(i=0;i<this.layers.length;i++)  {
			this.layers[i].render(imagedata,data);	
		}  
   }
   this.getCanvas = function () {
   	return this._context;
   }   
}
// param1 console handle
// param2 name of the application to bind with angularJs framework ng-app tag
var map = new Map(console,"nationbuilderApp");