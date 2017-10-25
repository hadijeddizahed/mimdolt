/**
 * Created by Hadi Jeddi Zahed on 11/13/2016.
 */
angular.module('myApp').factory("registerService", [
    "$resource",
    function ($resource) {
        return $resource("", {}, {
            query: {method: "GET", isArray: true},
            create: {method: "POST",url:"/register"},
            //get: {method: "GET", url: "/find?id=:id"},
            remove: {method: "DELETE", url: "/api/StudentsApi?id=:id"},
            sendConfirmCode: {method: "POST", url: "/register/send?username=:username"},
            update: {method: "PUT", url: "/api/StudentsApi?id=:id"},
            get:{method:"GET",url:"/page?page=:page"}
        });
    }
]);