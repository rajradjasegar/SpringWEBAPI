'use strict';

App.controller('CategoryController', ['$scope', 'CategoryService', function($scope, CategoryService) {
          var self = this;
          self.category={categoryId:null,categoryName:''};
          self.categories=[];
              
          self.fetchAllCategorys = function(){
              CategoryService.fetchAllCategorys()
                  .then(
      					       function(d) {
      						        self.categories = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.createCategory = function(category){
              CategoryService.createCategory(category)
		              .then(
                      self.fetchAllCategorys, 
				              function(errResponse){
					               console.error('Error while creating Category.');
				              }	
                  );
          };

         self.updateCategory = function(category){
              CategoryService.updateCategory(category)
              .then(
                      self.fetchAllCategorys, 
				              function(errResponse){
					               console.error('Error while updating Category.');
				              }	
                  );
          };

         self.deleteCategory = function(categoryId){
              CategoryService.deleteCategory(categoryId)
		              .then(
				              self.fetchAllCategorys, 
				              function(errResponse){
					               console.error('Error while deleting Category.');
				              }	
                  );
          };

          self.fetchAllCategorys();

          self.submit = function() {
              if(self.category.categoryId==null){
                  console.log('Saving New Category', self.category);    
                  self.createCategory(self.category);
              }else{
                  console.log('Category updating with id ', self.category.categoryId);
                  console.log('Category: ', self.category);
                  self.updateCategory(self.category);
              }
              self.reset();
          };
              
          self.edit = function(categoryId){
              console.log('id to be edited', categoryId);
              for(var i = 0; i < self.categories.length; i++){
                  if(self.categories[i].categoryId == categoryId) {
                     self.category = angular.copy(self.categories[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(categoryId){
              console.log('id to be deleted', categoryId);
              for(var i = 0; i < self.categories.length; i++){
                  if(self.categories[i].categoryId == categoryId) {
                     self.reset();
                     break;
                  }
              }
              self.deleteCategory(categoryId);
          };

          
          self.reset = function(){
              self.category={categoryId:null,categoryName:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);

