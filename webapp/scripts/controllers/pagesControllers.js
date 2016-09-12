

// Contrôleur de la page d'accueil
routeAppControllers.controller('homeCtrl', ['$scope', '$location',
    function($scope, $location){
        $scope.message = "Bienvenue sur la page d'accueil";
		$scope.searchAction = function(){
            $location.path("/search/"+ $scope.query);
        }
    }
]);

// Contrôleur de la page de search
routeAppControllers.controller('searchCtrl', ['$scope', '$location', '$routeParams', '$http',
    function($scope, $location, $routeParams, $http){
		$scope.query = $routeParams.query;		
		
		$scope.datas = [];
		$http.get('json/jsonListDeFilm.php').success(function(data){
			$scope.datas = data;
		});
		
		$scope.detailAction = function(){
            $location.path("/detail/rrr");//+ $scope.id);
        }
    }
]);

// Contrôleur de la page detail
routeAppControllers.controller('detailCtrl', ['$scope', '$location', '$routeParams', '$http',
    function($scope, $location, $routeParams, $http){
		//$scope.id = $routeParams.id;	
		$scope.details = [];
		$http.get('json/jsonUnSeulFilm.php').success(function(data){
			//alert(data.movies[0].title);
			$scope.details = data[0];
		});		
    }
]);