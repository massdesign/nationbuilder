function GridLayer(parentMap,loginstance)
{
  this.parentMap = parentMap
  this.loginstance = loginstance;
	
	this.init = function()
	{
		this._createGrid();
   }
   	
	this.render = function(imagedata,data)
	{
	}
	this.getLayer = function()
	{
		return this._layer;
	}
	 this._createGrid = function ()
    {
    	this._layer = new Kinetic.Layer();
		 var currentx = 0;
		 var currenty = 0;
						
		for(var x=0;x<this.parentMap.getMapWidth();x++)
		{
			currenty = 0;
					
				
			for(var y=0;y<this.parentMap.getMapHeight();y++)
			{
				points = [currentx+this.parentMap.getXOffset(), currenty+this.parentMap.getYOffset()
				, currentx+this.parentMap.getXOffset(),currenty+this.parentMap.getTileHeight()+this.parentMap.getYOffset()];
				  var blackLine = new Kinetic.Line({
        					points:  points,
       					 stroke: 'black',
      					  strokeWidth: 1,
       					 lineCap: 'round',
       					 lineJoin: 'round'
     					 });

   		 	this._layer.add(blackLine);
			  this.parentMap.setTileValue(x,y,0,currentx + this.parentMap.getXOffset());
			  this.parentMap.setTileValue(x,y,1,currenty + this.parentMap.getYOffset());
			  currenty = y*this.parentMap.getTileHeight();		 
			}
			
			 currentx = x*this.parentMap.getTileWidth();
		}
		
		 var currentx = 0;
		 var currenty = 0;
		for(var y=0;y<this.parentMap.getMapHeight();y++)
		{
			for(var x=0;x<this.parentMap.getMapWidth();x++)
			{
				points = [currentx+this.parentMap.getXOffset(), currenty+this.parentMap.getYOffset(),
				currentx+this.parentMap.getTileWidth()+this.parentMap.getXOffset(),currenty+this.parentMap.getYOffset()];
			  			  var blackLine = new Kinetic.Line({
        					points:  points,
       					 stroke: 'black',
      					  strokeWidth: 1,
       					 lineCap: 'round',
       					 lineJoin: 'round'
     					 });
     		  	this._layer.add(blackLine);
			  currentx = x*this.parentMap.getTileWidth();
			}
			 currenty = y*this.parentMap.getTileHeight();
		}
    } 
}