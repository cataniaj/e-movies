
'use strict'
var way = "localhost:8080";

routeAppControllers.factory('UserService', ['$http', '$q', '$filter', '$timeout',
    function ($http, $q, $filter, $timeout) {
		return{
			userManage:function(){				
				var service = {};
				
				service.GetAll = GetAll;
				service.GetByUser = GetByUser;
				service.Create = Create;
				service.Update = Update;
				service.Delete = Delete;			
				service.InfoUser = InfoUser;			
				return service;

				function GetAll() {
					return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
				}

				function GetByUser(user) {

					//alert(user.mail+','+user.password);

					var req = {
						 method: 'POST',
						 url: 'http://'+way+'/e-movies/rest/users/login',
						 headers: {
						   'Content-Type': "application/json"                             
                        },
						 data: {"mail":user.mail,
						 		"password":user.password }
					}
					return $http(req).then(handleSuccess, handleError('Erreur: email est deja pris'));
					 //return	$http.get('client/app/json/jsonEmail.php').then(handleSuccess, handleError('Erreur: email est deja pris')); 
				}

				function Create(user) {
					var req = {
						 method: 'POST',
						 url: 'http://'+way+'/e-movies/rest/users/createNewAccount',
						 headers: {
						   'Content-Type': "application/json"
						 },
						 data: {"lastName":user.lastName, 
							"firstName":user.firstName, 
							"address":user.address, 
							"zipcode":user.zipcode, 
							"country":user.country,
							"city":user.city,
							"phone":user.phone, 
							"mail":user.mail, 
							"password":user.password                             
                        }
                    }
					return $http(req).then(handleSuccess, handleError('Erreur: email est deja pris'));
				}

				function InfoUser(user){
					var req = {
						 method: 'POST',
						 url: 'http://'+way+'/e-movies/rest/users/information',
						 headers: {
						   'Content-Type': "application/json"                             
                        },
						 data: {"mail":user.mail}
					}
					return $http(req).then(handleSuccess, handleError('Erreur: email est deja pris'));

				}

				function Update(user) {
					return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
				}

				function Delete(email) {
					return $http.delete('/api/users/' + email).then(handleSuccess, handleError('Error deleting user'));
				}

				// private functions
				function handleSuccess(res) {
					//return res.data;
					return res;
				}

				function handleError(error) {
					return function () {
						return { success: false, message: error };
					};
				}
			}	
		}
	}
]);

