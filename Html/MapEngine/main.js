require.config({
	paths: {
		// libraries
		'angular': 'lib/angular13.min',
		//'jquery': 'lib/jquery-1.11.0.js',
		//'kinetic': 'lib/kinetic-v5.1.0.min',
		'domReady': 'lib/domready-v2.0.1',
		'app' : 'js/app'

		
		// services
				
	},
	shim: {
        'angular': {
            exports: 'angular'
        }
    },
	deps: ['../js/bootstrap.js']

});