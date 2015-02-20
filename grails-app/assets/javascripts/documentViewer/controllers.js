(function(){

	var controllers = angular.module('controllers', ['ui.bootstrap']);

	controllers.controller('ListController', ['$scope','$http', '$location', function($scope, $http, $location){
		$scope.documents = [];
		
		
		$http.get('documents').success(function(data){
			$scope.documents = data;
			$scope.totalItems = $scope.documents.length;
		});
		
		$scope.currentPage = 1;
		$scope.numPerPage = 10;
		
		$scope.paginate = function(value) {
			var begin, end, index;
			begin = ($scope.currentPage - 1) * $scope.numPerPage;
			end = begin + $scope.numPerPage;
			index = $scope.documents.indexOf(value);
			return (begin <= index && index < end);
		};
		
		
		$scope.createDocument = function(){
			$location.path('document/create/');
		};
		
		$scope.editDocument = function(docId){
			$location.path('document/edit/' + docId);
		};
		
	}]);

	controllers.controller('CreateController', ['$scope','$http', '$location', '$filter', function($scope, $http, $location, $filter){
		
		$scope.showWeeks = false;
		
		
		$scope.saveDocument = function () {
			console.log('saveDocument called');
			var pubDate = $scope.document.pubDate != '' ? $scope.document.pubDate:new Date();
			$scope.document.pubDate = $filter('date')(pubDate,'yyyy-MM-dd'); 

			$http.post('/DocumentViewer/document/save', $scope.document).
			success(function (data) {
				console.log("saveDocument success: " + data);
				$scope.document = '';
				$location.path("/documents")
			}).
			error(function (data) {
				console.log('saveDocument error: ' + data);

				var errors = {};
				$(data.errors).each( function() {
					console.log(this.message);
					errors[this.field] = this.message;
				});
				$scope.errors = errors;
			}
			);
		};

		$scope.cancel = function() {
			console.log('cancel called');

			$location.path("/documents");
		};
		
		
	}]);


	controllers.controller('EditController', ['$scope','$http', '$location', '$filter', '$routeParams', function($scope, $http, $location, $filter, $routeParams){
		$http.get('/DocumentViewer/document/edit/' + $routeParams['documentId']).
			success(function(data) {
				console.log('loadDocument success');

				$scope.document = data;
				angular.copy($scope.document, $scope.copy);
			}
		);
		

		$scope.showWeeks = false;
		
		
		$scope.saveDocument = function () {
			console.log('updateDocument called');
			
			$scope.document.pubDate = $filter('date')($scope.document.pubDate,'yyyy-MM-dd'); 

			$http.put('/DocumentViewer/document/update', $scope.document).
			success(function (data) {
				console.log("update Document success: " + data);
				$scope.document = '';
				$location.path("/documents")
			}).
			error(function (data) {
				console.log('updateDocument error: ' + data);

				var errors = {};
				$(data.errors).each( function() {
					console.log(this.message);
					errors[this.field] = this.message;
				});
				$scope.errors = errors;
			}
			);
		};

		$scope.cancel = function() {
			console.log('cancel called');

			$location.path("/documents");
		};
	}]);


})();