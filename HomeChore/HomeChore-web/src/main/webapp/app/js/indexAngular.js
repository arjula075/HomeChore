var roflApp = angular.module('roflApp', ['ngAnimate', 'ngTouch']);

roflApp.controller('indexController', function indexController($scope, $http) {
	
	$scope.logOut = function () {
		
		$http.delete('/HomeChore-web/rest/login').
		success(function(data, status, headers, config) {
			console.log(data);
			window.location.replace("/HomeChore-web/");			
		}).
		error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
		
	}

	$scope.swipeRightTask = function (taskRow) {
		console.log("right");
		console.log(taskRow);
		taskRow.showActions = true;
		$scope.safeApply();
		
	};
	
	$scope.swipeLeftTask = function (taskRow) {
		console.log("left");
		console.log(taskRow);
		taskRow.showActions = false;
		$scope.safeApply();
		
	};
	
	$scope.showTaskActions = function (taskRow) {
		
		return taskRow.showActions;
		
	};
	
	$scope.manageFamily = function () {
		window.location.href = '/HomeChore-web/app/collective.html';
	};
	
	$scope.hideTask = function (blnShowTask) {
		
		if ($scope.blnShowComplete) {
			return false;
		}
		
		return blnShowTask;
		
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
	
	$scope.clickTask = function (taskRow) {
		
		console.log(taskRow);
		taskRow.completeDate = null;
		taskRow.deadline = null;
		$http.post('/HomeChore-web/rest/taskexecution', taskRow).
		success(function(data, status, headers, config) {
			console.log(data);
			
		}).
		error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
		
	};
	
	$scope.createTestData = function() {
		$http.post('/HomeChore-web/rest/taskexecution/createTestData', $scope.tasks).
		success(function(data, status, headers, config) {
			console.log(data);
			
		}).
		error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
		
	};
	
	$scope.getTasks = function() {
		
		$http.get('/HomeChore-web/rest/taskexecution', $scope.tasks).
		success(function(data, status, headers, config) {
			$scope.tasks = data;
			
		}).
		error(function(data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
		});
		
	};
	
	$scope.blnShowComplete = false;
	
	$scope.getTasks();
	
	
});

  function testAnim(x) {
    $('#animationSandbox').removeClass().addClass(x + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
      $(this).removeClass();
    });
  };