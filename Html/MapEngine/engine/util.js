function SectionLocation(X, Y) {
    this.X = X;
    this.Y = Y;

    this.getX = function () {
        return this.X;
    }
    this.getY = function () {
        return this.Y;
    }
}

function XYTuple(x, y) {
    this._x = x;
    this._y = y;

    this.getX = function () {
        return this._x;
    }
    this.getY = function () {
        return this._y;
    }
}
function TripleTuple(x, y, a) {
    this._x = x;
    this._y = y;
    this._a = a;

    this.getX = function () {
        return this._x;
    }
    this.getY = function () {
        return this._y;
    }
    this.getA = function () {
        return this._a;
    }
}
function QuadTuple(x1, y1, x2, y2) {
    this._x1 = x1;
    this._x2 = x2;
    this._y1 = y1;
    this._y2 = y2;

    this.getX1 = function () {
        return this._x1;
    }
    this.getX2 = function () {
        return this._x2;
    }
    this.getY1 = function () {
        return this._y1;
    }
    this.getY2 = function () {
        return this._y2;
    }
}


Reflection = function () {
}
Util = function () {
}

Reflection.typeof = function typeOf(obj) {
    return {}.toString.call(obj).split(' ')[1].slice(0, -1).toLowerCase();
}
// Used for classes
Reflection.classType = function (object) {
    return object.name;
}
// Used for instances of a class
Reflection.className = function (object) {
    // Get the type of a object instance
    return object.constructor.name
}
// create 2 dimensional array
Util.createArray = function (x, y) {
    var result = new Array(x);
    for (var i = 0; i < y; i++) {
        result[i] = new Array(5);
        for (var j = 0; j < y; j++) {
            result[i][j] = new Array(2);
        }
    }
    return result;
}
