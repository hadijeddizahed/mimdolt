/**
 * Created by Dev4 on 11/13/2016.
 */
angular.module('myApp').factory("manufacturerService", [
    "$resource",
    function ($resource) {
        return $resource("/api/admin/manufacturer", {}, {
            query: {method: "GET", url:"/api/admin/manufacturers", isArray: true},
            create: {method: "POST"},
            //get: {method: "GET", url: "/find?id=:id"},
            remove: {method: "DELETE", url: "/api/StudentsApi?id=:id"},
            update: {method: "PUT", url: "/api/StudentsApi?id=:id"},
            get:{method:"GET",url:"/api/admin/manufacturer/find/:id",params:{
                id:'@id'
            }}
        });
    }
]);