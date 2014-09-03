
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
	
	this.getMap = function(callback) {	
			
			this._baseService.doGetRequest('/maps',callback);
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
	
}