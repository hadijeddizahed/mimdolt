/**
 * Created by Ghost on 12/26/2016.
 */
angular.module('myApp').controller("navbarController", ["$scope", "cartService", "cartDetail", "categories", "ngDialog", function ($scope, cartService, cartDetail, categories, ngDialog) {

    var vm = this;
    vm.isNavCollapsed = true;
    vm.cartDetail = [];
    vm.status = {isopen: false};
    vm.showMiniCart = false;
    vm.totalPrice = 0;

    vm.cartDetail = cartDetail.cart;
    vm.categories = categories;


    vm.deleteItem = deleteItem;

    $scope.$on('add-to-cart', function (event, args) {
        vm.totalPrice = 0;
        vm.displayCart();
        ngDialog.open({
            template: 'lastItem.html', width: '60%',
            resolve: {
                cartDetail: function () {
                    return cartService.get().$promise.then(
                        function (response) {
                            return response;
                        }
                    )
                }
            },
            controller: ["$scope", "cartDetail",function ($scope, cartDetail) {
                $scope.lastCartItem = {};
                $scope.ngDialog = ngDialog;
                console.log(cartDetail.cart);
                console.log(args.cartItem.cartItemAttribute);
                for (var cart1 in cartDetail.cart) {
                    if (cartDetail.cart[cart1].productId == args.cartItem.productId &&
                        isSameProduct(args.cartItem.cartItemAttribute, cartDetail.cart[cart1].attributeNameValues)) {
                        $scope.lastCartItem = cartDetail.cart[cart1];
                    }
                }
                $scope.lastCartItem.quantity = args.cartItem.quantity;
            }]
        });
    });


    vm.displayCart = function () {
        cartService.get().$promise
            .then(
            function (response) {
                vm.cartDetail = response.cart;
            },
            function (error) {
                console.log("The request failed: ");
            });
    };

    function deleteItem(code) {
        cartService.deleteItem({code: code}).$promise.then(
            function (response) {
                vm.totalPrice = 0;
                vm.displayCart();
            },
            function (error) {
                console.log("The request failed: ");
            }
        )
    }

    vm.count = function (child) {
        var a = [];
        if (child instanceof Array) {
            for (var i = 0; i < child.length; i++) {
                a.push(i);
            }
        }
        return a;
    }

    function isSameProduct(cartItemAttribute, attributeNameValues) {
        var count = 0;
        for (var attrib in attributeNameValues) {
            if (cartItemAttribute.indexOf(attributeNameValues[attrib].optionId) > -1) {
                count = count + 1;
            }
        }
        return count === cartItemAttribute.length;
    }


}]);
