
LayerService.TILE_LAYER = "tilelayer"
LayerService.GRID_LAYER = "gridlayer"
LayerService.ITEM_LAYER = "itemlayer"
LayerService.SELECT_LAYER = "selectlayer" 
LayerService.BACKGROUND_LAYER = "backgroundlayer"

function LayerService(_parent,_console) {

this._parent  = _parent;
this._console = _console;

this._layers = {}


this._layers[LayerService.BACKGROUND_LAYER] = new	 BackgroundLayer(this,javascript_console);
this._layers[LayerService.TILE_LAYER] = 	new TileLayer(this._parent,_console);
this._layers[LayerService.GRID_LAYER] = 	new GridLayer(this._parent,_console);
this._layers[LayerService.ITEM_LAYER] = 	new ItemLayer(this._parent,_console);
this._layers[LayerService.SELECT_LAYER] = new SelectLayer(this._parent,_console);



this.initialize = function()  {

}
this.zoomOut = function()  { 

}
this.zoomIn = function() { 

}
this.move = function() { 

for (var key in this._layers) {
  if (this._layers.hasOwnProperty(key)) {
  		this._layers[key].move()
  	}
}

}

this.redrawLayer = function(layer_id,stage) { 
// TODO: ik weet niet of dit helemaal de bedoeling is.. ik vind het nogal borky
var selectedLayer = this._layers[layer_id];
stage.remove(selectedLayer.getLayer());
stage.add(selectedLayer.getLayer())

}

this.getLayer = function(layer_id) {

return this._layers[layer_id]
}

this.registerStage = function(stage) {


for (var key in this._layers) {
  if (this._layers.hasOwnProperty(key)) {
  		console.log("current key")
  		console.log(key)
  		stage.add(this._layers[key].getLayer())
  }
}
}

}

