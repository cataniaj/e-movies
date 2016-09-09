'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:AddfilmctrlCtrl
 * @description
 * # AddfilmctrlCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('AddfilmCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.title = "";
        	var url = "http://localhost:8080/e-movies";
    $scope.addtitle = function(){
    	    $http.get(url).
		    success(function(data, status) {
		    	    	console.log($scope.title);
		    }).
		    error(function(data, status) {
		    	    	console.log($scope.title);
		    });
    }
  }]);
