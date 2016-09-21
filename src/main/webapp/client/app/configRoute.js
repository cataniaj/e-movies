
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
            templateUrl: 'client/app/views/home.html',
            controller: 'homeCtrl'
        })
        .when('/search/:query', {
            templateUrl: 'client/app/views/search.html',
            controller: 'searchCtrl'
        })
		.when('/detail/:id', {
            templateUrl: 'client/app/views/detail.html',
            controller: 'detailCtrl'
        })
	
		.when('/achat', {
            templateUrl: 'client/app/views/achat.html',
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
var dataPanierTotal=[0];
var dansPanier = [false];


var dataAchat=[];
var dataAvis=[];

var ListMenu=false;
var GenreFilm = false;
var GenreSerie = false;
var BtnSearch = true;
var isConnect = false;

routeAppControllers.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
            });
}]);
