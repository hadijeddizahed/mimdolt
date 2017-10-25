/**
 * Created by Ghost on 12/17/2016.
 */
/**
 * Created by Ghost on 12/17/2016.
 */
angular.module('myApp').controller("homeController", ["$scope","cartService", "productService", "categoryService", "ngNotify", "$http", "$state","$uibModal","ngDialog",
    function ($scope,cartService, productService, categoryService, ngNotify, $http, $state,$uibModal,ngDialog) {

        var vm = this;
        /**
         *
         * private variables
         *
         */


        vm.selected = 0;
        vm.productInfo = {
            title:"",
            fullDescription:"",
            price:"",
            images:[]
        };
        /**
         *
         * public method
         *
         */

        vm.getPage = getPage;
        //vm.addToCart = addToCart;
        vm.init = init;
        vm.displayProduct = displayProduct;


        init();

        function init() {
            //console.log(products);
            //vm.products = products.data;
            //vm.pages = new Array(products.count);
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

        function displayProduct(productId) {
            productService.get({id:productId}).$promise.then(
                function (response) {
                    console.log(response);
                    vm.productInfo = response;
                    console.log(vm.productInfo);

                },
                function (error) {
                });
        }


        /**
         *
         * @param category
         */


        $scope.slickConfig = {
            dots: false,
            autoplay: true,
            initialSlide: 4,
            infinite: true,
            arrows: false,
            rtl: true,
            autoplaySpeed: 3000,
            method: {},
            event: {
            }
        };

        vm.open = function (productId) {
            ngDialog.open({ template: 'myModalContent.html',width: '80%',
                resolve:{
                    productInfo: function(){
                        return productService.get({id:productId}).$promise.then(
                            function(response){
                                console.log(response);
                                return response;
                            }
                        )

                    }
                },
                controller: ["$scope","$rootScope","productInfo","cartService",function($scope,$rootScope,productInfo,cartService){
                    $scope.quantityValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
                    $scope.cart = {
                        cartItem: {
                            cartItemId: null,
                            productId: productId,
                            quantity: 0,
                            cartItemAttribute: []
                        }

                    };
                    $scope.productInfo = productInfo;
                    $scope.sel = "";
                    $scope.addToAttributes = function(attributeId){
                        $scope.cart.cartItem.cartItemAttribute.push(attributeId);
                        console.log($scope.cart);
                    }
                    $scope.slickConfig = {
                        dots: false,
                        autoplay: true,
                        initialSlide: 3,
                        infinite: true,
                        arrows: true,
                        rtl: true,
                        autoplaySpeed: 3000,
                        method: {},
                        event: {
                        }
                    };

                     $scope.addToCart = function() {
                        console.log("add to cart");
                        cartService.create($scope.cart).$promise
                            .then(
                            function (response) {
                                $rootScope.$broadcast('add-to-cart');
                            },
                            function (error) {
                                console.log("The request failed: " + $scope.cart);
                            });

                    }

                }]
            });
            
        };

    }]);

