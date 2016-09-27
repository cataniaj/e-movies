
routeAppControllers.controller('loginCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout', 'UserService', 'AuthenticationService', 'PanierService',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout, UserService, AuthenticationService, PanierService){	
        //$scope.usert = usert;
		initController();
		
		/** tester si l'utilisateur est connecté ou pas ***/		
		function initController() {
			if($rootScope.globals.currentUser){
				loadCurrentUser();
			}
        }
		
		// charger l'identifiant du user qui s'etait connecté et qui ne s'est pas déconnecté
        function loadCurrentUser() {
			/*alert(AuthenticationService.Authentication().Base64().encode($rootScope.globals.currentUser.authdata));
			alert(AuthenticationService.Authentication().Base64().decode($rootScope.globals.currentUser.authdata));*/
			var userLog={"mail":$rootScope.globals.currentUser.email,"password":$rootScope.globals.currentUser.authdata};
			AuthenticationService.Authentication().Login(userLog, function (response) {
				if (response.success) {
					AuthenticationService.Authentication().SetCredentials(userLog.mail, userLog.password);			
					//$scope.$apply();				
					$scope.usert=true;
					$scope.userEmail = $rootScope.globals.currentUser.email;
					// $scope.user = $rootScope.globals.currentUser;
				} else {
					AuthenticationService.Authentication().ClearCredentials();	
				}
			});
        }

		// deconnexion		
		$scope.deconnexion=function(){
			AuthenticationService.Authentication().ClearCredentials();
			$scope.usert=false;
			//$scope.$apply();
			window.location.reload();
			$location.path("/home");
		}

		// deconnexion
	   $scope.searchAction2 = function(){
            $location.path("/search/"+ $scope.queryIndex);
    	} 
		
		// bouton back de la navigation
		$scope.back = function () {
            window.history.back();			
        };  
    }
]);   


routeAppControllers.controller('panierCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout', 'PanierService',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout, PanierService){	
        /** tester dialogue ***/
        $scope.dataPanier1=dataPanier;
        $scope.dansPanier=dansPanier;
        
		$scope.datasPanier = [];

        $scope.dataAchat1=dataAchat;
        $scope.dataAvis1=dataAvis;
        $scope.dataPanierTotal=dataPanierTotal;
		//alert("yooo");
		if($rootScope.globals.currentUser){
			loadPanier();
		}else{
			$scope.datasPanier=[];
		}
		
		function loadPanier(){			
			if($rootScope.globals.currentUser.email){				
				var user={"mail":$rootScope.globals.currentUser.email};	
				//alert(user.mail);			
				PanierService.panierManage().myPanier(user).then(function(response){ 
					$scope.datasPanier = response.data.cart;
					$scope.dansPanier[0]=true;
				});
			}
		}
        
        $scope.updateQuantite = function (index, qte){                     
            $scope.dataPanier1[index][4]=qte;
			$scope.dataPanierTotal[0]=0;
			for(i=0;i<$scope.dataPanier1.length;i++){
				$scope.dataPanierTotal[0]=$scope.dataPanierTotal[0]+($scope.dataPanier1[i][4]*$scope.dataPanier1[i][5]);
			}
        };
		
        $scope.clearPanier = function(){
        	var user={"mail":$rootScope.globals.currentUser.email};	
        	PanierService.panierManage().clearPanier(user).then(function(response){ 
					$scope.datasPanier = response.data.cart;
					$scope.dansPanier[0]=false;
				});
            // while(dataPanier.length){                
            //     dataPanier.shift();                
            // }
            // dansPanier.shift();
            // dataPanierTotal[0]=0;
            // dansPanier[0]=false;            
        };
		
        $scope.deletePanier = function (idP){   

        	var json={"mail":$rootScope.globals.currentUser.email,"idProduct":idP};	  
        	PanierService.panierManage().deleteProduct(json).then(function(response){ 
        		$scope.datasPanier = response.data.cart;
        	});

            // dataPanierTotal[0]= dataPanierTotal[0] - (dataPanier[index][4]*dataPanier[index][5]);
            // dataPanier.splice(index, 1); 

            // if(dataPanier.length == 0){
            //     dansPanier.shift();
            //     dansPanier[0]=false;
            // }
        };
		
		$scope.payer=function(){
			// PanierService.panierManage().payment(user).then(function(response){ 
					
			// 	});

			//alert("yoooo "+$rootScope.globals.currentUser.email);
			if($rootScope.globals.currentUser){
				//alert("paiement ok");
				$(paiement).modal("show");
			}else{
				$(dialogPanier).modal("hide");
				$(dialogLogin).modal("show");
			}
		};
    }
]);   

 routeAppControllers.controller('loginValidateCtrl', ['$scope', '$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout', 'AuthenticationService', 'FlashService', 'UserService',
	function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout, AuthenticationService, FlashService, UserService){   
	
		$scope.login = function(){
			$scope.vm.dataLoading = true;
			var userLog={"mail":$scope.vm.email,"password":$scope.vm.pwd};
			AuthenticationService.Authentication().Login(userLog, function (response) {				
				if (response.success) {
					AuthenticationService.Authentication().SetCredentials(userLog.mail, userLog.password);			
					$scope.$apply();					
					$scope.vm.dataLoading = false;
					$(dialogLogin).modal("hide");
					$scope.usert=true;
					window.location.reload();
				} else {
					FlashService.Error(response.message);
					$scope.vm.dataLoading = false;
				}
			});
		};	
		
		$scope.signUp = function(){
			var user={"lastName":$scope.user.nom, 
						"firstName":$scope.user.pName, 
						"address":$scope.user.adress, 
						"zipcode":$scope.user.zipCode, 
						"country":"France", 
						"city":"Grenoble",
						"phone":$scope.user.tel, 
						"mail":$scope.user.email, 
						"password":$scope.user.pwd1,
						"notification":$scope.user.notif};
			
			$scope.dataLoadingSign_up = true;
			
			UserService.userManage().Create(user).then(function (response) {
				if (response !== null && response.status=="200") {						
				//if ((response.success!="undefined")||(response.success=="200")||(response.success==true)) {							
				//if (response.success) {							
					FlashService.Success('Registration successful', true);
					 alert("good");
					$location.path('/');
					$(dialogSign_up).modal("hide");
					$scope.login;
					$scope.dataLoadingSign_up = false;
					$scope.usert=true;
				} else {
					alert("not good");
					FlashService.Error(response.message);
					$scope.dataLoadingSign_up = false;
				}
			});
		};						
	}    
 ]);   
    
    


