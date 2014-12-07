

function Tile(tile) {
	

this.xposition = 0;
this.yposition = 0;
this.imageUrl = ""
this._kineticImage = null
this.setPosition = function(xposition,yposition) {
		this.xposition = xposition;
		this.yposition = yposition;
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