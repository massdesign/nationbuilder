
var nationbuilderApp = angular.module('nationbuilderApp',[]);

nationbuilderApp.controller('ClickdataCtrl',function($scope) {

    var s = new MapService(); 
    var u = new UserService();
    $scope.zoomIn = function(event) {
        map.zoomIn();
    }
    $scope.zoomOut = function(event) {
        map.zoomOut();
    }
    
    $scope.scrollup = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY()+1;
		   var scrollValueX =  map.getMapData().getViewportX();
			//alert(scrollValue)		  
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }
     $scope.scrolldown = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY()-1;
		   var scrollValueX =  map.getMapData().getViewportX();
			//alert(scrollValue)		  
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }
      $scope.scrollleft = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY();
		   var scrollValueX =  map.getMapData().getViewportX()-1;
			//alert(scrollValue)		  
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }
      $scope.scrollright = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY();
		   var scrollValueX =  map.getMapData().getViewportX()+1;
			//alert(scrollValue)		  
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }
    
    $scope.availableactions  = function(event)   {
    	console.log("bladiebloe")
    }
    
    $scope._scroll = function(x,y,width,height) {
    	
 		  map.getMapData().setviewportPosition(x,y);
 		  s.getMap(function (mapData) {
            images  = Array();
            var	data = mapData[0]['layers'];
            map.init();
            s.getImages(function(imagedata)
            {
                map.setImageData(imagedata,data);
                map.render();
            });
				
        },x,y,width,height);
	   
    }
   	u.getUserById(1,function(modelData) {
   		
   		$scope.$apply(function () {
   		
   		console.log(modelData)
   		$scope.userData = modelData;
   			
   		})	
   	}); 
    // NOTE: hacky way to integreate jquery with angularjs.. this way we can use the already written mapService
    /*s.getTileByXY(2,1,function(modelData) { 
    
		$scope.$apply(function() {
		
		$scope.modelData = modelData;
		});        
    });*/

});