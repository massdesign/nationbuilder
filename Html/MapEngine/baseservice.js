

function BaseService() {

//this._server = "192.168.1.6";
this._server = "localhost";
this._port = "3000"
this.doGetRequest = function(path,callback) {
                $.ajax({
                    type: 'GET',
                    url: 'http://' + this._server +  ':' + this._port +   path + '.json?callback=?',
                    dataType: 'jsonp',
                    success: callback,
                    error : function(xhr, status, error) 
                     { 
                      var err = eval("(" + xhr.responseText + ")");
  							 alert(error);

                     },
                    jsonp: 'json'
                });
             }
}

