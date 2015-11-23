
function SelectLayer(parentMap,loginstance) {

this.parentMap = parentMap;
this.loginstance = loginstance;

this.currentClickedCoords = null;
this._tileValues = null;

this.init = function() {

this._layer = new Kinetic.Layer({clearBeforeDraw: true});
this._createBackgroundRect(2,2);
this._mapService = new MapService();
this._eventBus = EventBus.instance;
this._eventBus.registerClass(this)

}
this.getLayer = function()
{
	return this._layer;
}

// Event bus interface
this.getSubscribedEvents = function () {

	return [Event.INIT_GRID, Event.MAP_SIZE_CHANGE];

}


this._showSelectedTile = function showSelectedTile(x,y)
{
	var new_x = x / Config.TILE_WIDTH;
	var new_y = y / Config.TILE_HEIGHT;
	
	var newCoords = this.parentMap.getMapTranslator().translatePosition(new_x,new_y);

   var p =	this.parentMap;
   var context = p;
	this._mapService.getTileByXY(newCoords.getX(),newCoords.getY(),function(data)	{
		p.getMapData().setClickedTile(data,newCoords.getX(),newCoords.getY());	
		p.getAngularBridge().updateMapControllerScope(p.getMapData());
	});	
}
this.move = function() {

if(this.currentClickedCoords != null) {
    this._showSelectedTile(this.currentClickedCoords.getX() * Config.TILE_WIDTH, this.currentClickedCoords.getY() * Config.TILE_HEIGHT);
}
	
}

// tevent = triggered event, payload is the message we want to get accross
this.notify = function(tevent) {
	
	switch(tevent.getEventId()) {
	case Event.INIT_GRID:
		
		
		var x1 = tevent.getPayload()[0].getX();
		var y1 = tevent.getPayload()[0].getY();

		var x2 = tevent.getPayload()[1].getX();
		var y2 = tevent.getPayload()[1].getY();
		
		this._tileValues[x1][y1][0] = tevent.getPayload()[0].getA();
		this._tileValues[x2][y2][1] = tevent.getPayload()[1].getA();
	break;
	case Event.MAP_SIZE_CHANGE:
	this._tileValues = Util.createArray(tevent.getPayload().getMapSize().getX(),tevent.getPayload().getMapSize().getY())
	break;
	}
	return true;
}


this._createBackgroundRect = function(c_width,c_height)
{
	
		var currentContext = this;
    	this.parentMap.getStage().addEventListener('click', function (evt) {	
    	       
       var mouseXY = currentContext.parentMap.getStage().getPointerPosition();
    	 var canvasX = mouseXY.x;
    	 var canvasY = mouseXY.y;
       var cst = currentContext._currentTilePosition(canvasX,canvasY,currentContext.parentMap.getMapWidth(),
       currentContext.parentMap.getMapHeight());
        if(cst != null)
        {
        			var selectedRect = new Kinetic.Rect({
    					x: cst[0],
    					y: cst[1],
    					width: currentContext.parentMap.getRelativeTilesize(),
    					height: currentContext.parentMap.getRelativeTilesize(),
    					fill: 'green',
    					stroke: 'green',
    					strokeWidth: 1,
    					opacity: 0.5
			}); 		
			currentContext._layer.destroyChildren();
			currentContext._layer.add(selectedRect);
			currentContext._layer.draw();
			currentContext.currentClickedCoords = new Coordinate(cst[0] / Config.TILE_WIDTH, cst[1] / Config.TILE_HEIGHT);
		  }  	
	});
 
    }
		this._currentTilePosition = function (mousePosx,mousePosy,currentx,currenty) {
   
    	var X_AXIS = 0;
    	var Y_AXIS = 1;
    	var result = null
    	for(var x=0;x<currentx;x++)
    	{
    		for(var y=0;y<currenty;y++)
    		{ 
    		  // topleft 
    		  var topleftx = this._tileValues[x][y][X_AXIS];
    		  var toplefty = this._tileValues[x][y][Y_AXIS];
    		  // topright
    		  var toprightx = this._tileValues[x][y][X_AXIS] + this.parentMap.getRelativeTilesize();
    		  var toprighty = this._tileValues[x][y][Y_AXIS];
   		      		  
    		  var bottomleftx = this._tileValues[x][y][X_AXIS];
    		  var bottomlefty = this._tileValues[x][y][Y_AXIS] + this.parentMap.getRelativeTilesize()
    		      		  
    		  var bottomrightx = this._tileValues[x][y][X_AXIS] + this.parentMap.getRelativeTilesize();
    		  var bottomrighty = this._tileValues[x][y][Y_AXIS] + this.parentMap.getRelativeTilesize();
    		  if(mousePosx > topleftx && mousePosx < toprightx &&
    		  	 mousePosy > toprighty && mousePosy < bottomlefty)
    		  { 		   
    		   	this._showSelectedTile(this._tileValues[x][y][X_AXIS],this._tileValues[x][y][Y_AXIS]);
  					result = [this._tileValues[x][y][X_AXIS],this._tileValues[x][y][Y_AXIS]];
    		    	break;
    		  }
    		}
    	}	
    	return result;
    }

}