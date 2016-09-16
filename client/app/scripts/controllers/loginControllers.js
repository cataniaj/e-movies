
routeAppControllers.controller('loginCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout){	
        /** tester dialogue ***/

        $scope.dialogLogin = function () {
            ngDialog.open({ 
                template: 'dialogLogin'
            });
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

		$scope.connect=function(){
            isConnect = !isConnect;   
            $scope.isConnect1=isConnect;    
            alert($scope.isConnect1) ;            
        };

        // alert($scope.isConnect);
    }
]);   

routeAppControllers.controller('panierCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout){	
        /** tester dialogue ***/
        $scope.dataPanier1=dataPanier;
        
        $scope.dataPanierTotal1=dataPanierTotal;

        $scope.dataAchat1=dataAchat;
        $scope.dataAvis1=dataAvis;


        $scope.addAchats = function (){
            dataAchat.push($scope.dataPanier1);
            dataPanier=[];



            // alert(dataPanier);
        };


        // fonction ajout avis
        $scope.addAvis = function (id,titre,annee) {
              dataAvis.push(new Array(titre));
        };
        //alert($scope.dataPanier1[3]*$scope.dataPanier1[4]);
        /*for(i=0;i<$scope.dataPanier1.length;i++){
            // $scope.dataPanierTotal = $scope.dataPanierTotal + i;  
alert('yess');            
        }*/
            
        //$scope.dataPanier1="boyret";
        

    }
]);    
    
    


