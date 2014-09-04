
var nationbuilderApp = angular.module('nationbuilderApp',[]);

nationbuilderApp.controller('ClickdataCtrl',function($scope) {

    var s = new MapService(); 
    $scope.zoomIn = function(event) {
        map.zoomIn();
    }
    $scope.zoomOut = function(event) {
        map.zoomOut();
    }
    // NOTE: hacky way to integreate jquery with angularjs.. this way we can use the already written mapService
    s.getTileByXY(2,1,function(modelData) { 
    
		$scope.$apply(function() {
		
		$scope.modelData = modelData;
		});        
    });

});