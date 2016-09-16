

// Contrôleur de la page d'accueil
routeAppControllers.controller('homeCtrl', ['$scope', '$location','$routeParams','$http',
	function($scope, $location, $routeParams, $http){
        $scope.message = "Bienvenue sur la page d'accueil";
        $scope.rechAvancee = false;
		$scope.searchAction = function(){
            $location.path("/search/"+ $scope.query);
	}

	//Meilleures ventes :		
	$scope.datasBest = [];
	$http.get('json/jsonListDeFilm2.php').success(function(data){ //get meilleures ventes
		$scope.datasBest = data;
		for(i=0;i<data.length;i++){
			if($scope.datasBest[i].poster == "N/A"){
				$scope.datasBest[i].poster = "images/logos/no-image.jpg";
			}
		}
	});
	//Suggestions :		
	$scope.datasSuggest = [];
	$http.get('json/jsonListDeFilm2.php').success(function(data){ //get suggestions
		$scope.datasSuggest = data;
		for(i=0;i<data.length;i++){
			if($scope.datasSuggest[i].poster == "N/A"){
				$scope.datasSuggest[i].poster = "images/logos/no-image.jpg";
			}
		}
	});	        
	
	
    }
]);

// Contrôleur de la page de search
routeAppControllers.controller('searchCtrl', ['$scope', '$location', '$routeParams', '$http',
    function($scope, $location, $routeParams, $http){
		$scope.query = $routeParams.query;		
		$scope.datas = [];
		
		$scope.datas = [];
		/** pre: string; 
			post: list de films ou series */
		$http.get('json/jsonListDeFilm.php').success(function(data){
        //$http.get('http://localhost:8080/e-movies/rest/videos/search/all/movie/'+$scope.query).success(function(data){
        //$http.get('https://api.themoviedb.org/3/search/movie?query=jurassic+park&language=fr&api_key=db1096cd136c906c06e7d77b313df0d4').success(function(data){
			//$scope.datas = data.movies;
			$scope.datas = data;
			for(i=0;i<data.length;i++){
				if($scope.datas[i].poster == "N/A"){
					$scope.datas[i].poster = "images/logos/no-image.jpg";
				}
				if($scope.datas[i].year == "N/A"){
					$scope.datas[i].year = "(date inconnue)";
				}
			}
		});	

		$scope.detailAction = function(){
            $location.path("/detail/rrr");//+ $scope.id);
        }
    }
]);


// Contrôleur de la page detail
routeAppControllers.controller('detailCtrl', ['$scope', '$location', '$routeParams', '$http','$timeout',
    function($scope, $location, $routeParams, $http, $timeout){
		//$scope.id = $routeParams.id;	
		$scope.details = [];
		$http.get('json/jsonUnSeulFilm.php').success(function(data){
			//alert(data.movies[0].title);
			$scope.details = data[0];
		});	
        
        /** fonction ajout dans panier  **/
        $scope.addPanier = function (titre, annee, support, quantite, pu) {
            dataPanier.push(new Array(titre, annee, support, quantite, pu));
            dataPanierTotal[0]= (dataPanierTotal[0] + (quantite*pu));
            
             /*setTimeout(function(){  
                $scope.addInfo=true;
                //document.getElementById("addInfoId").ng-show=false;
                alert("Hello" + $scope.addInfo);}, 3000);*/
           
        };
        
        $scope.supportPrice=function(index){
            if(index=="2"){
                $scope.txtDtlPu=10,00;
            }else if(index=="3"){
                $scope.txtDtlPu=15,00;
            }else{
                $scope.txtDtlPu=7,00;
            }
                
        }
		
		//setTimeout(function() { alert("my message"); }, 1);
	}
]);

// Contrôleur de la page achat
routeAppControllers.controller('achatCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout){	
        
        $scope.download = function () {
            ngDialog.open({ template: 'dialogDownload' });            
        };
        /* tester dialogue ***
        $scope.open = function () {
            ngDialog.open({ template: 'dialogLogin' });
        };*/

        $rootScope.$on('ngDialog.setPadding', function (event, padding){
            angular.element( document.querySelector('.paddingHeader') ).css('padding-right', padding + 'px');
        });

    }
]);


      
       

