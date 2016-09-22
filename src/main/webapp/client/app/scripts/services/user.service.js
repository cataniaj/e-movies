
'use strict'

routeAppControllers.factory('UserService', ['$http', '$q', '$filter', '$timeout',
    function ($http, $q, $filter, $timeout) {
		return{
			userManage:function(){				
				var service = {};
				
				service.GetAll = GetAll;
				service.GetByEmail = GetByEmail;
				service.Create = Create;
				service.Update = Update;
				service.Delete = Delete;			
				return service;

				function GetAll() {
					return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
				}

				function GetByEmail(email) {
					//return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
					return $http.get('client/app/json/jsonEmail.php').then(handleSuccess, handleError('Error getting user by email'));
				}

				function Create(user) {
					return $http.post('/api/users', user).then(handleSuccess, handleError('Erreur: email est deja pris'));
				}

				function Update(user) {
					return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
				}

				function Delete(email) {
					return $http.delete('/api/users/' + email).then(handleSuccess, handleError('Error deleting user'));
				}

				// private functions

				function handleSuccess(res) {
					return res.data;
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

