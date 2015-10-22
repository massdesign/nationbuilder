	  function SectionLocation(X,Y)  {		
		this.X = X;
		this.Y = Y;
		this._needsfetching
			
		this.getX = function () {
			return this.X;		
		}		
		this.getY = function () {
			return this.Y;		
		}
		this.getNeedsFetching = function () { 
			return  this._needsfetching;		
		}
	}	