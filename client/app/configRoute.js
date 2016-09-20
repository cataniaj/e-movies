
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
var dataPanierTotal=[0];
var dansPanier = [false];


var dataAchat=[];
var dataAvis=[];

var ListMenu=false;
var GenreFilm = false;
var GenreSerie = false;
var BtnSearch = true;
var isConnect = [false];

routeAppControllers.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
            });
}]);


// routeApp.run($rootScope, $location, $cookieStore, $http) {
//         // keep user logged in after page refresh
//         $rootScope.globals = $cookieStore.get('globals') || {};
//         if ($rootScope.globals.currentUser) {
//             $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
//         }

//         $rootScope.$on('$locationChangeStart', function (event, next, current) {
//             // redirect to login page if not logged in and trying to access a restricted page
//             var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
//             var loggedIn = $rootScope.globals.currentUser;
//             if (restrictedPage && !loggedIn) {
//                 $location.path('/login');
//             }
//         });
//     }