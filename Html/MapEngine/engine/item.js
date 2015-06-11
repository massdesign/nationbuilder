
function Item() {
	
	
 this.setItemImage = function(image) {
	this._kineticImage = image;
 }
 this.getItemImage = function () {
	return this._kineticImage;
 }
 	
this.setX = function(x) {
	this.getItemImage().setX(x);
}
this.setY = function (y) {

this.getItemImage().setY(y);
}
this.getX = function()  {
 return this.getItemImage().getX();
}
this.getY = function () {
	return  this.getItemImage().getY();
}
this.setPosition = function(xposition,yposition) {
		this.xposition = xposition;
		this.yposition = yposition;
}

this.setTileImage = function(image) {
	this._kineticImage = image;
}
this.getTileImage = function () {
	return this._kineticImage;
}

} 