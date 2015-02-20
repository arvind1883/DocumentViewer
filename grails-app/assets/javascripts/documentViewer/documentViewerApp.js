(function(){
	var documentViewerApp = angular.module('documentViewerApp', ['controllers', 'ngRoute']);
	
	documentViewerApp.config(['$routeProvider',function($routeProvider) {
		$routeProvider.
			when('/document/create', {
	            templateUrl: 'assets/partials/document-form.html',
	            controller: 'CreateController'
	        }).
	        when('/document/edit/:documentId', {
                templateUrl: 'assets/partials/document-form.html',
                controller: 'EditController'
            }).
			otherwise({
				templateUrl: 'assets/partials/document-list.html',
				controller: 'ListController'
            });
	}]);

})();
