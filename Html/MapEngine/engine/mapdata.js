	
// MapData object for communication with Mapengine and the rest of the application
function MapData() {


this._viewportx = 0;
this._viewporty = 0;
this._prevviewportx = 0;
this._prevviewporty = 0;

this._startPositionX = 0;
this._startPositionY = 0;

this._renderOffset = [];
this._imageGrids = [];
this._imagePositions = [];
this._xMovement = 0;
this._yMovement = 0;
this._tiles = [];
this._tresholdX = 0;
this._tresholdY = 0;


this.setMapWidth = function (width) {
	this._mapWidth = width;
}
this.setMapHeight = function(height) {
	this._mapHeight = height;
}

this.clickedTile = function() {
}
this.getTiles = function() {
	return this._tiles;
}
this.getItems = function() {
 return this._items;	
}
this.setItems = function (items) {
	this._items = items;
}
this.setTiles = function(tiles)	 {
	this._tiles = tiles;
}
this.setYMovement = function (y) {
this._yMovement = y;
}
this.setTresholdX = function (treshold) {
this._tresholdX = treshold;
}
this.setTresholdY = function (treshold) {
this._tresholdY = treshold;
}
this.getTresholdX = function () {
return this._tresholdX;
}
this.getTresholdY = function () {
return this._tresholdY;
}
this.getPrevViewportX = function() {
	return this._prevviewportx;
}
this.getPrevViewportY = function() {
	return this._prevviewporty;
}
this.setPrevViewport = function(prevx,prevy) {
	this._prevviewportx = prevx;
	this._prevviewporty = prevy;
}
this.setviewportPosition = function(x,y) { 
	this.setPrevViewport(this._viewportx,this._viewporty)
	this._viewportx = x;
	this._viewporty = y;
}

this.getStartPositionX = function() {
	return this._startPositionX;	
}
this.getStartPositionY = function() {
	return this._startPositionY;
}
this.setStartPositionX = function(x) {
	this._startPositionX = x;
}
this.setStartPositionY = function(y) {
	this._startPositionY = y;
}


this.getViewportX = function() {
	return this._viewportx;
}
this.getViewportY = function() {
	return this._viewporty;
}
this.setRenderOffset = function(x,layerId) {

this._renderOffset[layerId] = x;
}
this.getRenderOffset = function (i) { 
	
return this._renderOffset[i];
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
 console.log(this.clickedTile)
return this.clickedTile;
}

}
