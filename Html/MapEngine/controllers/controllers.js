
var nationbuilderApp = angular.module('nationbuilderApp',[]);

nationbuilderApp.controller('ClickdataCtrl',function($scope) {

    var s = new MapService(); 
    var u = new UserService();
    var t = new TerritoryService();
    $scope.zoomIn = function(event) {
        map.zoomIn();
    }
    $scope.zoomOut = function(event) {
        map.zoomOut();
    }
    
    $scope.scrollup = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY()+1;
		   var scrollValueX =  map.getMapData().getViewportX();
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }
     $scope.scrolldown = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY()-1;
		   var scrollValueX =  map.getMapData().getViewportX();
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }
      $scope.scrollleft = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY();
		   var scrollValueX =  map.getMapData().getViewportX()-1;
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }
		// alle militaire acties zijn afgekort met mil_    
    	$scope.mil_lct = function(event) {
    		// by wijze van test een terraintype posten omdat het lekker kort is
    		var newClaim = {}
    		newClaim.state_id = 1;
    		console.log(JSON.stringify(newClaim))
			// TODO: ervoor zorgen dat dit domeinmodel van selected tile er wat beter uitziet het is nu een bij elkaar geraapt zooitje
			newClaim.tile_id = map.getMapData().getClickedTile().tile.tiles[0].tile.id;
			newClaim.state_id = $scope.userData.state.id;
    		t.doPostRequest(newClaim)
    	
    	}
      $scope.scrollright = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY();
		   var scrollValueX =  map.getMapData().getViewportX()+1;
			//alert(scrollValue)		  
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }

    $scope.showgrid = function(event) {
        if(event.target.checked) {

            map.enableGrid()
        }
        else
        {
            map.disableGrid()
        }
      //  alert('even een testje doen of dit werkt')
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

});