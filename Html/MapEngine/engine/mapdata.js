
// MapData object for communication with Mapengine and the rest of the application
function MapData() {


this._viewportx = 0;
this._viewporty = 0;
this.clickedTile = function() {
// TODO: this seems obsolete?? Maybe delete it?
	this.x = -1;
	this.y = -1;
}

this.setviewportPosition = function(x,y) { 
	this._viewportx = x;
	this._viewporty = y
}

this.getViewportX = function() {
	return this._viewportx;
}
this.getViewportY = function() {
	return this._viewporty;
}

this.setClickedTile = function(tile,newX,newY) {

var ClickedTile = function (tile,newX,newY) { 
this.tile = tile;
this.newX = newX;
this.newY = newY;

this.getNewX = function() {

	return this.newX;
	} 
this.getNewY = function () {

	return this.newY;
	}
}
 this.clickedTile = new ClickedTile(tile,newX,newY)
}
this.getClickedTile = function() {

return this.clickedTile;
}

this.getNewY = function () {
	return this.newY;
}

this.getClickedTile = function() { 

return this.clickedTile;

}
}

