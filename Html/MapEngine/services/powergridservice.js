
function PowergridService() {
	this._baseService = new BaseService();
	
	
	this.getAllPowergridConnections = function(callback) {
		this._baseService.doGetRequest('/powergridconnections',callback);
	
	}
}