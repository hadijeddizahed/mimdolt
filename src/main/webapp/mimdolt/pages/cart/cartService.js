/**
 * Created by Hadi Jeddi Zahed on 11/13/2016.
 */
angular.module('myApp').factory("cartService", [
    "$resource",
    function ($resource) {
        var baseUrl = "/api/cart/";
        return $resource("", {}, {
            query: {method: "GET", url: "/api/category", isArray: true},
            create: {method: "POST", url: "/api/cart/add"},
            remove: {method: "DELETE", url: "/api/StudentsApi?id=:id"},
            update: {method: "PUT", url: "/api/StudentsApi?id=:id"},
            get: {method: "GET", url: "/api/cart/display"},
            updateCartItem: {method: "POST", url: "/api/cart/updateCartItem"},
            deleteItem: {method: "POST", url: "/api/cart/deleteItem/:code", params: {code: '@code'}}

        });
    }
]);