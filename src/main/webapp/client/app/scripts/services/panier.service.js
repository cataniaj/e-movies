'use strict'
var way = "localhost:8080";

/********* 
	la factory de la gestion du panier 
*********/

routeAppControllers.factory('PanierService',['$http' ,function ($http) {
	return{
		panierManage:function(){				
			var service = {};
			
			service.myPanier = myPanier;
			service.addProduct = addProduct;
			service.deleteProduct = deleteProduct;
			service.addOneQuantity = addOneQuantity;
			service.deleteOneQuantity = deleteOneQuantity;
			service.clearPanier = clearPanier;
			service.stockState = stockState;
			service.payment = payment;
			service.getOrder = getOrder;
			service.httpSendRequestFunction = httpSendRequestFunction;
			service.handleSuccess = handleSuccess;	
			service.handleError = handleError;		
			return service;
		
		
			/** Etat: recuperer les donnees du panier;	Entrée: mail de l'utilisateur;		Sortie: le panier sous Json avec les données;	**/
			function myPanier(dataJson){				 
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/cart/getCart',
					headers: {'Content-Type': "application/json" },
					data: dataJson
				};		
				return	$http(req);
			}
			
			/**	Etat: ajouter un produit du panier;	  
				Entrée: id du produit, mail de l'utilisateur, titre produit, annee produit, support produit, prix produit;		
				Sortie: le panier avec update;	**/
			function addProduct(dataJson){					
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/cart/addToCart',
					headers: {'Content-Type': "application/json" },
					data: dataJson
				};				
				return	$http(req);
			}
			
			/**	Etat: supprimer un produit du panier;	  Entrée: id du produit, mail de l'utilisateur;		Sortie: le panier avec update;	**/
			function deleteProduct(dataJson){
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/cart/removeToCart',
					headers: {'Content-Type': "application/json" },
					data: dataJson
				};		
				return	$http(req);
			}
			
			/**	Etat: incrémenté de 1 la quantité d'un produit du panier;	  Entrée: id du produit, mail de l'utilisateur;		Sortie: le panier avec update;	**/
			function addOneQuantity(dataJson){
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/cart/increment',
					headers: {'Content-Type': "application/json" },
					data: dataJson
				};		
				return	$http(req);
			}
			
			/**	Etat: décrémenter de 1 la quantité d'un produit du panier;	  Entrée: id du produit, mail de l'utilisateur;		Sortie: le panier avec update;	**/
			function deleteOneQuantity(dataJson){
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/cart/decrement',
					headers: {'Content-Type': "application/json" },
					data: dataJson
				};		
				return	$http(req);
			}
			
			/**	Etat: vider le panier;	  Entrée: mail de l'utilisateur;	Sortie: le panier vide;	**/
			function clearPanier(dataJson){
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/cart/removeCart',
					headers: {'Content-Type': "application/json" },
					data: dataJson
				};		
				return	$http(req);
			}
			
			/**	Etat: verifier l'etat du stock (DVD & Bluray) d'une video;		Entrée: id du produit;		Sortie: la valeur du stock;	 **/			
			function stockState(dataJson){  			
				var endUrl="";
				var data={"id":dataJson.id};
				return httpSendRequestFunction(data, endUrl, "Erreur: Stock pas disponible");     
			}
			
			/** Etat: effectuer le paiement;	Entrée: mail de l'ustilisateur;		Sortie: true or false;	**/
			function payment(dataJson){  
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/cart/pay',
					headers: {'Content-Type': "application/json" },
					data: dataJson
				};		
				return	$http(req);
			}
			
			/** Etat: effectuer le paiement;	Entrée: mail de l'ustilisateur;		Sortie: true or false;	**/
			function getOrder(dataJson){  
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/order/getOrder',
					headers: {'Content-Type': "application/json" },
					data: dataJson
				};		
				return	$http(req);
			}
			
			
			/*********
				Functions definies
			*********/
			function httpSendRequestFunction (dataJson, endUrl, error) {
				var req = {
					method: 'POST',
					url: 'http://'+way+'/e-movies/rest/cart/'+endUrl,
					headers: {'Content-Type': "application/json" },
					data: {"mail":dataJson.email}
				};				
				return	$http.get(req).then(handleSuccess, handleError(error)); 
				//return	$http('client/app/json/jsonPanier.php').then(handleSuccess, handleError(error));
			}
		
			
			function handleSuccess (res) {
				//return res.data;
				alert("panier "+res);
				return res;
			}

			function handleError(error) {
				return function () {
					return { success: false, message: error };
				};
			}
		}
	}
}]);


