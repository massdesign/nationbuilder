

// class that will act as a bridge between the Mapengine and the AngularJs framework
// will be used to marshall data between the two
function AngularBridge() {
	
	this._controllerContext = null; 

this.updateMapControllerScope = function(model) {

	if(this._controllerContext != null)
	{
		var $scope = angular.element(this._controllerContext).scope();
		$scope.$apply(function() { 

		$scope.mapData = model;	

		});
		
	}
	else 	
	{
			console.log("no controller set, can't complete request");
	}
}



this.setController = function(controllerName) {

	this._controllerContext = document.querySelector('[ng-app=' + controllerName + ']')
}

}

