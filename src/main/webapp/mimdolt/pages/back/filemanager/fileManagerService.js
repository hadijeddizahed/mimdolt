/**
 * Created by Hadi Jeddi Zahed on 11/13/2016.
 */
angular.module('myApp').factory("fileManagerService", [
    "$resource",
    function ($resource) {
        return $resource("/api/admin/fm", {}, {
            query: {method: "GET", isArray: true},
            create: {method: "POST"},
            remove: {method: "DELETE", url: "/api/admin/fm/delete/:path",params:{
                path:'@path'
            }},
            get: {
                method: "GET", url: "/api/fm/download/:path",
                params:{
                    path:'@path'
                }
            }
        });
    }
]);