/**
 * Created by Ghost on 11/11/2016.
 */
angular.module('myApp').controller("manufacturersController", ["$scope", "manufacturerService", "ngNotify", "$state",
    function ($scope, manufacturerService, ngNotify, $state) {
    var vm = this;

    vm.manufacturer = {
        "name": "",
        "sortOrder": 0
    };
    vm.success = false;
    /**
     *   public methods
     */
    vm.add = add;
    vm.update = update;
    vm.clear = clear;



    if ($state.current.name == "admin.manufacturer") {
        init();
    }



    function init() {
        manufacturerService.query().$promise.then(
            function (response) {
                console.log(response);
                vm.manufacturers = response;
            },
            function (error) {
            });
    }

    /**
     *
     * @param manufacturers
     */
    function add() {
        $scope.$broadcast('show-errors-check-validity');
        if ($scope.categoryForm.$valid) {
            manufacturerService.create(vm.manufacturer).$promise
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

    function update(manufacturer) {
        var opts = [];
        angular.forEach(manufacturer.values, function (tag, i) {
            opts.push(tag.text);
        });
        manufacturer.values = opts;
        manufacturerService.create(manufacturer).$promise
            .then(
            function (response) {
                notify();
                clear();
                console.log(response);
            },
            function (error) {
                console.log("The request failed: " + error);
            });
    }


    function clear() {
        $scope.$broadcast('show-errors-reset');
        vm.manufacturer = {
            "name": "",
            "enable": "",
            values: []
        };
    }

    function notify() {
        ngNotify.set('عملیات با موفقیت انجام شد', {
            target: '#notifyTarget',
            duration: 3000,
            position: 'top',
            type: 'success',
            sticky: false
        });
    }

}]);
angular.module('myApp').controller("manufacturerController", ["$scope", "manufacturerService", "ngNotify", "$state","manufacturer",
    function ($scope, manufacturerService, ngNotify, $state,manufacturer) {
        var vm = this;

        vm.manufacturer = {
            "name": "",
            "sortOrder": 0
        };
        /**
         *   public methods
         */
        vm.add = add;
        vm.update = update;
        vm.clear = clear;




        if ($state.current.name == "admin.manufacturer.edit") {
           vm.manufacturer = manufacturer;
            console.log( vm.manufacturer);
        }



        /**
         *
         * @param manufacturers
         */
        function add() {
            $scope.$broadcast('show-errors-check-validity');
            if ($scope.categoryForm.$valid) {
                manufacturerService.create(vm.manufacturer).$promise
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

        function update(manufacturer) {
            var opts = [];
            angular.forEach(manufacturer.values, function (tag, i) {
                opts.push(tag.text);
            });
            manufacturer.values = opts;
            manufacturerService.create(manufacturer).$promise
                .then(
                function (response) {
                    notify();
                    clear();
                    console.log(response);
                },
                function (error) {
                    console.log("The request failed: " + error);
                });
        }


        function clear() {
            $scope.$broadcast('show-errors-reset');
            vm.manufacturer = {
                "name": "",
                "enable": "",
                values: []
            };
        }

        function notify() {
            ngNotify.set('عملیات با موفقیت انجام شد', {
                target: '#notifyTarget',
                duration: 3000,
                position: 'top',
                type: 'success',
                sticky: false
            });
        }

    }]);

