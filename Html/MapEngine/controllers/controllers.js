
var nationbuilderApp = angular.module('nationbuilderApp',[]);
	//jsimport("services/mapservice.js")
	//jsimport("services/baseservice.js")
	


console.log("hier komt hij toch?")
nationbuilderApp.controller('ClickdataCtrl',function($scope) {

	 jsimport("services/baseservice.js","services/mapservice.js","services/userservice.js","services/militaryservice.js","services/territoryservice.js","map.js",function() {
	 	console.log("hier ook dan?")
    var s = new MapService(); 
    var u = new UserService();
    var ms = new MilitaryService();
    var ts = new TerritoryService();
    $scope.zoomIn = function(event) {
        map.zoomIn();
    }
    $scope.zoomOut = function(event) {
        map.zoomOut();
    }
    
    $scope.scrollup = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY()-1;
		   var scrollValueX =  map.getMapData().getViewportX();
		   $scope._scroll(scrollValueX,scrollValueY,9,9)
    }
     $scope.scrolldown = function (event) {
		   var scrollValueY =  map.getMapData().getViewportY()+1;
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
    $scope.mil_pmb = function(event) {
   		var newMilitaryBase = {}   		
			// TODO: database id's moeten niet geexposed worden.. zelfde geldt voor Claims   		
   		newMilitaryBase.name = "Temp Name for MilBase";
   		console.log(JSON.stringify(newMilitaryBase));

   		// Hier moet ik dus mijn tile informatie vandaan halen (location)
   		clickedTile = map.getMapData().getClickedTile();
			console.log(clickedTile)			
			newMilitaryBase.tile_id = clickedTile.tile.tiles[0].tile.id;
			//newMilitaryBase
   		ms.createNewBase(newMilitaryBase,function(json_data) {
				
				if(json_data.status == "ALREADYINUSE"){
					alert("sorry man die shit is al in gebruik")
				}
				else {
					
					map.drawItem(json_data)
				}
				   		
   		}
   		);
   	//	ms.doPostRequest(newMilitaryBase);
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

    }
    $scope.availableactions  = function(event)   {
    	console.log("bladiebloe")
    }
    $scope._scroll = function(x,y,width,height) {
    	
 		  map.getMapData().setviewportPosition(x,y);
 		  map.move(); 
    }    
      	u.getUserById(1,function(modelData) {
   		
   		$scope.$apply(function () {
   		$scope.userData = modelData;
   		})	
   	}); 
   	javascript_console = console;
	   var map = new Map(console,"nationbuilderApp");
	   map.init()
	});
	   
}); 
