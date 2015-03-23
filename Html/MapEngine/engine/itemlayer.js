
function ItemLayer(parentMap,loginstance) {
this.parentMap = parentMap;
this.loginstance = loginstance;
this._host = "localhost:8083"

this.init = function() {

 this._layer = new Kinetic.Layer({clearBeforeDraw: true});
 var currentContext = this;
 var newAsset = new Image();
 
assetUrl = "http://" + this._host + "/assets/mil_symbol.png";

newAsset.src = assetUrl;
 var img = new Kinetic.Image({
				x: 32*8,
				y: 32*10,
				width: currentContext.parentMap.getRelativeTilesize(),
				height: currentContext.parentMap.getRelativeTilesize(),
				image: newAsset,
				draggable: false
			}); 		
							
this._layer.add(img);
this._layer.draw();
}
this.render = function(imagedata,data) {


}
this.getLayer = function() {
	return this._layer;
}
}