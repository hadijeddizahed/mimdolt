/**
 * Created by Ghost on 12/31/2016.
 */
angular.module('myApp').controller("registerController",  ["$scope", "registerService", "ngNotify", function ($scope, registerService, ngNotify) {
    var vm = this;

    vm.userInfo = {
        firstName:"",
        lastName:"",
        email:"",
        mobile:"",
        username:"",
        password:""
    };

    /**
     * public methods
     *
     */
    vm.register = register;





    function register(userInfo){
        console.log(userInfo);
        $scope.$broadcast('show-errors-check-validity');
            registerService.create(userInfo).$promise
                .then(
                function (response) {
                    notify();
                    clearForm();
                },
                function (error) {
                    console.log("The request failed: " + error);
                });
    }




    function clearForm() {
        $scope.registerForm.$setPristine();
        vm.userInfo = {
            firstName:"",
            lastName:"",
            email:"",
            mobile:"",
            username:"",
            password:""
        };
    }
    function notify() {
        ngNotify.set('عمل?ات با موفق?ت انجام شد', {
            target: '#notifyTarget',
            duration: 3000,
            position: 'top',
            type: 'success',
            sticky: false
        });
    }

}]);

