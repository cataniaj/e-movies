
//configRoute.js
'use strict';

/**
 * Déclaration de l'application routeApp
 */
var routeApp = angular.module('routeApp', [
    // Dépendances du "module"
    'ngRoute',
    'routeAppControllers'
]);

/**
 * Configuration du module principal : routeApp
 */
routeApp.config(['$routeProvider',
    function($routeProvider) { 
        
        // Système de routage
        $routeProvider
        .when('/home', {
            templateUrl: 'partials/home.html',
            controller: 'homeCtrl'
        })
        .when('/search/:query', {
            templateUrl: 'partials/search.html',
            controller: 'searchCtrl'
        })
		.when('/detail/:id', {
            templateUrl: 'partials/detail.html',
            controller: 'detailCtrl'
        })
		.otherwise({
            redirectTo: '/home'
        });
    }
]);


/**
 * Définition des contrôleurs
 */
var routeAppControllers = angular.module('routeAppControllers', []);