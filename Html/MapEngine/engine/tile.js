

function Tile(tile) {
	
// TODO: standandarizeren van xposition,yposition en setX en setY deze benamingen zijn verwarrend en het is niet duidelijk welke functionaliteit nu wat is
this.xposition = 0;
this.yposition = 0;
this.imageUrl = ""
this._kineticImage = null
this.setPosition = function(xposition,yposition) {
		this.xposition = xposition;
		this.yposition = yposition;
}

this.setX = function(x) {
	this.getTileImage().setX(x);
}
this.setY = function (y) {

this.getTileImage().setY(y);
}
this.getX = function()  {
 return this.getTileImage().getX();
}
this.getY = function () {
	return  this.getTileImage().getY();
}

this.getXPosition = function() {
	return this.xposition;
}
this.getYPosition = function() {
	return this.yposition;
}
this.setImageUrl = function (imageUrl) {
	this.imageUrl = imageUrl;
}
this.getImageUrl = function() {
	return this.imageUrl;
}
this.setTileImage = function(image) {
	this._kineticImage = image;
}
this.getTileImage = function () {
	return this._kineticImage;
}
this.setTileHtmlImage = function(img) {
	this._htmlImage = img;
}
this.getTileHtmlImage = function() {
	return this._htmlImage;
}
				

}