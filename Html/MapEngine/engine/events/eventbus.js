
// we maken hier  even een global instance van.. zodat we de event bus ten allen tijde overal aan kunnen roepen
function EventBus() {
this._eventBusId = Util.token();

this._registeredClasses = []
this._delayedQueuedEvents = []
this._queuedEvents = new Queue();
this._handleInParent = false;
this.registerClass = function(classInstance) {
//console.log(Reflection.className(classInstance))
this._registeredClasses.push(classInstance)
var localqueue = this._delayedQueuedEvents;
// check the queue for delayed messages and see if we can deliver them to the newly registered class
for(var i=0;i<localqueue.length;i++) {
		console.log("biertje met worst")
		console.log(this._registeredClasses[i])
	var eventHandled = this.notifyListeners(localqueue[i],false);
	if(eventHandled) {
		this._delayedQueuedEvents = this._delayedQueuedEvents.splice(i,1)
	}
}

}

this.notifyListeners = function (tevent,allowDelayedNotify) {

var eventHandled = false;
var newEvent = tevent;
	// if we find something on the queue that means there is a notifyListeners in progress, so we push the event on the queue and handle it after this one
	this._queuedEvents.enqueue(newEvent)
	// indication if we are already inside a notifylisteners loop.. if so we let take the parent call the handling of the event
	if(!this._handleInParent) {
		// this loop ensures that events that have been added to the queue from within an event handler will also be handled
	while(this._queuedEvents.getLength() > 0) {
		this._handleInParent = true
		newEvent = this._queuedEvents.dequeue();
	for(var i=0;i<this._registeredClasses.length;i++) {
		// BROADCAST betekent gewoon naar alles wat geregisteerd is sturen ongeacht het type
		if(newEvent.getDestinationClass() == Event.BROADCAST) {	
		console.log(this._registeredClasses[i])
		eventHandled =	 this._registeredClasses[i].notify(newEvent)
		}
		else {
			for(var a=0;a<this._registeredClasses[i].getSubscribedEvents().length;a++) {
				if(this._registeredClasses[i].getSubscribedEvents()[a] == newEvent.getEventId()) {
				eventHandled =	this._registeredClasses[i].notify(newEvent)
				}		
			}		
		}
	
	} 	

	}
	// When done always reset to false
	this._handleInParent = false

	
	
// No handlers have been found.. 	
if(allowDelayedNotify && !eventHandled) {
this._delayedQueuedEvents.push(newEvent)
}


return eventHandled;	
	
}
else {
	return null
}
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