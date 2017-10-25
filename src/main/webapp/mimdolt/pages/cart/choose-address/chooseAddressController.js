/**
 * Created by Ghost on 5/2/2017.
 */
angular.module('myApp').controller("chooseAddressController", ["$scope", "addressService", "$state", "addressList", "ngDialog",
    function ($scope, addressService, $state, addressList, ngDialog) {
        var vm = this;


        vm.chooseAddress = chooseAddress;
        vm.listAddress = listAddress;


        _init();
        function _init() {
            vm.addressList = addressList;
            console.log(vm.addressList);
        }

        function listAddress(){
            addressService.listAddress().$promise.then(
                function (response) {
                    vm.addressList = response.data;
                },
                function (error) {
                });
        }

        function chooseAddress(addressID) {
            addressService.setDefaultAddress({id: addressID}).$promise.then(
                function (response) {
                    $state.go("addAddress",{},{reload: true});
                },
                function (error) {
                });
        }

        vm.open = function () {
            ngDialog.open({
                template: 'new-address.html', width: '50%',
                controller: ["$scope","addressService","$state",function ($scope,addressService,$state) {
                    var vm = this;
                    $scope.ngDialog = ngDialog;
                    $scope.address = {
                        fullName: "",
                        mobile: "",
                        phone: "",
                        province: "",
                        city: "",
                        postalAddress: "",
                        postalCode: ""
                    };
                    $scope.addAddress= function() {
                        addressService.create($scope.address).$promise.then(
                            function (response) {
                                $state.go("addAddress",{},{reload: true});
                                $scope.ngDialog.close();
                            },
                            function (error) {
                            });
                    }

                }]
            });

        };

    }]);
