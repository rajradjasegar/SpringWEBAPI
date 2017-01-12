'use strict';

App.factory('AddressService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllAddress : function() {
					return $http.get('http://127.0.0.1:8080/address/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching addresss');
								return $q.reject(errResponse);
							});
				},

				readAddress : function(address) {
					return $http.get('http://127.0.0.1:8080/address/', address)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while reading address');
								return $q.reject(errResponse);
							});
				},

				createAddress : function(address) {
					return $http
							.post('http://127.0.0.1:8080/address/', address)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating address');
								return $q.reject(errResponse);
							});
				},

				updateAddress : function(address, addressId) {
					console.log("XXX", address);
					return $http.post('http://127.0.0.1:8080/addressUpdate/',
							address).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating address');
						return $q.reject(errResponse);
					});
				},

				deleteAddress : function(addressId) {
					return $http.get(
							'http://127.0.0.1:8080/addressDelete/' + addressId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting address');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);