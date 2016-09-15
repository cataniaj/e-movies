
routeAppControllers.controller('loginCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout){	
        /** tester dialogue ***/
        $scope.dialogLogin = function () {
            ngDialog.open({ template: 'dialogLogin' });
        };
        
        
		/*$scope.dialogSign_up = function () {
            ngDialog.open({ template: 'dialogSign_up' });
        };*/
        $rootScope.$on('ngDialog.setPadding', function (event, padding){
            angular.element( document.querySelector('.paddingHeader') ).css('padding-right', padding + 'px');
        });
	$scope.searchAction2 = function(){
            $location.path("/search/"+ $scope.queryIndex);
    	} 
		$scope.ListMenu = false;
		$scope.GenreFilm = false;
		$scope.GenreSerie = false;

		$scope.BtnSearch = true;
		$scope.isConnect = false;
    }
]);   

routeAppControllers.controller('panierCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout){	
        /** tester dialogue ***/
        $scope.dataPanier1=dataPanier;
        
        $scope.dataPanierTotal=dataPanierTotal;
        $scope.clearPanier=function(){
            while(dataPanier.length){                
                dataPanier.shift();
            }
            dataPanierTotal[0]=0;
        }
        $scope.deletePanier=function(index){                       
            dataPanierTotal[0]= dataPanierTotal[0] - (dataPanier[index][3]*dataPanier[index][4]);
            dataPanier.splice(index, 1);  
        }

    }
]);   
    
    


