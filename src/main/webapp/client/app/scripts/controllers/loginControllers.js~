
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

    }
]);   

routeAppControllers.controller('panierCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout){	
        /** tester dialogue ***/
        $scope.dataPanier1=dataPanier;
        
        $scope.dataPanierTotal=dataPanierTotal;
        //alert($scope.dataPanier1[3]*$scope.dataPanier1[4]);
        /*for(i=0;i<$scope.dataPanier1.length;i++){
            // $scope.dataPanierTotal = $scope.dataPanierTotal + i;  
alert('yess');            
        }*/
            
        //$scope.dataPanier1="boyret";
        

    }
]);   
    
    


