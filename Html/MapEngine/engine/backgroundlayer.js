
function BackgroundLayer(parentMap,loginstance) {

this._assetname = "testbackground"
this._host = "localhost:8083"
this._assetName = "testbackground"

this.init = function () {
	 this._layer = new Kinetic.Layer({clearBeforeDraw: true});
	 
	 	var backgroundImage = new Image();	
	source = "http://" + this._host + "/ncache/" +  this._assetName ;
	backgroundImage.src  = source
	
	if(Config.RENDER_STATIC_BACKGROUND)  {
	
		 		var img = new Kinetic.Image({
				x: 0,
				y: 0,
				// TODO: Dit moeten we in een instelling onderbrengen anders gaat dit onherroepelijk uit elkaar lopen
				width: 32*20,
				height: 32*20,
				image: backgroundImage,
				draggable: false
			}); 		
			
		this._layer.add(img);	
		this._layer.draw();		
			
	}
}
this.renderItem  =  function (data) {
	


}
this.getLayer = function() {
	return this._layer;
}

this.move  = function() {  

} 
}

