'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:GetfilmCtrl
 * @description
 * # GetfilmCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('GetfilmCtrl',['$scope', function ($scope) {
  	$scope.title = "";
	 $scope.gettitle = function(){
	     console.log($scope.title);
	  	}
  }]);
