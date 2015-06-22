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
	
	function QuadTuple(x1,y1,x2,y2) {
	this._x1 = x1;
	this._x2 = x2;
	this._y1 = y1;
	this._y2 = y2;
	
	this.getX1 = function() {
		return this._x1;	
	}	
	this.getX2 = function () { 
		return this._x2;	
	}
	this.getY1 = function () {
		return this._y1;	
	}
	this.getY2 = function () {
		return this._y2;	
	}
	}	