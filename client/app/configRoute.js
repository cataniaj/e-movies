
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
            templateUrl: 'views/home.html',
            controller: 'homeCtrl'
        })
        .when('/search/:query', {
            templateUrl: 'views/search.html',
            controller: 'searchCtrl'
        })
		.when('/detail/:id', {
            templateUrl: 'views/detail.html',
            controller: 'detailCtrl'
        })
	
		.when('/achat', {
            templateUrl: 'views/achat.html',
            controller: 'achatCtrl'
        })
        

		.otherwise({
            redirectTo: '/home'
        });
	
    }
]);


/**
 * Définition des contrôleurs
 */
var routeAppControllers = angular.module('routeAppControllers', ['ngDialog']);
var dataPanier=[];
var dataPanierTotal=0;
var ListMenu=false;
var GenreFilm = false;
var GenreSerie = false;
var BtnSearch = true;
var isConnect = true;

routeAppControllers.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
            });
}]);
