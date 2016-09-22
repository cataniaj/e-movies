'use strict'
var way = "localhost:8080";
/********* 
	la factory des recherches de videos 
*********/

routeAppControllers.factory('servicesSearch',['$http' ,function ($http) {
	return{
		search:function(){
		  return $http.get('json/jsonListDeFilm.php');
		},
		searchFilm:function(chaine){  
			return	$http.get('http://'+way+'/e-movies/rest/videos/search/all/movie/'+chaine);
			//return	$http.get('client/app/json/jsonListDeFilm.php');     
		},		
		searchSerie:function(chaine){      
			return	$http.get('http://'+way+'/e-movies/rest/videos/search/all/tv/'+chaine);
			//return	$http.get('json/jsonListDeFilm.php'); 			  
		},
		searchMulti:function(){      
			return	$http.get('http://'+way+'/e-movies/rest/videos/search/all/multi/'+mot);
			//return	$http.get('json/jsonListDeFilm.php');       
		}
  }
}]);

/*********
//	SERVICE POUR LA CRÉATION D'UN OBJECT User JSON
********/
routeAppControllers.factory('userCreationJSON',['$http',function($http){
  return{
  ObjectJsonUser : function(lastname,firstname,address,codePostal,city,country,phone,email,passwd){
       var user={"LastName": lastname, "Firstname":firstname, "Address":address, "ZipCode":codePostal, "City":city, "Country":country, "Phone":phone, "Email":email ,"Password":passwd};
    return user;
     
  }
}}]);

/*
 * Service de cration d'envoie d'un Object Nouveau user à la base de donnée
 * avec url à définir ,
 * et l'objet data en json comme donnée à envoyé
 */
routeAppControllers.factory('userCreationService',['$http',function($http){
	return{
		create:function(lastname,firstname,address,codePostal,city,country,phone,email,passwd){
			// creation d'un object javascript
			var dataObj='{"LastName":lastname, "Firstname":firstname, "Address":address, "ZipCode":codePostal, "City":city, "Country":country, "Email":email ,"Password":passwd}';
			// Url à définir
			var url="http://"+way+"/e-movies/rest/users/CreateNewAccount";
			// transformation sous format json 
			var data=eval('('+dataObj+')');			
			//console.log(data);
			// tester le contenu de data
			//return  data.LastName;
			// requete post
			return $http.post(url,data);
		}
	}
}]);


/*********
//	SERVICE AUTHENTIFICATION
********/
routeAppControllers.factory('userAuthService',['$http',function($http){
	return{
		authenfication: function(pseudo,passwd){
			var dataObj ='{"Pseudo":pseudo,"Password":passwd}';
			var url="";
			var data=eval('('+dataObj+')');
			//console.log(data)
			return $http.post(url,data);
		}
	}
}]);


/*
 * Service de création de ligne de commande 
 */
routeAppControllers.factory('lineOrderCreation',['$http',function($http){
	return{
		lineOrder:function(IdProduct,titre,support,quantity){
			var dataObj ='{"IdProduct":IdProduct,"titre":titre,"Support":support,"Quantity":quantity}';
			return dataObj;
		},
	
		order:function(MonTab){
			var dataObj=[];
			for(var i=0;i<MonTab.length;i++){
				dataObj.push(this.lineOrder(MonTab[i][0],MonTab[i][1],MonTab[i][2],MonTab[i][3]));
			}
			var dataObjJson='dataObj';
			var data=eval('('+dataObjJson+')');
			//console.log(data);			
		}		
	}
}]);


