/**
 * Created by Ghost on 12/17/2016.
 */
angular.module('myApp').register.controller("productsController", ["$scope", "productService", "categoryService", "ngNotify", "$http", "$state", "products",
    function ($scope, productService, categoryService, ngNotify, $http, $state, products) {

        var vm = this;
        /**
         *
         * private variables
         *
         */

        vm.selected = 0;
        /**
         *
         * public method
         *
         */

        vm.getPage = getPage;
        vm.changeStatus = changeStatus;
        vm.init = init;


        init();

        function init() {
            console.log(products);
            vm.products = products.data;
            vm.pages = new Array(products.count);
        }

        function getPage(page) {
            vm.selected = page;
            productService.getPage({page: page}).$promise.then(
                function (response) {
                    vm.products = response.data;
                    vm.pages = new Array(response.count);

                },
                function (error) {
                });
        }

        function changeStatus(productId) {
            productService.changeStatus({productId: productId}).$promise.then(
                function (response) {
                    var page = 0;
                    $state.params.page == undefined ? page = 0 : page = $state.params.page;
                    $scope.$evalAsync(function () {
                        getPage(page);
                    });
                },
                function (error) {
                });
        }
    }]);
