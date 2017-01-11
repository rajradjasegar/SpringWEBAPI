'use strict';

App.controller('CityController', ['$scope', 'CityService', function($scope, CityService) {
          var self = this;
          self.city={cityId:null,cityName:'',countryId:'', country:{countryName:'',countryId:''}};
          //self.city={cityId:null,cityName:'',countryId:'', country:''};
          self.cities=[];
              
          self.fetchAllCities = function(){
              CityService.fetchAllCities()
                  .then(
      					       function(d) {
      						        self.cities = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.createCity = function(city){
              CityService.createCity(city)
		              .then(
                      self.fetchAllCities, 
				              function(errResponse){
					               console.error('Error while creating City.');
				              }	
                  );
          };

         self.updateCity = function(city){
              CityService.updateCity(city)
              .then(
                      self.fetchAllCities, 
				              function(errResponse){
					               console.error('Error while updating City.');
				              }	
                  );
          };

         self.deleteCity = function(cityId){
              CityService.deleteCity(cityId)
		              .then(
				              self.fetchAllCities, 
				              function(errResponse){
					               console.error('Error while deleting City.');
				              }	
                  );
          };

          self.fetchAllCities();

          self.submit = function() {
              if(self.city.cityId==null){
                  console.log('Saving New City', self.city);
                  console.log('Saving New City', self.city.countryId);
                  console.log('Saving New City', self.city.country);
                  self.createCity(self.city);
              }else{
                  console.log('City updating with id ', self.city.cityId);
                  console.log('City: ', self.city);
                  self.updateCity(self.city);
              }
              self.reset();
          };
              
          self.edit = function(cityId){
              console.log('id to be edited', cityId);
              for(var i = 0; i < self.cities.length; i++){
                  if(self.cities[i].cityId == cityId) {
                     self.city = angular.copy(self.cities[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(cityId){
              console.log('id to be deleted', cityId);
              for(var i = 0; i < self.cities.length; i++){
                  if(self.cities[i].cityId == cityId) {
                     self.reset();
                     break;
                  }
              }
              self.deleteCity(cityId);
          };

          
          self.reset = function(){
              self.city={cityId:null,cityName:'',countryId:'',countryName:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);

