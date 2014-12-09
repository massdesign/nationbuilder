function GridLayer(parentMap,loginstance)
{
  this.parentMap = parentMap
  this.loginstance = loginstance;
  this._lines = [];
  this._layer = null;

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
	this.enableGrid = function() {

		for(i=0;i<this._lines.length;i++) {

			this._lines[i].show()
		}
		this._layer.draw();
	}
	this.disableGrid = function() {
		for (i = 0; i < this._lines.length; i++) {

			this._lines[i].hide()
		}
		this._layer.draw();
	}
	 this._createGrid = function ()
    {
    	this._layer = new Kinetic.Layer();
		 var currentx = 0;
		 var currenty = 0;
						
		for(var x=1;x<this.parentMap.getMapWidth()+1;x++)
		{
			currenty = 0;
			for(var y=1;y<this.parentMap.getMapHeight()+1;y++)
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
			  this.parentMap.setTileValue(x,y,0,currentx + this.parentMap.getXOffset());
			  this.parentMap.setTileValue(x,y,1,currenty + this.parentMap.getYOffset());
			  currenty = y*this.parentMap.getRelativeTilesize();
			}
			
			 currentx = x*this.parentMap.getRelativeTilesize();
		}
		
		 var currentx = 0;
		 var currenty = 0;
		for(var y=0;y<this.parentMap.getMapHeight()+1;y++)
		{
			for(var x=0;x<this.parentMap.getMapWidth()+1;x++)
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
}