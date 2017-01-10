'use strict';

App.factory('CityService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllCities: function() {
					return $http.get('http://127.0.0.1:8080/city/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching cities');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createCity: function(city){
					return $http.post('http://127.0.0.1:8080/city/', city)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating city');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateCity: function(city, cityId){
     	    	    console.log("XXX", city);
					return $http.post('http://127.0.0.1:8080/cityUpdate/', city)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating city');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteCity: function(cityId){
					return $http.get('http://127.0.0.1:8080/cityDelete/'+cityId)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting city');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);

