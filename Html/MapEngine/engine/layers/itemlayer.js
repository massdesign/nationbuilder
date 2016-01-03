function ItemLayer(parentMap, loginstance) {
    this.parentMap = parentMap;
    this.loginstance = loginstance;
    this._host = "localhost:8083"
    this.assetUrl = "http://" + this._host + "/assets/mil_symbol.png";

    this._items = [];
    this._layer = new Kinetic.Layer({clearBeforeDraw: true});
 

    this.renderItem = function (data) {
        var currentContext = this;
        if (data.tiles[0] != null) {

            var newAsset = new Image();
            newAsset.src = this.assetUrl;

            var normalizedPosition = this.parentMap.getMapTranslator().normalizePosition(data.tiles[0].tile.xposition, data.tiles[0].tile.yposition);
				newAsset.onload = function () {
					
					 var xposition = normalizedPosition.getX() * currentContext.parentMap.getRelativeTilesize();
         	    var yposition = normalizedPosition.getY() * currentContext.parentMap.getRelativeTilesize();
            	 var img = new Kinetic.Image({
                x: xposition,
                y: yposition,
                width: currentContext.parentMap.getRelativeTilesize(),
                height: currentContext.parentMap.getRelativeTilesize(),
                image: newAsset,
                draggable: false
            });

            currentContext._layer.add(img);
            
            var item = new Item()
            item.setPosition(xposition, yposition);
            item.setTileImage(img);
            currentContext._items.push(item);
            currentContext.parentMap.getMapData().setItems(currentContext._items);
				currentContext._layer.draw();
				
				}

           
 
        }
       
    }

    this.move = function () {
			console.log("items")
			console.log(this.parentMap.getMapData().getItems())
    
      
      if (typeof this.parentMap.getMapData().getItems() != 'undefined') {
		         this.parentMap.getMapTranslator().move(this._layer, this.parentMap.getMapData().getItems()); 
      }

    }


    this.renderItems = function (data) {
        var currentContext = this;

        for (i = 0; i < data.length; i++) {
            if (data[i].tiles[0] != null) {
                var xposition = data[i].tiles[0].tile.xposition * currentContext.parentMap.getRelativeTilesize();
                var yposition = data[i].tiles[0].tile.yposition * currentContext.parentMap.getRelativeTilesize()
                var newAsset = new Image();
                newAsset.src = this.assetUrl;
                var img = new Kinetic.Image({
                    x: xposition,
                    y: yposition,
                    width: currentContext.parentMap.getRelativeTilesize(),
                    height: currentContext.parentMap.getRelativeTilesize(),
                    image: newAsset,
                    draggable: false
                });
                this._layer.add(img);
                var item = new Item()
                item.setPosition(xposition, yposition);
                item.setTileImage(img);
                this._items.push(item);
                this.parentMap.getMapData().setItems(this._items);
            }
        }
        this._layer.draw();
    }

    this.getLayer = function () {
        return this._layer;
    }
}