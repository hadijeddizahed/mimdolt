/**
 * Created by Hadi Jeddi Zahed on 11/13/2016.
 */
angular.module('myApp').factory("accountService", [
    "$resource",
    function ($resource) {
        var baseUrl = "/api/user";
        return $resource("", {}, {
            query: {method: "GET",url:"security/account"},
            update:{method:"POST", url:"/user/updateProfile"},
            changePass:{method: "POST", url:"/user/changePass"}
        });
    }
]);