
function TerritoryService() {
	this._baseService = new BaseService();


  this.doPostRequest = function(data) {
  // this._baseService.doPostJsonRequest("/tiles",function() {},0);
  // ff tijdelijk voor test naar terraintype posten
   this._baseService.doPostJsonRequest("/claims.json",function() {},data);
  
  }
}