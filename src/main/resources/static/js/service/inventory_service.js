/**
 * 
 */
App.factory('InventoryService', ['$http', '$q', function($http, $q){
	return {
		getInventories: function(){
			console.log('Using the Inventory Service: getInventories.');
			return $http.get('http://localhost:8080/getInventories/').then(
					function(res){
						console.log('Sucess du service, getInventories ');
						return res.data;
					},
					function(err){
						console.log('Erreur du service, getInventories ');
						return $q.reject(err);
					}
			);
		},
		getInventoriesForStore: function(){
			console.log('Using then Inventory service getInventoriesForStore');
			return $http.get('').then(
					function(res){
						return res.data;
					},
					function(err){
						return $q.reject(err);
					}
			)
		},
		getInventoriesByFilmId: function(filmId){
			return $http.get('http://localhost:8080/getInventoryByIdFilm/' + filmId).then(
					function(res){
						return res.data;
					},
					function(err){
						return $q.reject(err);
					}
			);
		},
		createInventory: function(inventory){
			console.log('Using the Inventory Service: createInventory.');
			return $http.post('http://localhost:8080/createInventory/', inventory).then(
					function(res){
						console.log('Sucess du service, createInventory ');
						return res.data;
					},
					function(err){
						console.log('Erreur du service, createInventory ');
						return $q.reject(err);
					}
			);
		},
		deteleInventoryByFilmId: function(id){
			console.log('Using the Inventory Service: deteleInventoryByFilmId.');
			return $http.get('http://localhost:8080/deleteInventoryByIdFilm/' + id).then(
				function(res){
					console.log('Sucess du service, deteleInventoryByFilmId ');
					return res.data;
				},
				function(err){
					console.log('Erreur du service, deteleInventoryByFilmId ');
					return $q.reject(err);
				}
			)
		},
		deleteInventory: function(inventory){
			console.log('Using the Inventory Service: deleteInventory.');
			return $http.get('http://localhost:8080/deleteInventory/', inventory).then(
					function(res){
						console.log('Sucess du service, creatInventory ');
						return res.data;
					},
					function(err){
						console.log('Erreur du service, creatInventory ');
						return $q.reject(err);
					}
			);
		}
	}
}])