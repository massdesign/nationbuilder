
function MapDataBroker() {
	
	this._mapservice = new MapService();
	

this.getMapData = function (x,y,width,height,callback) {

var currentContext = this

this._mapservice.getMap(function(mapData) {

var data = mapData[0]['layers'];
currentContext._mapservice.getImages(function(imagedata) {
callback(imagedata,data)
}
);

},
x,y,width,height);

}
}