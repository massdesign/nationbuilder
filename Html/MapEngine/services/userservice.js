function UserService() {
	this._baseService = new BaseService();
	
	
	this.getUserById = function(id,callback) {

		this._baseService.doGetRequest('/users/' + id,callback)
	
	}
	this.getUserByUsername = function(username,callback) {

		this._baseService.doGetRequest('/users/names/' + username,callback)
	}
}