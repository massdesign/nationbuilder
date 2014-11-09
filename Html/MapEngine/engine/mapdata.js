
// MapData object for communication with Mapengine and the rest of the application
function MapData() {


this._viewportx = 0;
this._viewporty = 0;
this.clickedTile = function() {
// TODO: this seems obsolete?? Maybe delete it?
	//this.x = -1;
	//this.y = -1;
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

this.setClickedTile = function(tile) {

this.clickedTile = tile;

}

}