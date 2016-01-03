
// we maken hier  even een global instance van.. zodat we de event bus ten allen tijde overal aan kunnen roepen
function EventBus() {

this._registeredClasses = []
this._queuedEvents = []

this.registerClass = function(classInstance) {

this._registeredClasses.push(classInstance)
var localqueue = this._queuedEvents;
// check the queue for delayed messages and see if we can deliver them to the newly registered class
for(var i=0;i<localqueue.length;i++) {

	var eventHandled = this.notifyListeners(localqueue[i],false);
	if(eventHandled) {
		this._queuedEvents = this._queuedEvents.splice(i,1)
	}
}

}

this.notifyListeners = function (tevent,allowDelayedNotify) {



var eventHandled = false;
var newEvent = tevent;

	for(var i=0;i<this._registeredClasses.length;i++) {
		// BROADCAST betekent gewoon naar alles wat geregisteerd is sturen ongeacht het type
		if(newEvent.getDestinationClass() == Event.BROADCAST) {	
		eventHandled =	 this._registeredClasses[i].notify(newEvent)
		}
		else {
			for(var a=0;a<this._registeredClasses[i].getSubscribedEvents().length;a++) {
				if(this._registeredClasses[i].getSubscribedEvents()[a] == tevent.getEventId()) {
				eventHandled =	this._registeredClasses[i].notify(newEvent)
				}		
			}		
		}
	}

// No handlers have been found.. 	
if(allowDelayedNotify && !eventHandled) {
this._queuedEvents.push(newEvent)
}

return eventHandled;	
	
}
}


function Event(event_id,sourceClass,destClass,payload) { 

this._destClass = destClass;
this._event_id = event_id;
this._payload = payload;


this.getDestinationClass = function() {

return this._destClass;

}
this.getEventId = function() {

return this._event_id;
}
this.getPayload = function () {
	return this._payload;
}
}
Event.BROADCAST = "BROADCAST"
Event.INIT_GRID = "INIT_GRID"
Event.MAP_SIZE_CHANGE = "MAP_SIZE_CHANGE";

EventBus.instance = new EventBus();