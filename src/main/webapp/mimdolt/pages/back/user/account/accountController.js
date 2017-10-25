/**
 * Created by Ghost on 11/11/2016.
 */
angular.module('myApp').controller("accountController", ["$scope", "accountService", function ($scope, accountService) {
    var vm = this;

    vm.enableEdit = false;
    vm.userInfo = {
        firstName: "",
        lastName: "",
        email: "",
        mobile: "",
        username: "",
        password: ""
    };

    vm.updateProfile = updateProfile;


    function updateProfile() {
        accountService.update(vm.userInfo).$promise.
            then(
            function (response) {
                vm.enableEdit = false;
            },
            function (error) {

            }
        );
    }

    function _init() {
        accountService.query().$promise.then(function (response) {
            vm.userInfo = response;

        })
    }

    _init();
}]);
angular.module('myApp').controller("changePassController", ["$scope", "accountService", function ($scope, accountService) {
    var vm = this;

    vm.changePassword = {
        oldPass: "",
        newPass: "",
        rePass: ""
    };

    vm.changePass = function () {
        accountService.changePass(vm.changePassword).$promise.
            then(
            function (response) {
            }
        );
    }

}]);