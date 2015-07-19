
function MapService() { 
	this._baseService = new BaseService();
	this.getImageById = function (id,callback) {
	this._baseService.doGetRequest('/images/' + id,callback);
	}
	this.getLayerById = function(id)	{
			this._baseService.doGetRequest('/layers/' + id,function() { })
	}
	this.getTileById = function(id) {
			this._baseService.doGetRequest('/tiles/' + id,function() { })
	
	}
	this.getTiles = function()	{
			this._baseService.doGetRequest('/tiles',function() {  })
	}
	
	this.getMap = function(callback,x,y,width,height) {	
			this._baseService.doGetRequest('/maps/getscreen/' + x + '/'+ y +'/' + (x+width) + '/' + (y+height),callback);
		}
	this.getImages = function(callback) {
		
		this._baseService.doGetRequest('/images',callback);
	}
	this.getLayers = function(callback) {
		
		this._baseService.doGetRequest('/layers',callback);
	}
	this.getTileByXY = function(x,y,callback) {

		this._baseService.doGetRequest('/tiles/find/' + x + "/" + y,callback)	
	}
	
	this.markSectionsForFetching = function (sections,callback) {
		// TODO: bekijken of de .json erachter netjes is anders hiervoor wat anders bedenken
		this._baseService.doPostJsonRequest('/tiles/post/marksections.json',callback,sections)

	}
	
}