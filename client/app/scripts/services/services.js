'use strict'

/********* 
	la factory des recherches de videos 
*********/

routeAppControllers.factory('servicesSearch',['$http' ,function ($http) {
	return{
		search:function(){
		  return $http.get('json/jsonListDeFilm.php');
		},
		searchFilm:function(chaine){  
			//return	$http.get('http://localhost:8080/e-movies/rest/vidéos/exact/movie/'+chaine);
			return	$http.get('json/jsonListDeFilm.php');     
		},		
		searchSerie:function(chaine){      
			//return	$http.get('http://localhost:8080/e-movies/rest/vidéos/exact/tv/'+chaine);
			return	$http.get('json/jsonListDeFilm.php'); 			  
		},
		searchMulti:function(){      
			//return	$http.get('http://localhost:8080/e-movies/rest/vidéos/exact/multi/'+mot);
			return	$http.get('json/jsonListDeFilm.php');       
		}
  }
}]);

//SERVICE POUR LA CRÉATION D'UN OBJECT User JSON

routeAppControllers.factory('userCreationJSON',['$http',function($http){
  return{
  ObjectJsonUser : function(lastname,firstname,address,codePostal,city,country,phone,email,passwd){
       var user={"LastName": lastname, "Firstname":firstname, "Address":address, "ZipCode":codePostal, "City":city, "Country":country, "Phone":phone, "Email":email ,"Password":passwd};
    return user;
     
  }
}}]);

routeAppControllers.factory('userCreationService',['$http',function($http){
  return{
  create:function(lastname,firstname,address,codePostal,city,country,phone,email,passwd){
/*       var user={"LastName": lastname, "Firstname":firstname, "Address":address, "ZipCode":codePostal, "City":city, "Country":country, , "Email":email ,"Password":passwd};
*/           // use $.param jQuery function to serialize data from JSON 
            var data = $.param({
               LastName: lastname,
              FirstName: firstname,
               Address:address,
                ZipCode:codePostal,
                City:city,
                Country:country,
                Phone:phone,
                Email:email,
                Passeword:passwd
            });
            var config = {
                headers:{
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }
           var url="";
        return  "lolol";/*$http.post(url,data,config);*/
   }
  
   }
  
  }
]);

routeAppControllers.factory('userAuthService',['$http',function($http){
  return{
 authenfication: function(pseudo,passwd){
    var data = $.param({
               Pseudo: pseudo,
                Passeword:passwd
            });
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }
       var url="";
    return $http.post(url,data,config);
  }
}}]);

/*routeAppControllers.factory('PanierService',['$http',function($http){
  return{
    envoiPanier:function(monTableau){
      var idProduit=0;
      var category="";
      var support="";
      var quantity=0;
      var price=0;
      var lineOrder={};
      var finaljson=[];
      for(var ligne in monTableau){
           idProduit=monTableau[ligne][0];
            category=monTableau[ligne][1];
            support=monTableau[ligne][2];
            quantity=monTableau[ligne][3];
            price=monTableau[ligne][4];
            lineOrder="{'id_produit':'"+idProduit+"', 'category':'"+category+"','support':'"+support+"','quantity':"+quantity+"'}";
      }
      return lineOrder;
    }
    
     var data = $.param({
               LastName: lastName,
              FirstName: firstName,
               Address:address,
                ZipCode:codePostal,
                City:city,
                Country:country,
                Email:email,
                Passeword:passwd
                
            });
        
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }
       var url="";
    return $http.post(url,data,config);
    
    
    
  }
  
}])*/



