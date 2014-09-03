
// MapData object for communication with Mapengine and the rest of the application
function MapData() {

this.clickedTile = function() {

	this.x = -1;
	this.y = -1;
}

this.setClickedTile = function(tile) {

this.clickedTile = tile;

}

}