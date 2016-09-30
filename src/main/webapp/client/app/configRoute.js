
//configRoute.js
'use strict';

/**
 * Déclaration de l'application routeApp
 */
var routeApp = angular.module('routeApp', [
    // Dépendances du "module"
    'ngRoute',
    'routeAppControllers',
    'ngCookies'
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
var routeAppControllers = angular.module('routeAppControllers', ['ngDialog', 'ngCookies']);
var dataPanier=[];
var dataPanierTotal=[0];
var dansPanier = [false];
var totalPanier = [0];

//var usert = [false];

var dataAchat=[];
var dataAvis=[];

var ListMenu=false;
var GenreFilm = false;
var GenreSerie = false;
var BtnSearch = true;

routeAppControllers.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
            });
}]);


routeAppControllers.run(['$rootScope', '$location', '$cookieStore', '$http', function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
		$rootScope.userIn={'firstName':'','lastName':'','mail':'','zipCode':'','country':'','city':'','address':'','phone':'','datasPanier':'0', 'totalPrice':'0'};
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
		
		//$rootScope.userIn={};
		
        /*$rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['#/home']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/home');
            }
        });*/
    }]);
