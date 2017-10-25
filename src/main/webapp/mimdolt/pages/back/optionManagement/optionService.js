/**
 * Created by Dev4 on 11/13/2016.
 */
angular.module('myApp').factory("optionService", [
    "$resource",
    function ($resource) {
        return $resource("/api/admin/option", {}, {
            query: {method: "GET", isArray: true},
            create: {method: "POST"},
            //get: {method: "GET", url: "/find?id=:id"},
            remove: {method: "DELETE", url: "/api/StudentsApi?id=:id"},
            update: {method: "PUT", url: "/api/StudentsApi?id=:id"},
            get:{method:"GET",url:"/api/admin/option/find/:id",params:{
                id:'@id'
            }},
            findRange:{
                url: '/rest/api/v2/category/page/:page',
                method: 'GET',
                params:{
                    page:'@page'
                }
            }
        });
    }
]);