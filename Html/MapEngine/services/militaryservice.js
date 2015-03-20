
function MilitaryService() {
	this._baseService = new BaseService();


  this.doPostRequest = function(data) {
   this._baseService.doPostJsonRequest("/militarystrongholds.json",function() {},data);
  
  }
    this.createNewBase = function(data,callback) {
   this._baseService.doPostJsonRequest("/militarystrongholds/createnewbase.json",callback,data);
  
  }
}