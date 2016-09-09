'use strict';

/**
 * @ngdoc overview
 * @name webappApp
 * @description
 * # webappApp
 *
 * Main module of the application.
 */
angular
  .module('webappApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/addfilm', {
        templateUrl: 'app/views/addfilm.html',
        controller: 'AddfilmCtrl',
        controllerAs: 'addfilm'
      })
      .when('/getfilm', {
        templateUrl: 'app/views/getfilm.html',
        controller: 'GetfilmCtrl',
        controllerAs: 'getfilm'
      })
      .when('/viewfilm', {
        templateUrl: 'app/views/viewfilm.html',
        controller: 'ViewfilmCtrl',
        controllerAs: 'viewfilm'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
