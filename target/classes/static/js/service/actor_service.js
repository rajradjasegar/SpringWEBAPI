'use strict';

App.factory('ActorService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllActors: function() {
					return $http.get('http://127.0.0.1:8080/actor/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching actors');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createActor: function(actor){
					return $http.post('http://127.0.0.1:8080/actor/', actor)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating actor');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateActor: function(actor, actorId){
     	    	    console.log("XXX", actor);
					return $http.post('http://127.0.0.1:8080/actorUpdate/', actor)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating actor');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteActor: function(actorId){
					return $http.get('http://127.0.0.1:8080/actorDelete/'+actorId)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting actor');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);

