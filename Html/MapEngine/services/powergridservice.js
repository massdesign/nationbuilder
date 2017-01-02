
function PowergridService() {
	this._baseService = new BaseService();
	
	this.getAllPowergridConnections = function(callback) {
		this._baseService.doGetRequest('/power_connections/fetch/connections',callback);
	}
}