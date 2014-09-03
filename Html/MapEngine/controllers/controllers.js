
var nationbuilderApp = angular.module('nationbuilderApp',[]);

nationbuilderApp.controller('ClickdataCtrl',function($scope) {

 /*$scope.phones = [
    {'name': 'Nexus S',
     'snippet': 'Fast just got faster with Nexus S.'},
    {'name': 'Motorola XOOM™ with Wi-Fi',
     'snippet': 'The Next, Next Generation tablet.'},
    {'name': 'MOTOROLA XOOM™',
     'snippet': 'The Next, Next Generation tablet.'}
  ]; 
  $scope.naam = "henkie"
  */
    var s = new MapService(); 
    
    // NOTE: hacky way to integreate jquery with angularjs.. this way we can use the already written mapService
    s.getTileByXY(2,1,function(modelData) { 
    
		$scope.$apply(function() {
		
		$scope.modelData = modelData;
		});        
    });

});