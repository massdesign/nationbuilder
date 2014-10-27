
function TerritoryService() {
	this._baseService = new BaseService();


  this.doPostRequest = function(o) {
   this._baseService.doPostJsonRequest("/tiles",function() {},0);
  
  }
}