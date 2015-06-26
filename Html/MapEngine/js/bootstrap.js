define([
    'require',
    'angular',
    'app',
     '../controllers/controllers.js'
], function (require, ng) {

  'use strict';

    require(['domReady!'], function (document) {
    		console.log(document)	
        ng.bootstrap(document, ['app']);
    });
});