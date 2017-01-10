'use strict';

App.factory('CustomerService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllCountries: function() {
					return $http.get('http://127.0.0.1:8080/customer/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching countries');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createCountry: function(customer){
					return $http.post('http://127.0.0.1:8080/customer/', customer)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating customer');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateCountry: function(customer, customerId){
     	    	    console.log("XXX", customer);
					return $http.post('http://127.0.0.1:8080/customerUpdate/', customer)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating customer');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteCountry: function(customerId){
					return $http.get('http://127.0.0.1:8080/customerDelete/'+customerId)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting customer');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);

