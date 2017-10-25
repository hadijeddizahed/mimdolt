/**
 * Created by Ghost on 12/17/2016.
 */
/**
 * Created by Ghost on 12/17/2016.
 */
angular.module('myApp').controller("productViewController", ["$scope","$rootScope", "product","cartService",
    function ($scope,$rootScope, product,cartService) {
        
        /**
         *
         * private variables
         *
         */


        $scope.notSelectedOption = true;
        $scope.selected = 0;
        $scope.productInfo = {
        };
        $scope.cart = {
            cartItem: {
                cartItemId: null,
                productId: 0,
                quantity: 1,
                cartItemAttribute: []
            }
        };

        /**
         *
         * public method
         *
         */

        $scope.init = init;


        init();

        function init() {

            $scope.productInfo = product;
            console.log($scope.productInfo);
        }

        $scope.addToCart = function (cart) {
            var attributeCount = _hasProperty();
            if (attributeCount!= 0) {
                if (cart.cartItem.cartItemAttribute.length < attributeCount) {
                    $scope.notSelectedOption = false;
                } else {
                    $scope.notSelectedOption = true;
                }
            }
            if ($scope.notSelectedOption) {
                cartService.create($scope.cart).$promise
                    .then(
                    function (response) {
                        $rootScope.$broadcast('add-to-cart');
                    },
                    function (error) {
                        console.log("The request failed: " + $scope.cart);
                    });
            }

        }

        function _hasProperty() {
            var i = 0;
            for (var item in $scope.productInfo.attributesMap) {
                i++;
            }
            return i;

        }

        $scope.slickConfig = {
            dots: true,
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



    }]);

