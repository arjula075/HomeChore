var roflApp = angular.module('roflApp', ['ngAnimate', 'ngTouch']);

roflApp.controller('indexController', function indexController($scope, $http) {
	
	$scope.login = function () {
		console.log($scope.user);
		$http.post('/HomeChore-web/rest/login', $scope.user).
		success(function(data, status, headers, config) {
			console.log('data', data);
			window.location.replace("/HomeChore-web/app/");
		}).
		error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
	};
	
	$scope.register = function () {
		$('#registerModal').modal('show');
	};
	
	$scope.submitRegistration = function() {
		
		console.log($scope.newUser);
		$scope.pivotUid = $scope.newUser.uid;
		$http.post('/HomeChore-web/rest/register', $scope.newUser).
		success(function(data, status, headers, config) {
			console.log(data);
			$scope.newUser = undefined;
			$('#registerModal').modal('hide');
			$('#successRegistration').modal('show');
			$scope.user = new Object();
			$scope.user.uid = $scope.pivotUid;
		}).
		error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
		
	};

	$scope.safeApply = function(fn) {
		var phase = this.$root.$$phase;
		if(phase == '$apply' || phase == '$digest') {
			if(fn && (typeof(fn) === 'function')) {
				fn();
			}	
		} 
		else {
		this.$apply(fn);
		}
	};
	
	$('#registerModal').modal('hide');
		
});

  function testAnim(x) {
    $('#animationSandbox').removeClass().addClass(x + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
      $(this).removeClass();
    });
  };