/**
 * Created by Hadi Jeddi Zahed on 11/13/2016.
 */
angular.module('myApp').factory("categoryService", [
    "$resource",
    function ($resource) {
        var baseUrl = "/api/admin/category";
        return $resource("", {}, {
            query: {method: "GET",url:"/api/category", isArray: true},
            queryAsFlat: {method: "GET",url:"/api/category/flat", isArray: true},
            list : {method: "GET",url:"/api/category/list", isArray: true},
            listChild : {method: "GET",url:"/api/category/listChild", isArray: true},
            create: {method: "POST", url : "/api/admin/category"},
            //get: {method: "GET", url: "/find?id=:id"},
            remove: {method: "DELETE", url: "/api/StudentsApi?id=:id"},
            update: {method: "PUT", url: "/api/StudentsApi?id=:id"},
            get:{method:"GET",url:baseUrl+"/page?page=:page"},
            findRange:{
                url: '/api/admin/category/page/:page',
                method: 'GET',
                params:{
                    page:'@page'
                }
            }
        });
    }
]);