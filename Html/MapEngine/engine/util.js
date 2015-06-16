	  function SectionLocation(X,Y)  {		
		this.X = X;
		this.Y = Y;
			
		this.getX = function () {
			return this.X;		
		}		
		this.getY = function () {
			return this.Y;		
		}
	}
	
	function XYTuple(x,y) {
	this._x = x;
	this._y = y;
	
	this.getX = function () {
	return this._x;
	}	
	this.getY = function () {
	return this._y;	
	}
	}	