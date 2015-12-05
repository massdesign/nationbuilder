
function BackgroundLayer(parentMap,loginstance) {

this._host = "localhost:8083"
this._assetName = "testbackground"
this._eventBus = EventBus.instance;
this._eventBus.registerClass(this)

this._zoomfactor = 1;
this._previouszoomfactor = 1;
this._currentSize = 20;

this.init = function () {

	 this._layer = new Kinetic.Layer({clearBeforeDraw: true});
	 	if(Config.RENDER_STATIC_BACKGROUND)  {

	 	var backgroundImage = new Image();	
	source = "http://" + this._host + "/ncache/" +  this._assetName + "_" + this._zoomfactor;
	backgroundImage.src  = source
	currentContext = this;
	backgroundImage.onload = function () {

					// zoom in
					if(currentContext._zoomfactor >  currentContext._previouszoomfactor) 
					{
							currentContext._currentSize = currentContext._currentSize*2;
					}
					if(currentContext._zoomfactor < currentContext._previouszoomfactor) 
					{
					  		currentContext._currentSize = currentContext._currentSize/2;
					}		
		
					
		 			var img = new Kinetic.Image({
					x: 0,
					y: 0,
					// TODO: Dit moeten we in een instelling onderbrengen anders gaat dit onherroepelijk uit elkaar lopen
					width: 32*currentContext._currentSize,
					height: 32*currentContext._currentSize,
					image: backgroundImage,
					draggable: false
					
					
			}); 			
			currentContext._layer.add(img);	
			currentContext._layer.draw();
			}
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
				this._previouszoomfactor = this._zoomfactor;
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

