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
			return	$http.get('http://localhost:8080/e-movies/rest/videos/search/all/movie/'+chaine);
			//return	$http.get('client/app/json/jsonListDeFilm.php');     
		},		
		searchSerie:function(chaine){      
			return	$http.get('http://localhost:8080/e-movies/rest/videos/search/all/tv/'+chaine);
			//return	$http.get('json/jsonListDeFilm.php'); 			  
		},
		searchMulti:function(){      
			return	$http.get('http://localhost:8080/e-movies/rest/videos/search/all/multi/'+mot);
			//return	$http.get('json/jsonListDeFilm.php');       
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
      /*
       * creation d'un object javascript
       */
      var dataObj='{"LastName":lastname, "Firstname":firstname, "Address":address, "ZipCode":codePostal, "City":city, "Country":country, "Email":email ,"Password":passwd}';
      /*
       * Url à définir
       */
      var url='http://localhost:8080/e-movies/rest/users/createNewAccount';
      /*
       * transformation sous format json 
       */
      var data=eval('('+dataObj+')')
      
      console.log(data);
      /*
       * tester le contenu de data
       */
      /*return  data.LastName;*/
      /*
       * requete post
       */
      return $http.post(url,data);
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



