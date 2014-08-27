
function SelectLayer(parentMap,loginstance) {

this.parentMap = parentMap;
this.loginstance = loginstance;

this.init = function() {
	
this._layer = new Kinetic.Layer({clearBeforeDraw: true});
this._createBackgroundRect(2,2);

}
this.getLayer = function()
{
	return this._layer;
}

this.render = function(imagedata,data) {


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
	 loginstance.log(cst);  
        if(cst != null)
        {
        			var selectedRect = new Kinetic.Rect({
    					x: cst[0],
    					y: cst[1],
    					width: 32,
    					height: 32,
    					fill: 'green',
    					stroke: 'green',
    					strokeWidth: 1,
    					opacity: 0.5
			}); 		
			currentContext._layer.destroyChildren();
			currentContext._layer.add(selectedRect);
			currentContext._layer.draw();
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
    		  var topleftx = this.parentMap.getTileValue(x,y,X_AXIS);
    		  var toplefty = this.parentMap.getTileValue(x,y,Y_AXIS);
    		  // topright
    		  var toprightx = this.parentMap.getTileValue(x,y,X_AXIS) + this.parentMap.getTileWidth(); 
    		  var toprighty = this.parentMap.getTileValue(x,y,Y_AXIS);
    		      		  
    		  var bottomleftx = this.parentMap.getTileValue(x,y,X_AXIS);
    		  var bottomlefty = this.parentMap.getTileValue(x,y,Y_AXIS) + this.parentMap.getTileHeight()    	
    		      		  
    		  var bottomrightx = this.parentMap.getTileValue(x,y,X_AXIS) + this.parentMap.getTileWidth(); 
    		  var bottomrighty = this.parentMap.getTileValue(x,y,Y_AXIS) + this.parentMap.getTileHeight();
    		  if(mousePosx > topleftx && mousePosx < toprightx &&
    		  	 mousePosy > toprighty && mousePosy < bottomlefty)
    		  { 		   
    		   	//this._showSelectedTile(this._g_tileValues[x][y][X_AXIS],this._g_tileValues[x][y][Y_AXIS]);
  					result = [this.parentMap.getTileValue(x,y,X_AXIS),this.parentMap.getTileValue(x,y,Y_AXIS)];
    		    	break;
    		  }
    		}
    	}	
    	return result;
    }

}