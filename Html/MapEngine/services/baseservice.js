function BaseService() {

//this._server = "192.168.1.6";
this._server = "localhost";
this._port = "8083"
this.doGetRequest = function(path,callback) {
                $.ajax({
                    type: 'GET',
                    url: 'http://' + this._server +  ':' + this._port +   path + ".json",
                    success: callback,
                    error : function(xhr, status, error) 
                     { 
  							 alert(error);

                     },
                });
             }
this.doPostJsonRequest = function(path,callback,jsonObject) { 
                $.ajax({
                    type: 'POST',
      				  url: 'http://' + this._server +  ':' + this._port +   path,
						  contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(jsonObject),
                    success: callback,
                    error : function(xhr, status, error) 
                     { 

  							 alert(error);

                     },
                });

					 
		}
}
