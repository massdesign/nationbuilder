	
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
//this._renderOffsetY = 0;

this.clickedTile = function() {
}
this.getTiles = function() {
	return this._tiles;
}
this.setTiles = function(tiles)	 {
	console.log("de tiles " + tiles)
	this._tiles = tiles;
}
this.getXMovement = function () {
	return this._xMovement;
}
this.getYMovement = function () {
	return this._yMovement;
}
this.setXMovement = function (x) {
this._xMovement = x;
}
this.setYMovement = function (y) {
this._yMovement = y;
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
/*this.setRenderOffsetY = function (y) {
this._renderOffsetY = y;
} */

this.setClickedTile = function(tile) {

this.clickedTile = tile;

}
// TODO: rename this method
this.getImageGrid = function () {
	return this._imageGrids;
}

this.getImagePosition = function() {
	return this._imagePositions;
}
this.addImagePosition = function(imagePos,pos) {
if(pos == "PRE") {
this._imagePositions =  imagePos.concat(this._imagePositions)
}
else if(pos == "POST") {

	for(i=0;i<imagePos.length;i++)
	{
		this._imagePositions.push([imagePos[i][0],imagePos[i][1]]);
	}
	this._imagePositions = this._imagePositions.concat(imagePos)
}
}
this.addImageGrid = function(imagePos,pos) {

if(pos == "PRE")
{
	this._imageGrids = imagePos.concat(this._imageGrids);	
}
else if(pos == "POST")
{
	this._imageGrids =  this._imageGrids.concat(imagePos)
}
}
}