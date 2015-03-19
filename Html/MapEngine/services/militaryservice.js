
function MilitaryService() {
	this._baseService = new BaseService();


  this.doPostRequest = function(data) {
   this._baseService.doPostJsonRequest("/military_bases.json",function() {},data);
  
  }
    this.createNewBase = function(data) {
   this._baseService.doPostJsonRequest("/military_bases/createnewbase.json",function() {},data);
  
  }
}