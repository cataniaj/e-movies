

// Contrôleur de la page de search
routeAppControllers.controller('searchCtrl', ['$scope', '$location', '$routeParams', '$http',
    function($scope, $location, $routeParams, $http){
		$scope.query = $routeParams.query;		
		
		$scope.datas = [];
		$http.get('json/jsonListDeFilm.php').success(function(data){
			//alert(data.length);
			$scope.datas = data;
		});
		
		$scope.detailAction = function(){
            $location.path("/detail/rrr");//+ $scope.id);
        }
    }
]);