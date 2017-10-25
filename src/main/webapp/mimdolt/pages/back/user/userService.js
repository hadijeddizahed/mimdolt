/**
 * Created by Hadi Jeddi Zahed on 11/13/2016.
 */
angular.module('myApp').factory("userService", [
    "$resource",
    function ($resource) {
        var baseUrl = "/admin/users";
        return $resource("", {}, {
            query: {method: "GET",url:"/admin/users", isArray: true},
            create: {method: "POST", url : "/admin/users"},
            //get: {method: "GET", url: "/find?id=:id"},
            remove: {method: "DELETE", url: "/admin/users/:id",params:{id:'@id'}},
            update: {method: "PUT", url: "/admin/users/:id",params:{id:'@id'}},
            get:{method:"GET",url: "/admin/users/:id",params:{id:'@id'}},
            getPage:{
            url: '/admin/users/page/:page',
                method: 'GET',
                params:{
                page:'@page'
            }
        }
        });
    }
]);