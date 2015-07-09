
// we maken hier  even een global instance van.. zodat we de event bus ten allen tijde overal aan kunnen roepen
function EventBus() {

this._registeredClasses = []

this.registerClass = function(classInstance) {
console.log(Reflection.className(classInstance))
this._registeredClasses.push(classInstance)

}

this.notifyListeners = function (event_id,dest,payload) {

console.log("dest: " + dest)


}
}


function Event(event_id,destClass) { 

this._destClass = destClass;
this._event_id = event_id;


this.getDestinationClass = function() {

return this._destClass;

}
this.getEventId = function() {

return this._event_id;
}
}
Event.BROADCAST = "BROADCAST"
Event.INIT_GRID = "INIT_GRID"

EventBus.instance = new EventBus();