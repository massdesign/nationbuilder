
function MapDataBroker() {
	
	this._mapservice = new MapService();
	

this.getMapData = function (x,y,width,height,callback) {

var currentContext = this

this._mapservice.getMap(function(mapData) {

var data = mapData[0]['layers'];
currentContext._mapservice.getImages(function(imagedata) {

// if there is something on the left that is worth loading do it 
if(x >= 5 && y >=5)
{
 
}
else 
{
 console.log("don't load tiles to the left")	
}

	
	
callback(imagedata,data)
}
);

},
x,y,width,height);

}
}