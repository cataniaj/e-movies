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
	$http.get('client/app/json/bestSales.php').success(function(data){ //get meilleures ventes
		$scope.datasBest = data;
		for(i=0;i<data.length;i++){
			if($scope.datasBest[i].poster == "N/A"){
				$scope.datasBest[i].poster = "client/app/images/logos/no-image.jpg";
			}
		}
	});
	//Suggestions :		
	$scope.datasSuggest = [];
	$http.get('client/app/json/suggests.php').success(function(data2){ //get suggestions
		$scope.datasSuggest = data2;
		for(i=0;i<data2.length;i++){
			if($scope.datasSuggest[i].poster == "N/A"){
				$scope.datasSuggest[i].poster = "client/app/images/logos/no-image.jpg";
			}
		}
	});	        
	
	
    }
]);

// Contrôleur de la page de search
routeAppControllers.controller('searchCtrl', ['$scope', '$location', '$routeParams', '$http', 'servicesSearch', 
    function($scope, $location, $routeParams, $http, servicesSearch){
		$scope.query = $routeParams.query;		
		$scope.datas = [];
		/** pre: string; 
			post: list de films ou series */
		//$http.get('json/jsonListDeFilm.php').success(function(data){
        //$http.get('http://'+way+'/e-movies/rest/videos/search/all/movie/'+$scope.query).success(function(data){
        //$http.get('https://api.themoviedb.org/3/search/movie?query=jurassic+park&language=fr&api_key=db1096cd136c906c06e7d77b313df0d4').success(function(data){
		
		
		servicesSearch.searchFilm($scope.query).success(function(data){	
            
			// $scope.datas = data;
			$scope.datas = data.movies;

			for(i=0;i<data.movies.length;i++){
				if($scope.datas[i].poster == "N/A"){
					$scope.datas[i].poster = "client/app/images/logos/no-image.jpg";
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
routeAppControllers.controller('detailCtrl', ['$scope', '$location','$rootScope', '$routeParams', '$http', '$timeout','servicesSearch','PanierService',
    function($scope, $location, $rootScope, $routeParams, $http, $timeout, servicesSearch,PanierService){
		$scope.id = $routeParams.id;	


		$scope.details = [];
                
                servicesSearch.detailsFilm($scope.id).success(function(data){	
            		/*$http.get('client/app/json/jsonUnSeulFilm2.php').success(function(data){
            			//alert(data.movies[0].title);
            			$scope.details = data[0];
            		});*/
                    // $scope.details = data[0];
					$scope.details = data;
                    if($scope.details.trailer == "N/A"){
                        $scope.bd=false;
                    } else{
                        $scope.bd=true;
                    }

                });
        
        /** fonction ajout dans panier  **/

        //$scope.addPanier = function (idC, idD, idP , titre, annee, support, quantite, pu) {
        $scope.addPanier = function (produit, support, quantite) {
            //panierFunction(idC,titre, annee, support, quantite, pu);
            if(support=="DVD"){
                panierFunction(produit.idProductDvd, produit.title, produit.year, support, 1, produit.dvdPrice);
            }else if(support=="BluRay"){
                panierFunction(produit.idProductBR, produit.title, produit.year, support, 1, produit.blurayPrice);
            }else if(support=="Copie Numerique"){
                panierFunction(produit.idProductNumeric, produit.title, produit.year, support, 1, produit.numericPrice);
            }
            
			
            function panierFunction(id, titre, annee, support, quantite, pu){

                var user={"idProduct":id,
                    "mail":$rootScope.globals.currentUser.email,
                    "title":titre,
                    "year":annee,
                    "support":support,
                    "unitPrice":pu};
                PanierService.panierManage().addProduct(user).then(function(response) { 
                    //alert("yess add"+response.data.cart[0].title);
                    $scope.userIn.datasPanier = response.data.cart;
                    //$scope.dansPanier[0]=true;
                });
				$timeout(function() {$scope.addInfo = false;}, 1000);

          //       if(dataPanier.length>0){					// test si panier non vide **

          //               for(i=0; i<dataPanier.length; i++){
          //                       if(dataPanier[i][0]==id){			// test si la video n'est pas deja present dans le panier **
          //                               dataPanier[i][4]=dataPanier[i][4]+1;
          //                               // on actualiste le panier total
          //                               dataPanierTotal[0]=0;
          //                               for(j=0;j<dataPanier.length;j++){
          //                                       dataPanierTotal[0]=dataPanierTotal[0]+(dataPanier[j][4]*dataPanier[j][5]);
          //                               }
										// i=dataPanier.length;
          //                       }
          //                       else if((i==(dataPanier.length-1))&&(dataPanier[i][0]!=id)){
          //                               dataPanier.push(new Array(id, titre, annee, support, quantite, pu));
          //                               dataPanierTotal[0]= (dataPanierTotal[0] + (quantite*pu));
          //                               dansPanier.shift();
          //                               dansPanier.push(true);
										// i=dataPanier.length;
          //                       }
          //               }
          //       }else{ 						// la video n'est pas dans le panier, et panier vide, on l'ajoute donc **		
          //               dataPanier.push(new Array(id, titre, annee, support, quantite, pu));
          //               dataPanierTotal[0]= (dataPanierTotal[0] + (quantite*pu));
          //               dansPanier.shift();
          //               dansPanier.push(true);

          //       }
          //       $timeout(function() {$scope.addInfo = false;}, 1000);
            }
        };
        
        $scope.supportPrice=function(index, prix){
            if(index=="2"){
                $scope.txtDtlPu = prix;
            }else if(index=="3"){
                $scope.txtDtlPu = prix;
            }else{
                $scope.txtDtlPu = prix;
            }                
        }		
		//setTimeout(function() { alert($scope.addInfoId); }, 1000);
	}
]);


// Contrôleur de la page achat
routeAppControllers.controller('achatCtrl', ['$scope','$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout','$interval','PanierService',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout, $interval,PanierService){

        $scope.datasOrders = [];
		
		if(!$rootScope.globals.currentUser){
			$location.path("/home");
		}
                
        if($rootScope.globals.currentUser.email){               
            var user={"mail":$rootScope.globals.currentUser.email}; 
            PanierService.panierManage().getOrder(user).then(function(response){ 
                $scope.datasOrders = response.data.order;
            });
        }                
		
		$scope.toto=0;						
        $scope.download = function () {
            //ngDialog.open({ template: 'dialogDownload' });  
			$interval(function() {$scope.toto= $scope.toto+1;}, 2000, 100);
        };
        
        $rootScope.$on('ngDialog.setPadding', function (event, padding){
            angular.element( document.querySelector('.paddingHeader') ).css('padding-right', padding + 'px');
        });

    }
]);


// Contrôleur de la page achat
routeAppControllers.controller('settingCtrl', ['$scope','$location', '$http', '$rootScope', '$timeout','$interval','UserService',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout, $interval, UserService){
		var logID={'mail':'user1@gmail.com'};
									UserService.userManage().InfoUser(logID).then(function (info) {
										//alert(info.data.mail);
										$rootScope.userIn.firstName = info.data.firstName;
										$rootScope.userIn.lastName = info.data.lastName;
										$rootScope.userIn.address = info.data.address;
										//$rootScope.userIn.zipcode = info.data.zipcode;
										$rootScope.userIn.phone = info.data.phone;
										//$rootScope.userIn.city = info.data.city;
										//$rootScope.userIn.country = info.data.country;
										$rootScope.userIn.mail = info.data.mail;									
										
									});
		
		//$scope.userIn.firstName = $rootScope.userIn.firstName;
		$scope.userIn.lastName = $rootScope.userIn.lastName;
		$scope.userIn.address = $rootScope.userIn.address;
		//$rootScope.userIn.zipcode = info.data.zipcode;
		$scope.userIn.phone = $rootScope.userIn.phone;
		/*$rootScope.userIn.city = info.data.city;
		$rootScope.userIn.country = info.data.country;*/
		$scope.userIn.mail = $rootScope.userIn.mail;
		
		alert($rootScope.userIn.mail);
    }
]);