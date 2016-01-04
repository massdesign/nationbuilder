

function GridChangedPayload(mapwidth,mapheight,zoomfactor) {

this.mapSize = new XYTuple(mapwidth,mapheight);
this._zoomfactor = zoomfactor;

this.getZoomfactor = function() {
	return this._zoomfactor;
}
this.setZoomfactor = function (zoomfactor) {
	this._zoomfactor = zoomfactor;
}
this.getMapSize = function () { 
	return this.mapSize;
}
this.setMapSize = function (mapSize) {

this.mapSize =  mapSize;
}

}