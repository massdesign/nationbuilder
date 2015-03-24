
function ItemLayer(parentMap,loginstance) {
this.parentMap = parentMap;
this.loginstance = loginstance;
this._host = "localhost:8083"

this.assetUrl = "http://" + this._host + "/assets/mil_symbol.png";

this.init = function() {

 this._layer = new Kinetic.Layer({clearBeforeDraw: true});
}

this.renderItem = function(data) { 
var currentContext = this;
	if(data.tiles[0] != null) {
		
		var newAsset = new Image();
		newAsset.src = this.assetUrl;
 		var img = new Kinetic.Image({
				x: data.tiles[0].tile.xposition*currentContext.parentMap.getRelativeTilesize(),
				y: data.tiles[0].tile.yposition*currentContext.parentMap.getRelativeTilesize(),
				width: currentContext.parentMap.getRelativeTilesize(),
				height: currentContext.parentMap.getRelativeTilesize(),
				image: newAsset,
				draggable: false
			}); 		
		this._layer.add(img);
	}
	this._layer.draw();
}
this.renderItems = function(data) { 

var currentContext = this;

for(i=0;i<data.length;i++)

	if(data[i].tiles[0] != null) {
		console.log(data[i].tiles[0].tile)
		
		var newAsset = new Image();
		newAsset.src = this.assetUrl;
 		var img = new Kinetic.Image({
				x: data[i].tiles[0].tile.xposition*currentContext.parentMap.getRelativeTilesize(),
				y: data[i].tiles[0].tile.yposition*currentContext.parentMap.getRelativeTilesize(),
				width: currentContext.parentMap.getRelativeTilesize(),
				height: currentContext.parentMap.getRelativeTilesize(),
				image: newAsset,
				draggable: false
			}); 		
		this._layer.add(img);
	}
	this._layer.draw();
}

this.getLayer = function() {
	return this._layer;
}
}