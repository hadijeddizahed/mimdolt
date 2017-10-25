/**
 * Created by Ghost on 11/11/2016.
 */
angular.module('myApp').controller("NavbarController", ["$scope", function ($scope) {
    var vm = this;

    vm.isCollapsed = true;
    vm.isCollapsedUser = true;
    vm.isCollapsedMain = true;
    vm.menu = [
        {
            "id": 1,
            "title": "مد?ر?ت فروشگاه",
            "href": "javascript:;",
            "icon": "fa fa-fw fa-dashboard",
            "submenu": [
                {
                    "id": 11,
                    "title": "مد?ر?ت گروه ها",
                    "href": "/admin/category",
                    "icon": "fa fa-fw fa-dashboard"
                },
                {
                    "id": 12,
                    "title": "مد?ر?ت محصولات",
                    "href": "/admin/product",
                    "icon": "fa fa-fw fa-dashboard"
                },
                {
                    "id": 13,
                    "title": "سفارشات",
                    "href": "/admin/orders",
                    "icon": "fa fa-fw fa-dashboard"
                }
            ]
        },
        {"id": 1, "title": "", "href": "", "icon": "", "submenu": []},
        {"id": 1, "title": "", "href": "", "icon": "", "submenu": []},
        {"id": 1, "title": "", "href": "", "icon": "", "submenu": []}
    ];
}]);
