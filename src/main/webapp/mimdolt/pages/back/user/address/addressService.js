/**
 * Created by Hadi Jeddi Zahed on 11/13/2016.
 */
angular.module('myApp').factory("addressService", [
    "$resource",
    function ($resource) {
        var baseUrl = "/api/user/";
        return $resource("", {}, {
            create: {method: "POST", url: "/user/address/add"},
            remove:{method: "POST", url: "/user/address/remove/:id",
                params: {id: "@id"}
            },
            listAddress: {method: "GET", isArray: true, url: "/user/address/list"},
            setDefaultAddress: {
                method: "POST", url: "/user/address/setDefault/:id",
                params: {id: "@id"}
            },
            getDefaultAddress:{
                method: "GET", url: "/user/address/default"
            }
        });
    }
]);