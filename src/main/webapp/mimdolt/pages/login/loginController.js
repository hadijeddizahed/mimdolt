/**
 * Created by Ghost on 12/31/2016.
 */
angular.module('myApp').controller('LoginController',["$rootScope", "$scope", "AuthSharedService",function ($rootScope, $scope, AuthSharedService) {
    var vm = this;
    vm.rememberMe = true;
    vm.login = function () {
        $rootScope.authenticationError = false;
        console.log(vm.username);
        AuthSharedService.login(
            vm.username,
            vm.password,
            vm.rememberMe
        );
    }
}]);