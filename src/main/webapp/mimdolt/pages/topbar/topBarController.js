/**
 * Created by Ghost on 12/31/2016.
 */
angular.module('myApp').controller("topBarController", ["AuthSharedService", "$state", "ngDialog", "$timeout", "registerService", "$http", "$rootScope", 'vcRecaptchaService',
    function (AuthSharedService, $state, ngDialog, $timeout, registerService, $http, $rootScope, vcRecaptchaService) {
        var vm = this;

        vm.publicKey = "6Lc6riUUAAAAAHZPBY6ya2biYh-y1ciYwPNeVdn5";
        vm.registration = {
            'firstName': "",
            'email': "",
            'password': "",
            'gRecaptchaResponse': ""
        };

        vm.hasError = false;


        vm.logout = function () {
            AuthSharedService.logout();
            $state.go("store");
        };


        vm.login = function () {
            ngDialog.open({
                template: 'login.html', width: '22%', className: 'ngdialog-theme-default',
                controller: ["$scope", "$rootScope",function ($scope, $rootScope) {
                    $scope.login = function () {
                        $rootScope.authenticationError = false;
                        AuthSharedService.login(
                            $scope.username,
                            $scope.password,
                            $scope.rememberMe
                        );
                    }

                    $scope.$on("success-login", function () {
                        ngDialog.close();
                    });
                    $scope.$on("failed-login", function () {
                        $scope.displayErrorMsg = true;
                    });
                    $scope.$on('ngDialog.opened', function (event, $dialog) {
                        $dialog.find('.ngdialog-content').css('padding', '3px');
                    });
                }]
            });

        };

        vm.register = function () {
            ngDialog.open({
                template: 'mimdolt/partials/register/confirm-code-input.html',
                width: '34%',
                className: 'ngdialog-theme-default',
                controller: "topBarController",
                controllerAs: "vm"
            });
        };


        //vm.createAccount = function () {
        //    vm.registration.username = $rootScope.username;
        //    registerService.create(vm.registration).$promise
        //        .then(
        //        function (response) {
        //
        //        },
        //        function (error) {
        //            console.log("The request failed: " + error);
        //        });
        //};

        //vm.sendConfirmCode = function (username) {
        //    $rootScope.username = username;
        //    $http.post("/sendConfirmCode?username=" + username, {}).then(function (response) {
        //        //ngDialog.close();
        //        ngDialog.open({
        //            template: 'mimdolt/partials/register/confirm-code-input.html',
        //            width: '34%',
        //            className: 'ngdialog-theme-default',
        //            controller: "topBarController",
        //            controllerAs: "vm"
        //        });
        //
        //    }, function (data, status, headers, config) {
        //
        //    });
        //};

        vm.createAccount = function () {
            if (vcRecaptchaService.getResponse() === "") { //if string is empty
                vm.hasError = true;
            } else {
                vm.registration.gRecaptchaResponse = vcRecaptchaService.getResponse();
                console.log(vm.registration);
                console.log(vcRecaptchaService.getResponse());
                registerService.create(vm.registration).$promise
                    .then(
                    function (response) {
                        ngDialog.close();
                        AuthSharedService.login(
                            vm.registration.email,
                            vm.registration.password
                        );
                    },
                    function (error) {
                        console.log("The request failed: " + error);
                    });
            }

        };


    }]);
