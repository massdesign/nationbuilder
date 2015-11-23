
function BackgroundLayer(parentMap,loginstance) {

this._assetname = "testbackground"
this._host = "localhost:8083"
this._assetName = "testbackground"
this._eventBus = EventBus.instance;
this._eventBus.registerClass(this)

this._zoomfactor = 1;


this.init = function () {
		console.log("dit wordt aangeroepen")
	 this._layer = new Kinetic.Layer({clearBeforeDraw: true});
	 
	 	var backgroundImage = new Image();	
	source = "http://" + this._host + "/ncache/" +  this._assetName + "_" + this._zoomfactor;
	backgroundImage.src  = source
	currentContext = this;
	
	if(Config.RENDER_STATIC_BACKGROUND)  {
	
		 		var img = new Kinetic.Image({
				x: 0,
				y: 0,
				// TODO: Dit moeten we in een instelling onderbrengen anders gaat dit onherroepelijk uit elkaar lopen
				width: 32*20*currentContext._zoomfactor,
				height: 32*20*currentContext._zoomfactor,
				image: backgroundImage,
				draggable: false
			}); 		
			
		this._layer.add(img);	
		this._layer.draw();		
			
	}
}
this.renderItem  =  function (data) {
	
}

// EventBus interface
this.getSubscribedEvents = function () {
        return [Event.MAP_SIZE_CHANGE];
}
this.notify = function (tevent) {

	switch (tevent.getEventId()) {
       case Event.MAP_SIZE_CHANGE:

       		this._zoomfactor = tevent.getPayload().getZoomfactor()
       		this.init()
       break;
       }
        return true;
    }

this.getLayer = function() {
	return this._layer;
}

this.move  = function() {  

} 
}

