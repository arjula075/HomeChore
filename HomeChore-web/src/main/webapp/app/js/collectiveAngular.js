var roflApp = angular.module('roflApp', ['ngAnimate', 'ngTouch']);

roflApp.controller('collectiveController', function indexController($scope, $http) {
	
	$scope.initializeScreen = function () {
		
		$http.get('/HomeChore-web/rest/taskexecution', $scope.tasks).
		success(function(data, status, headers, config) {
			$scope.tasks = data;
			
		}).
		error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
		
	};
	
	$scope.initializeScreen();
	
	$scope.test = "test";
	
	
	
});