function GridLayer(parentMap, loginstance) {
    this.parentMap = parentMap
    this.loginstance = loginstance;
    this._lines = [];
    this._layer = new Kinetic.Layer();
    this._eventBus = EventBus.instance;
    this._eventBus.registerClass(this)
    // this._tileValueGrid = []

    this.draw = function (width, height) {

        this._createGrid(width, height)
    }

    this.getLayer = function () {
        return this._layer;
    }

    // EventBus interface
    this.getSubscribedEvents = function () {
        return [Event.MAP_SIZE_CHANGE];
    }
    this.notify = function (tevent) {

        switch (tevent.getEventId) {

            case Event.MAP_SIZE_CHANGE:

                this._tileValueGrid = this._createArray(tevent.Getpayload.getX() + 1, tevent.getPayload().getY() + 1);
                break;
        }
        return true;
    }
    this.enableGrid = function () {
        for (i = 0; i < this._lines.length; i++) {

            this._lines[i].show()
        }
        this._layer.draw();
    }
    this.clearLayer = function () {

        this._lines = []
        this._layer.removeChildren()
        this._layer.draw()

    }
    this.disableGrid = function () {

        for (i = 0; i < this._lines.length; i++) {

            this._lines[i].hide()
        }
        this._layer.draw()

    }
    this.move = function () {
		// interface  method, do nothing    
    }
    this._createGrid = function (mapWidth, mapHeight) {
        var currentx = 0;
        var currenty = 0;

        this.clearLayer()

        for (var x = 1; x < mapWidth + 1; x++) {
            currenty = 0;
            for (var y = 1; y < mapHeight + 1; y++) {

                points = [currentx + this.parentMap.getXOffset(), currenty + this.parentMap.getYOffset()
                    , currentx + this.parentMap.getXOffset(), currenty + this.parentMap.getRelativeTilesize() + this.parentMap.getYOffset()];
                var blackLine = new Kinetic.Line({
                    points: points,
                    stroke: 'black',
                    strokeWidth: 1,
                    lineCap: 'round',
                    lineJoin: 'round'
                });

                this._layer.add(blackLine);
                this._lines.push(blackLine)

                // message for TileLayer.. package it in a QuadTuple
                var tilevalues = []
                tilevalues.push(new TripleTuple(x, y, currentx + this.parentMap.getXOffset()))
                tilevalues.push(new TripleTuple(x, y, currenty + this.parentMap.getYOffset()))
                
                var newEvent = new Event(Event.INIT_GRID, Reflection.className(this), Reflection.classType(SelectLayer), tilevalues);
                this._eventBus.notifyListeners(newEvent, true)
         

                currenty = y * this.parentMap.getRelativeTilesize();
            }

            currentx = x * this.parentMap.getRelativeTilesize();
        }

        var currentx = 0;
        var currenty = 0;
        for (var y = 0; y < mapHeight + 1; y++) {
            for (var x = 0; x < mapWidth + 1; x++) {
                points = [currentx + this.parentMap.getXOffset(), currenty + this.parentMap.getYOffset(),
                    currentx + this.parentMap.getRelativeTilesize() + this.parentMap.getXOffset(), currenty + this.parentMap.getYOffset()];
                var blackLine = new Kinetic.Line({
                    points: points,
                    stroke: 'black',
                    strokeWidth: 1,
                    lineCap: 'round',
                    lineJoin: 'round'
                });
                this._layer.add(blackLine);
                this._lines.push(blackLine)
                currentx = x * this.parentMap.getRelativeTilesize();
            }
            currenty = y * this.parentMap.getRelativeTilesize();
        }
    }
    // NOTE: misschien in de toekomst dit ding verplaatsen naar een util class of iets dergelijks

}