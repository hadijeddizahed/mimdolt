/**
 * Created by Ghost on 11/11/2016.
 */
angular.module('myApp').controller("cartController", ["$scope", "cartService", "shoppingCart", "$state", "ngDialog", "productService","AuthSharedService","$rootScope",
    function ($scope, cartService, shoppingCart, $state, ngDialog, productService,AuthSharedService,$rootScope) {
        var vm = this;


        vm.cart = {
            cartItem: {
                productId: 0,
                quantity: 1
            }

        }

        vm.shoppingCart = {};


        displayShoppingCart();


        /**
         *   public methods
         */
        vm.add = add;
        vm.displayShoppingCart = displayShoppingCart;
        vm.checkAuthority = checkAuthority;

        /**
         *
         * @param
         */
        function add() {
            cartService.create(vm.cart).$promise
                .then(
                function (response) {
                    console.log("Success");
                },
                function (error) {
                    console.log("The request failed: " + category);
                });

        }


        function displayShoppingCart() {
            if ($state.current.name == "shoppingCart") {
                vm.shoppingCart = shoppingCart.cart;
            }

        }

        function checkAuthority(){
            
        }

        $scope.$on('event:show-login-form', function () {
            ngDialog.open({
                template: 'login2.html', width: '22%', className: 'ngdialog-theme-default',
                controller: ["$scope", "$rootScope","$state",function ($scope, $rootScope,$state) {
                    $scope.login = function () {
                        $rootScope.authenticationError = false;
                        console.log($scope.username);
                        AuthSharedService.login(
                            $scope.username,
                            $scope.password,
                            $scope.rememberMe
                        );
                    }

                    $scope.$on("success-login", function () {
                        ngDialog.close();
                        $state.go("addAddress",{});
                    });
                    $scope.$on('ngDialog.opened', function (event, $dialog) {
                        $dialog.find('.ngdialog-content').css('padding', '3px');
                    });
                }]
            });
        });

        vm.login = function () {


        };
        $scope.$on("add-to-cart", function () {
            $scope.$evalAsync(function () {
                cartService.get().$promise.then(function (response) {
                    vm.shoppingCart = response.cart;
                })
            })
        });

        vm.open = function (cartItemId, productId, quantity) {
            ngDialog.open({
                template: 'myModalContent.html', width: '60%',
                resolve: {
                    productInfo: function () {
                        return productService.get({id: productId}).$promise.then(
                            function (response) {
                                return response;
                            }
                        )

                    }
                },
                controller: function ($scope, $rootScope, productInfo, cartService) {
                    $scope.quantityValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
                    $scope.cartItem = {
                        cartItemId: cartItemId,
                        productId: productId,
                        quantity: quantity,
                        cartItemAttribute: []
                    };
                    $scope.productInfo = productInfo;
                    $scope.attributeId = 0;

                    $scope.addToAttributes = function(attributeId){
                        $scope.cartItem.cartItemAttribute.push(attributeId);
                        alert("ttt");
                    };

                    $scope.slickConfig = {
                        dots: false,
                        autoplay: true,
                        initialSlide: 3,
                        infinite: true,
                        arrows: true,
                        rtl: true,
                        autoplaySpeed: 3000,
                        method: {},
                        event: {}
                    };

                    $scope.updateCartItem = function () {
                        cartService.updateCartItem($scope.cartItem).$promise
                            .then(
                            function (response) {
                                $rootScope.$broadcast('add-to-cart');
                                ngDialog.close();
                            },
                            function (error) {
                                console.log("The request failed: " + $scope.cart);
                            });

                    }

                }
            });

        };

        vm.removeCartItem = function (code) {
            cartService.deleteItem({code:code}).$promise.then(
                function (response) {
                    $rootScope.$broadcast('add-to-cart');
                },
                function (error) {
                    console.log("The request failed: ");
                }
            )
        }
    }]);
