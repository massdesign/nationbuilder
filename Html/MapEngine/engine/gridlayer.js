function GridLayer(parentMap,loginstance)
{
  this.parentMap = parentMap
  this.loginstance = loginstance;
  this._lines = [];
  this._layer = new Kinetic.Layer();
  this._eventBus = EventBus.instance;
  this._eventBus.registerClass(this)
   
   this.draw  = function (width,height) {
   	
		this._createGrid(width,height)   
	}

   	
	this.getLayer = function()
	{
		return this._layer;
	}
	this.enableGrid = function() {
		for(i=0;i<this._lines.length;i++) {

			this._lines[i].show()
		}
		this._layer.draw();
	}
	this.clearLayer = function () {

		this._lines = []		
		this._layer.removeChildren()
		this._layer.draw()
	
	}
	this.disableGrid = function() {

		for (i = 0; i < this._lines.length; i++) {

			this._lines[i].hide()
		}
		this._layer.draw()
		
	}
	 this._createGrid = function (mapWidth,mapHeight)
    {
    	 var currentx = 0;
		 var currenty = 0;
		
		this.clearLayer()			
		
		for(var x=1;x<mapWidth+1;x++)
		{
			currenty = 0;
			for(var y=1;y<mapHeight+1;y++)
			{

				points = [currentx+this.parentMap.getXOffset(), currenty+this.parentMap.getYOffset()
				, currentx+this.parentMap.getXOffset(),currenty+this.parentMap.getRelativeTilesize()+this.parentMap.getYOffset()];
				  var blackLine = new Kinetic.Line({
        					points:  points,
       					 stroke: 'black',
      					  strokeWidth: 1,
       					 lineCap: 'round',
       					 lineJoin: 'round'
     					 });

   		 	  this._layer.add(blackLine);
			  this._lines.push(blackLine)
			  this._eventBus.notifyListeners(Event.GRID_INIT,Reflection.classType(TileLayer),new Event())
			  //this.parentMap.setTileValue(x,y,0,currentx + this.parentMap.getXOffset());
			  //this.parentMap.setTileValue(x,y,1,currenty + this.parentMap.getYOffset());
			  currenty = y*this.parentMap.getRelativeTilesize();
			}
			
			 currentx = x*this.parentMap.getRelativeTilesize();
		}
		
		 var currentx = 0;
		 var currenty = 0;
		for(var y=0;y<mapHeight+1;y++)
		{
			for(var x=0;x<mapWidth+1;x++)
			{
				points = [currentx+this.parentMap.getXOffset(), currenty+this.parentMap.getYOffset(),
				currentx+this.parentMap.getRelativeTilesize()+this.parentMap.getXOffset(),currenty+this.parentMap.getYOffset()];
			  			  var blackLine = new Kinetic.Line({
        					points:  points,
       					 stroke: 'black',
      					  strokeWidth: 1,
       					 lineCap: 'round',
       					 lineJoin: 'round'
     					 });
     		  	this._layer.add(blackLine);
				this._lines.push(blackLine)
			  currentx = x*this.parentMap.getRelativeTilesize();
            }
			 currenty = y*this.parentMap.getRelativeTilesize();
        }
    } 
    // NOTE: misschien in de toekomst dit ding verplaatsen naar een util class of iets dergelijks
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
}	