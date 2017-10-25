/**
 * Created by Ghost on 5/5/2017.
 */

angular.module('myApp').controller("addressController", ["$scope", "addressList", "addressService", function ($scope, addressList, addressService) {
    var vm = this;


    vm.address = {
        id: null,
        fullName: "",
        mobile: "",
        phone: "",
        province: "",
        city: "",
        postalAddress: "",
        postalCode: ""
    };
    vm.enableNewAddress = false;

    vm.addAddress = addAddress;
    vm.getAddressInfo = getAddressInfo;
    vm.chooseAddress = chooseAddress;
    vm.removeAddress = removeAddress;


    function addAddress() {
        addressService.create(vm.address).$promise.then(
            function (response) {
            },
            function (error) {
            });
    }

    function getAddressInfo(address) {
        vm.address = address;
        vm.enableNewAddress = true;

    }

    function chooseAddress(addressID) {
        addressService.setDefaultAddress({id: addressID}).$promise.then(
            function (response) {
                $state.go("addAddress", {}, {reload: true});
            },
            function (error) {
            });
    }

    function removeAddress(id){
        addressService.remove({id:id}).$promise.then(
            function (response) {
            },
            function (error) {
            });
    }

    function _init() {
        vm.addressList = addressList;
        console.log(vm.addressList);
    }

    _init();
}]);
