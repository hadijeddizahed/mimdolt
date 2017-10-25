/**$scope,addressService,$state
 * Created by Ghost on 5/2/2017.
 */
angular.module('myApp').controller("paymentController", ["$scope", "shoppingCart", "$state", "defaultAddress",
    function ($scope, shoppingCart, $state, defaultAddress) {
        var vm = this;

        vm.shoppingCart = {};
        vm.defaultAddress = {};


        vm.totalPurchase = totalPurchase;

        function _init() {
            vm.shoppingCart = shoppingCart.cart;
            vm.defaultAddress = defaultAddress;

            console.log(defaultAddress);
        }

        function totalPurchase(){
            var total = 0;
            angular.forEach(vm.shoppingCart,function(cart){
                total += (cart.price * cart.quantity);
            });
            return total;
        }


        _init();
    }]);
