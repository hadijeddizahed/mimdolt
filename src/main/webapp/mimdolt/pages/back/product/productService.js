/**
 * Created by Hadi Jeddi Zahed on 11/13/2016.
 */
angular.module('myApp').factory("productService", [
    "$resource",
    function ($resource) {
        var baseUrl = "/api/admin/product";
        return $resource(baseUrl, {}, {
            query: {method: "GET", isArray: true},
            queryAsSelectBox: {method: "GET", url: "/api/admin/product/selectBox", isArray: true},
            create: {method: "POST"},
            get: {
                method: "GET", url: "/api/product/find/:id"
            },
            getByCode: {
                method: "GET", url: "/api/product/findByCode/:code"
            },
            remove: {method: "DELETE", url: "/api/StudentsApi?id=:id"},
            update: {method: "PUT", url: "/"},
            changeStatus: {
                method: "POST", url: baseUrl + "/changeStatus/:productId", params: {productId: '@productId'}
            },
            removeImage: {
                method: "POST", url: baseUrl + "/delete/:imageId", params: {
                    imageId: '@imageId'
                }
            },
            getProductImages: {method: "GET", isArray: true, url: "/api/product/getProductImages/:productId"},
            setDefaultImage: {
                method: "POST", url: baseUrl + "/setDefaultImage/:productId/image/:imageId",
                params: {productId: '@productId', imageId: '@imageId'}
            },
            getPage: {
                url: '/api/product/page/:page',
                method: 'GET',
                params: {
                    page: '@page'
                }
            },
            getByCategory:{
                url: '/api/product/category/:code/page/:page',
                method: 'GET',
                params: {
                    code: '@code',
                    page:'@page'
                }
            },
            getProductFilterPanel:{
                url: '/api/product/filterPanel/:code',
                method: 'GET',
                isArray: true,
                params: {
                    code: '@code'
                }
            },

            getLastSeen: {method: "GET", isArray: true, url: "/api/product/lastSeen"}
        });
    }
]);