
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
<<<<<<< HEAD
var dataPanierTotal=[0];
// dataPanierTotal.push(0);
=======
var dataPanierTotal=0;
var ListMenu=false;
var GenreFilm = false;
var GenreSerie = false;
var BtnSearch = true;
var isConnect = true;
>>>>>>> 25d379f21566e55903801a287bfc0b59e7d892af

routeAppControllers.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
            });
}]);
