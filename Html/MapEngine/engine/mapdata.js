
// MapData object for communication with Mapengine and the rest of the application
function MapData() {

this.clickedTile = function() {

	this.x = -1;
	this.y = -1;
}

this.setClickedTile = function(x,y) {

this.clickedTile.x = x;
this.clickedTile.y = y;

}

}