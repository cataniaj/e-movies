'use strict'

/********* 
	la factory de la gestion du panier 
*********/

routeAppControllers.factory('PanierService',['$http' ,function ($http) {
	return{
		/** clear panier when user logout **/
		
		
		/** Etat: recuperer les donnees du panier;	Entrée: mail de l'utilisateur;		Sortie: le panier sous Json avec les données;	**/
		myPanier:function(dataJson){
			var endUrl="";
			var data={"mail":dataJson.mail};
			// return httpSendRequestFunction(data, endUrl, "Erreur: Le produit n\'a pas pu etre supprim\é du panier");
			return	$http.get('client/app/json/jsonPanier.php');
		},
		
		/**	Etat: ajouter un produit du panier;	  
			Entrée: id du produit, mail de l'utilisateur, titre produit, annee produit, support produit, prix produit;		
			Sortie: le panier avec update;	**/
		addProduct:function(dataJson){	
			var endUrl="";
			var data={"idProduct":dataJson.idProduct,
				"mail":dataJson.mail,
				"title":dataJson.title,
				"year":dataJson.year,
				"support":dataJson.support,
				"unitPtice":dataJson.price
			};
			return httpSendRequestFunction(data, endUrl, "Erreur: Le produit n\'est plus disponible");
		},
		
		/**	Etat: supprimer un produit du panier;	  Entrée: id du produit, mail de l'utilisateur;		Sortie: le panier avec update;	**/
		deleteProduct:function(){
			var endUrl="";
			var data={"id":dataJson.id, "mail":dataJson.mail};
			return httpSendRequestFunction(data, endUrl, "Erreur: Le produit n\'a pas pu etre supprim\é du panier");
		},
		
		/**	Etat: incrémenté de 1 la quantité d'un produit du panier;	  Entrée: id du produit, mail de l'utilisateur;		Sortie: le panier avec update;	**/
		addOneQuantity:function(){
			var endUrl="";
			var data={"id":dataJson.id, "mail":dataJson.mail};
			return httpSendRequestFunction(data, endUrl, "Erreur: Le produit n\'a pas pu etre increment\é");
		},
		
		/**	Etat: décrémenter de 1 la quantité d'un produit du panier;	  Entrée: id du produit, mail de l'utilisateur;		Sortie: le panier avec update;	**/
		deleteOneQuantity:function(dataJson){
			var endUrl="";
			var data={"id":dataJson.id, "mail":dataJson.mail};
			return httpSendRequestFunction(data, endUrl, "Erreur: Le produit n\'a pas pu etre decrement\é");
		},
		
		/**	Etat: vider le panier;	  Entrée: mail de l'utilisateur;	Sortie: le panier vide;	**/
		clearPanier:function(dataJson){
			var endUrl="";
			var data={"mail":dataJson.mail};
			return httpSendRequestFunction(data, endUrl, "Erreur: Le panier n\'a pas pu etre vid\é");
		},	
		
		/**	Etat: verifier l'etat du stock (DVD & Bluray) d'une video;		Entrée: id du produit;		Sortie: la valeur du stock;	 **/			
		stockState:function(dataJson){  			
			var endUrl="";
			var data={"id":dataJson.id};
			return httpSendRequestFunction(data, endUrl, "Erreur: Stock pas disponible");     
		},
		
		/** Etat: effectuer le paiement;	Entrée: mail de l'ustilisateur;		Sortie: true or false;	**/
		payment:function(dataJson){  
			var endUrl="";
			var data={"mail":dataJson.mail};
			return httpSendRequestFunction(data, endUrl, "Erreur: le paiement n\'a pas pu se faire");  
		},
		
		/** Etat: effectuer le paiement;	Entrée: mail de l'ustilisateur;		Sortie: true or false;	**/
		getOrder:function(dataJson){  
			var endUrl="";
			var data={"mail":dataJson.mail};
			// return httpSendRequestFunction(data, endUrl, "Erreur: le paiement n\'a pas pu se faire"); 
			return	$http.get('client/app/json/jsonAchat.php');
		},
		
		
		/*********
			Functions definies
		*********/
		httpSendRequestFunction:function (endUrl, dataJson, error) {
			var req = {
				method: 'POST',
				url: 'http://localhost:8080/e-movies/rest/'+endUrl,
				headers: {'Content-Type': "application/json" },
				data: dataJson
			};
			return	$http.get(req).then(handleSuccess, handleError(error)); 
		},
		
		handleSuccess:function (res) {
			//return res.data;
			return res;
		},

		handleError:function (error) {
			return function () {
				return { success: false, message: error };
			};
		}
	}
}]);


