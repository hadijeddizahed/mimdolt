/**
 * Created by Ghost on 11/11/2016.
 */
angular.module('myApp').controller("optionsController", ["$scope", "optionService", "ngNotify", "$state",
    function ($scope, optionService, ngNotify, $state) {
    var vm = this;

    vm.option = {
        "name": "",
        "enable": "",
        "type": "",
        "sortOrder": 0,
        "description": "",
        values: [
            {"value": "", "sortOrder": 0, "description": ""}
        ]
    };
    vm.pages = [];
    vm.optionTypes = ["select", "checkbox", "radio", "text"];
    vm.selected = 0;
    vm.success = false;
    /**
     *   public methods
     */
    vm.add = add;
    vm.update = update;
    vm.clear = clear;
    vm.selectOption = selectOption;
    vm.addOptionValue = addOptionValue;
    vm.removeOptionValue = removeOptionValue;



    if ($state.current.name == "admin.option") {
        init();
    }

    //if ($state.current.name == "admin.option.edit") {
    //   vm.option = option.data;
    //}


    function init() {
        optionService.query().$promise.then(
            function (response) {
                vm.options = response;
            },
            function (error) {
            });
    }

    /**
     *
     * @param options
     */
    function add() {
        $scope.$broadcast('show-errors-check-validity');
        if ($scope.categoryForm.$valid) {
            optionService.create(vm.option).$promise
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
        var opts = [];
        angular.forEach(option.values, function (tag, i) {
            opts.push(tag.text);
        });
        option.values = opts;
        optionService.create(option).$promise
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

    function addOptionValue() {
        var index = vm.option.values.length;
        var item = {"value": "", "sortOrder": 0, "description": ""};
        vm.option.values.splice(index, 0, item);
    }


    function removeOptionValue(index) {
        vm.option.values.splice(index, 1);
    }

    function clear() {
        $scope.$broadcast('show-errors-reset');
        vm.option = {
            "name": "",
            "enable": "",
            values: []
        };
    }

    function selectOption(option) {
        vm.option = option;
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
angular.module('myApp').controller("optionController", ["$scope", "optionService", "ngNotify", "$state","option",
    function ($scope, optionService, ngNotify, $state,option) {
        var vm = this;

        vm.option = {
            "name": "",
            "enable": "",
            "type": "",
            "sortOrder": 0,
            "description": "",
            values: [
                {"value": "","classValue":"", "sortOrder": 0, "description": ""}
            ]
        };
        vm.pages = [];
        vm.optionTypes = ["select", "checkbox", "radio", "text"];
        vm.selected = 0;
        vm.success = false;
        /**
         *   public methods
         */
        vm.add = add;
        vm.update = update;
        vm.clear = clear;
        vm.selectOption = selectOption;
        vm.addOptionValue = addOptionValue;
        vm.removeOptionValue = removeOptionValue;




        if ($state.current.name == "admin.option.edit") {
           vm.option = option;
            console.log(vm.option);
        }



        /**
         *
         * @param options
         */
        function add() {
            $scope.$broadcast('show-errors-check-validity');
            if ($scope.categoryForm.$valid) {
                optionService.create(vm.option).$promise
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
            var opts = [];
            angular.forEach(option.values, function (tag, i) {
                opts.push(tag.text);
            });
            option.values = opts;
            optionService.create(option).$promise
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

        function addOptionValue() {
            var index = vm.option.values.length;
            var item = {"id":null,"value": "", "sortOrder": 0, "description": ""};
            vm.option.values.splice(index, 0, item);
        }


        function removeOptionValue(index) {
            vm.option.values.splice(index, 1);
            console.log(vm.option);
        }

        function clear() {
            $scope.$broadcast('show-errors-reset');
            vm.option = {
                "name": "",
                "enable": "",
                values: []
            };
        }

        function selectOption(option) {
            vm.option = option;
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

