
$(document).ready(function(){ /*code here*/ 
console.log("dit werkt")
//jsimport("config/config.js",function() { 

//} )
}) 


var depManager = null;

function jsimport() {
	console.log("js import die meuk maar")
	if(depManager == null) {
		depManager = new DepManager()	
	}	
	depManager.loadDependency(arguments,0)	
}

function DepManager() {

this.depMap = {}
this.batchdone = false
this.loadDependency = function(deps,current) { 
		var currentContext = this;
		//console.log("current dep = " + deps[current])
		console.log("current count = " + current)
		console.log("dep length = " + deps.length)
		console.log(deps[current])
		// -1 want het laatste argument is de functie waar we moeten callbacken
		if(current  == deps.length-1) {
			console.log("this is not calling") 
			deps[deps.length-1]()
			
		}
		else {
	
			if (typeof this.depMap[deps[current]] === 'undefined') {
			var script = document.createElement("script")
    		script.type = "text/javascript";

    		if (script.readyState){  //IE
        		script.onreadystatechange = function(){
            if (script.readyState == "loaded" ||
                    script.readyState == "complete"){
                script.onreadystatechange = null;
               currentContext.loadDependency(deps,current+1)
            }
        };
    	} 
    	else {  //Others
        script.onload = function(){
            currentContext.loadDependency(deps,current+1)
        };
    	}
    script.src = deps[current];
    document.getElementsByTagName("head")[0].appendChild(script);	
    this.depMap[deps[current]] = true    
 }}

}
}




