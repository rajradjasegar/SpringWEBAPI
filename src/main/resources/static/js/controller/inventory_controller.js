/**
 * 
 */

App.controller('InventoryController', ['$scope', '$cookieStore', 'InventoryService', 'FilmService', 
                                       function($scope, $cookieStore, InventoryService, FilmService) {
	var self = this;
	
	self.film = {
		filmId:null,
		title:'',
		description:'',
		releaseYear:'',
		rentalDuration:'',
		rentalRate:'',
		lenght:'',
		language:null,
		features:''
	};
	self.inventory = {
		inventory_id: '',
		film_id: '',
		storeId: '',
		last_update:''
	};
	self.inventories = [];
	self.films=[];
	
	self.setQuantity = function(){
		self.films.forEach(function(film){
			InventoryService.getInventoriesByFilmId(film['filmId']).then(
				function(count){
					film['quantity'] = count;
				},
				function(err){}
			)
		});
	};
	
	
	self.fetchAllFilms = function(){
		FilmService.fetchAllFilms().then(
			function(res){
				self.films = res;
				self.setQuantity();
			},
			function(err){
				console.log("Error: controller failed to get the films");
			}
		);
	};
	
	self.fetchAllInventories = function(){
		InventoryService.getInventoriesForStore($cookieStore.get('store_id')).then(
			function(res){
				self.inventories = res;
			},
			function(err){
				console.log("Error: controller failed to get the inventories");
			}
		);
	}
	
	self.createInventory = function(inventory){
		inventory['quantity']++;
		var map = {filmId: inventory['filmId'], storeId: $cookieStore.get('store_id')};
		InventoryService.createInventory(map).then(
				self.setQuantity,
				function(err){
					console.log("Error: controlle failed to greate a inventory");
				}
		)
	};
	
	self.updateInventory = function(inventory){
		InventoryService.updateInventory(inventory).then(
			self.fetchAllInventories,
			function(err){
				console.log("Error: controller failed to update a inventory");
			}
		)
	};
	
	self.deleteInventory = function(inventory){
		inventory['quantity']--;
		InventoryService.getInventoriesByFilmId(inventory['filmId']).then(
				function(res){console.log(res)},
				function(){console.log('____ An error occures____')}
		)
		InventoryService.deteleInventoryByFilmId(inventory['filmId']).then(
			self.fetchAllInventories,
			function(err){
				console.log("Error: controller failed to deleted a inventory");
			}
		)
	};
	
	self.fetchAllFilms();
	self.fetchAllInventories();
	
}]);