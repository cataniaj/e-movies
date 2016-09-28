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
      //declaration de la variable représentant l'espace = '+' 
        var plus='+';
      // déclaration de la  variable à chercher ici "espace"
        var myRegEx=new RegExp(" ","gm");
      // newMot reçoit le nouveau mot dans lequel espace est remplacé par '+'
        var newMot=chaine.replace(myRegEx,plus);
  			return $http.get('http://'+way+'/e-movies/rest/videos/search/all/movie/'+newMot);

			//return	$http.get('client/app/json/jsonListDeFilm.php');     
		},
		detailsFilm:function(id){  
			return	$http.get('http://'+way+'/e-movies/rest/videos/search/exact/movie/'+id);
			//return	$http.get('client/app/json/jsonUnSeulFilm2.php');     
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


