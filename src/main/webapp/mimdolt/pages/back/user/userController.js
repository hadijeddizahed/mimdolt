/**
 * Created by Ghost on 2/22/2017.
 */
/**
 * Created by Ghost on 11/11/2016.
 */
angular.module('myApp').controller("usersController", ["$scope", "users","userService", "ngNotify", function ($scope, users,userService, ngNotify) {
    var vm = this;


    /**
     *
     * public method
     *
     */

    vm.getPage = getPage;

    init();

    function init() {
        vm.users = users.data;
        vm.pages = new Array(users.count);
        console.log(vm.users);
    }

    function getPage(page) {
        vm.selected = page;
        userService.getPage({page: page}).$promise.then(
            function (response) {
                vm.users = response.data;
                vm.pages = new Array(response.count);

            },
            function (error) {
            });
    }

}]);
angular.module('myApp').controller("userController", ["$scope", "userService", "ngNotify", "$state","user",
    function ($scope, userService, ngNotify, $state,user) {
        var vm = this;

        vm.user = {};


        /**
         *   public methods
         */
        vm.add = add;
        vm.update = update;
        vm.clear = clear;



        if ($state.current.name == "admin.user.edit") {
            vm.user = user;
            console.log(vm.user);
        }



        /**
         *
         * @param options
         */
        function add() {
            console.log(vm.user);
            $scope.$broadcast('show-errors-check-validity');
            if ($scope.categoryForm.$valid) {
               userService.create(vm.user).$promise
                    .then(
                    function (response) {
                        notify();
                        clear();
                    },
                    function (error) {
                        console.log(error);
                    });
            }
        }

        function update(option) {

        }

        function clear() {
            $scope.$broadcast('show-errors-reset');
            vm.option = {
                "name": "",
                "enable": "",
                values: []
            };
        }


        function notify() {
            ngNotify.set('⁄„·?«  »« „Ê›ﬁ?  «‰Ã«„ ‘œ', {
                target: '#notifyTarget',
                duration: 3000,
                position: 'top',
                type: 'success',
                sticky: false
            });
        }

    }]);
