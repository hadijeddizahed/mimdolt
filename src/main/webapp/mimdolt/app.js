'use strict';

angular
    .module('myApp', ['ngResource', 'ngRoute', 'swaggerUi', 'oc.lazyLoad', 'angular-loading-bar', 'ngNotify', 'sticky', 'ui.bootstrap', 'slickCarousel', 'ngDialog',
        'http-auth-interceptor', 'ngAnimate', 'angular-spinkit', 'ui.router', 'uiRouterStyles', 'ui.bootstrap.showErrors', 'ngTagsInput',
        'summernote', 'angucomplete-alt', 'ec.stateloader', 'vcRecaptcha','cfp.loadingBar']);


angular.module('myApp').constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    user: 'user'
});

angular.module('myApp').config(["$stateProvider", "$urlRouterProvider", "$locationProvider", "$compileProvider", "USER_ROLES", function ($stateProvider, $urlRouterProvider, $locationProvider, $compileProvider, USER_ROLES) {
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|file|ftp|blob):|data:image\//);


    $stateProvider
        .state('admin', {
            url: '/admin',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            abstract: true,
            views: {
                'navbar': {
                    templateUrl: 'mimdolt/pages/back/navbar/navbar.html',
                    controller: 'NavbarController as vm'
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            },
            data: {
                css: ['/resources/css/admin.css', '/resources/css/admin-rtl.css']
            }
        })
        .state('admin.dashboard', {
            url: '/dashboard',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/dashboard/dashboard.html'
                    //controller: 'dashboardController as vm'
                }
            }
            ,
            data: {
                css: ['/resources/css/sb-admin.css', '/resources/css/sb-admin-rtl.css']
            }
        })
        .state('admin.user', {
            url: '/user',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/user/users.html',
                    controller: 'usersController as vm',
                    resolve: {
                        users: function (userService) {
                            return userService.getPage({page: 0}).$promise;
                        }
                    }
                }
            }
        })
        .state('admin.user.new', {
            url: '/new',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/user/add.html',
                    controller: 'userController as vm',
                    resolve: {
                        user: function (userService, $stateParams) {
                            var userId = $stateParams.id;
                            var user;
                            if (userId != undefined) {
                                user = userService.get({id: userId}).$promise;
                                return user;
                            }

                        }
                    }
                }
            }
        })
        .state('admin.user.edit', {
            url: '/edit/:id',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/user/add.html',
                    controller: 'userController as vm',
                    resolve: {
                        user: function (userService, $stateParams) {
                            var userId = $stateParams.id;
                            return userService.get({id: userId}).$promise;
                        }
                    }
                }
            }
        })
        .state('admin.product', {
            url: '/product',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/product/products.html',
                    controller: 'productsController as vm',
                    resolve: {
                        products: function (productService) {
                            return productService.getPage({page: 0}).$promise;
                        }
                    }
                }
            }
            ,
            data: {
                css: ['/resources/css/sb-admin.css', '/resources/css/sb-admin-rtl.css']
            }
        })
        .state('admin.product.page', {
            url: '/page/:page',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/product/products.html',
                    controller: 'productsController as vm',
                    resolve: {
                        products: function (productService, $stateParams) {
                            return productService.getPage({page: $stateParams.page}).$promise;
                        }
                    }
                }
            }
        })
        .state('admin.product.edit', {
            url: '/edit/:productId?',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/product/add.html',
                    controller: 'productController as vm',
                    resolve: {
                        categories: function (categoryService) {
                            return categoryService.listChild().$promise;
                        },
                        product: function (productService, $stateParams) {
                            var productId = $stateParams.productId;
                            return productService.get({id: productId}).$promise;
                        },
                        manufacturers: function (manufacturerService) {
                            return manufacturerService.query().$promise;
                        },
                        products: function (productService) {
                            return productService.queryAsSelectBox().$promise;
                        },
                        options: function (optionService) {
                            return optionService.query().$promise;
                        }
                    }
                }
            }
        })
        .state('admin.product.uploadImage', {
            url: '/uploadImage?productId',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/product/uploadImage.html',
                    controller: 'uploadController',
                    resolve: {
                        productImages: function (productService, $stateParams) {
                            var productId = $stateParams.productId;
                            return productService.getProductImages({productId: productId}).$promise;
                        }
                    }
                }
            }
        })
        .state('admin.category', {
            url: '/category/all',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/category/category.html',
                    controller: 'categoryController as vm'
                }
            }
        })
        .state('admin.optionManagement', {
            url: '/optionManagement',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/optionManagement/optionManagement.html',
                    controller: 'optionsController as vm'
                }
            }
        })
        .state('admin.option', {
            url: '/option',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/optionManagement/options.html',
                    controller: 'optionsController as vm'
                }
            }
        })
        .state('admin.option.edit', {
            url: '/edit/:id',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/optionManagement/optionManagement.html',
                    controller: 'optionController as vm',
                    resolve: {
                        option: function (optionService, $stateParams) {
                            var optionId = $stateParams.id;
                            return optionService.get({id: optionId}).$promise;
                        }
                    }
                }
            }
        })
        .state('admin.manufacturerManagement', {
            url: '/manufacturerManagement',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/product/manufacturer/manufacturerManagement.html',
                    controller: 'manufacturersController as vm'
                }
            }
        })
        .state('admin.manufacturer', {
            url: '/manufacturer',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/product/manufacturer/manufacturer.html',
                    controller: 'manufacturersController as vm'
                }
            }
        })
        .state('admin.manufacturer.edit', {
            url: '/edit/:id',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/product/manufacturer/manufacturerManagement.html',
                    controller: 'manufacturerController as vm',
                    resolve: {
                        manufacturer: function (manufacturerService, $stateParams) {
                            var manufacturerId = $stateParams.id;
                            return manufacturerService.get({id: manufacturerId}).$promise;
                        }
                    }
                }
            }
        })
        .state('account', {
            url: '/account',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user, USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/user/account/account.html',
                    controller: 'accountController as vm'
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm',
                    resolve: {
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        },
                        categories: function (categoryService) {
                            return categoryService.list().$promise;
                        }
                    }
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })
        .state('account.changePassword', {
            url: '/change-password',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user, USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/user/account/change-password.html',
                    controller: 'changePassController as vm'
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm'
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })
        .state('account.addressBook', {
            url: '/address-book',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user, USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/back/user/address/address.html',
                    controller: 'addressController as vm',
                    resolve: {
                        addressList: function (addressService) {
                            return addressService.listAddress().$promise;
                        }
                    }
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm'
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })
        .state('shoppingCart', {
            url: '/shopping-cart',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/cart/shopping-cart.html',
                    controller: 'cartController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    '/mimdolt/pages/cart/cartController.js',
                                    '/mimdolt/pages/topbar/topBarController.js',
                                    '/mimdolt/pages/back/navbar/navbarController.js'
                                ]
                            });
                        }],
                        shoppingCart: function (cartService, $stateParams) {
                            return cartService.get().$promise;
                        },
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        }
                    }
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm',
                    resolve: {
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        },
                        categories: function (categoryService) {
                            return categoryService.list().$promise;
                        }
                    }
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })
        .state('addAddress', {
            url: '/shopping-cart/chooseAddress',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user, USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/cart/choose-address/choose-address.html',
                    controller: 'chooseAddressController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    '/mimdolt/pages/cart/choose-address/chooseAddressController.js',
                                    '/mimdolt/pages/topbar/topBarController.js',
                                    '/mimdolt/pages/back/navbar/navbarController.js'
                                ]
                            });
                        }],
                        addressList: function (addressService) {
                            return addressService.listAddress().$promise;
                        }
                    }
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm',
                    resolve: {
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        },
                        categories: function (categoryService) {
                            return categoryService.list().$promise;
                        }
                    }
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })
        .state('payment', {
            url: '/shopping-cart/payment',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.user, USER_ROLES.admin]
            },
            views: {
                'container@': {
                    templateUrl: 'mimdolt/pages/cart/payment/payment.html',
                    controller: 'paymentController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    '/mimdolt/pages/cart/payment/paymentController.js',
                                    '/mimdolt/pages/cart/cartService.js'
                                ]
                            });
                        }],
                        shoppingCart: function (cartService, $stateParams) {
                            return cartService.get().$promise;
                        },
                        defaultAddress: function (addressService) {
                            return addressService.getDefaultAddress().$promise;
                        }
                    }
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm',
                    resolve: {
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        },
                        categories: function (categoryService) {
                            return categoryService.list().$promise;
                        }
                    }
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })
        .state('store', {
            url: '/store',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            },
            views: {
                'container': {
                    templateUrl: 'mimdolt/pages/home/home.html',
                    //controller: 'homeController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    '/mimdolt/pages/topbar/topBarController.js',
                                    '/mimdolt/pages/back/navbar/navbarController.js'
                                ]
                            });
                        }]
                    }
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm',
                    resolve: {
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        },
                        categories: function (categoryService) {
                            return categoryService.list().$promise;
                        }
                    }
                },
                'slider': {
                    templateUrl: 'mimdolt/pages//slider/slider.html',
                    controller: 'SlickController'
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                },
                data: {
                    css: ['/resources/css/style.css']
                }
            }
        })
        .state('product', {
            url: '/product/:title/:code',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            },
            views: {
                'container': {
                    templateUrl: 'mimdolt/pages/product/view-detail.html',
                    controller: 'productViewController',
                    resolve: {
                        product: function (productService, $stateParams) {
                            return productService.getByCode({code: $stateParams.code}).$promise;
                        },
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    '/mimdolt/pages/product/productViewController.js',
                                    '/mimdolt/pages/topbar/topBarController.js',
                                    '/mimdolt/pages/back/navbar/navbarController.js'
                                ]
                            });
                        }]

                    }
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm',
                    resolve: {
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        },
                        categories: function (categoryService) {
                            return categoryService.list().$promise;
                        }
                    }
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                },
                data: {
                    css: ['/resources/css/style.css']
                }
            }
        })
        .state('categories', {
            url: '/store/category/:code/page/:page?option&pageSize&orderBy&priceRange',
            cache: false,
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            },
            views: {
                'container': {
                    templateUrl: 'mimdolt/pages/product/productByCategory.html',
                    controller: 'productByCategoryController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    '/mimdolt/pages/product/productByCategoryController.js',
                                ]
                            });
                        }],
                        productCategories: function (productService, $stateParams) {
                            var page = $stateParams.page;
                            var code = $stateParams.code;
                            var option = $stateParams.option;
                            var pageSize = $stateParams.pageSize;
                            var orderBy = $stateParams.orderBy;
                            var priceRange = $stateParams.priceRange;
                            console.log(option);
                            return productService.getByCategory({
                                code: code, page: page,
                                option: option, pageSize: pageSize,
                                orderBy: orderBy, priceRange: priceRange
                            }).$promise;
                        },
                        productFilterPanel: function (productService, $stateParams) {
                            var code = $stateParams.code;
                            return productService.getProductFilterPanel({code: code}).$promise;
                        },
                        lastSeen: function (productService) {
                            return productService.getLastSeen().$promise;
                        }

                    }
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm',
                    resolve: {
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        },
                        categories: function (categoryService) {
                            return categoryService.list().$promise;
                        }
                    }
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })

        .state('login', {
            url: '/login',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            },
            views: {
                'container': {
                    templateUrl: 'mimdolt/pages/login/login.html',
                    controller: 'LoginController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    '/mimdolt/pages/login/loginController.js',
                                    '/mimdolt/pages/topbar/topBarController.js',
                                    '/mimdolt/pages/back/navbar/navbarController.js'
                                ]
                            });
                        }]
                    }
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm'
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })
        .state('signup', {
            url: '/signup',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            },
            views: {
                'container': {
                    templateUrl: 'mimdolt/pages/register/register.html',
                    controller: 'registerController as vm'
                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm'
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm'
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                }
            }
        })
        .state('logout', {
            url: '/logout',
            template: " ",
            controller: "LogoutController as vm",
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.all]
            }
        })
        .state('/', {
            url: '/',
            access: {
                loginRequired: false,
                authorizedRoles: [USER_ROLES.all]
            },
            views: {
                'container': {
                    templateUrl: 'mimdolt/pages/home/home.html',
                    controller: 'homeController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    '/mimdolt/pages/home/homeController.js'
                                ]
                            });
                        }]
                    }

                },
                'topbar': {
                    templateUrl: 'mimdolt/pages/topbar/topbar.html',
                    controller: 'topBarController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    './mimdolt/pages/topbar/topBarController.js'
                                ]
                            });
                        }]
                    }
                },
                'navbar': {
                    templateUrl: 'mimdolt/pages/navbar/navbar.html',
                    controller: 'navbarController as vm',
                    resolve: {
                        deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                name: "myApp",
                                files: [
                                    './mimdolt/pages/back/navbar/navbarController.js'
                                ]
                            });
                        }],
                        cartDetail: function (cartService) {
                            return cartService.get().$promise;
                        },
                        categories: function (categoryService) {
                            return categoryService.list().$promise;
                        }
                    }
                },
                'slider': {
                    templateUrl: 'mimdolt/pages/slider/slider.html',
                    controller: 'SlickController'
                },
                'footer': {
                    //templateUrl: 'partials/admin/layout/footer.html'
                    //controller: 'FooterCtrl'
                },
                data: {
                    css: ['/resources/css/style.css']
                }
            },
            data: {
                css: ['/resources/css/style.css']
            }
        });
    $urlRouterProvider.otherwise("/");

    $locationProvider.html5Mode({
        enabled: true,
        rewriteLinks: true
    });
}]);

angular.module('myApp').run(["$rootScope", "$location", "$http", "AuthSharedService", "Session", "USER_ROLES", "$q", "$timeout", "$state", function ($rootScope, $location, $http, AuthSharedService, Session, USER_ROLES, $q, $timeout, $state) {
    var path = $location.path();

    $rootScope.$on('$stateChangeStart', function (event, next) {
        $rootScope.loading = true;
        if (next.url === "/login" && $rootScope.authenticated) {
            console.log("Here");
            event.preventDefault();
        }
        else if (next.access && next.access.loginRequired && ($rootScope.authenticated != undefined && !$rootScope.authenticated)) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-loginRequired", {path: path});
        }
        else if (next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-forbidden", {});
        }
    });

    $rootScope.$on('$stateChangeSuccess', function (scope, next, current) {
        $rootScope.loading = false;
        if (next.name == current.name)
            $location.path(next.url);
        console.log(path);
        console.log(next);
        console.log(current);
    });

    // Call when the the client is confirmed
    $rootScope.$on('event:auth-loginConfirmed', function (event, data) {
        console.log('login confirmed start ' + data);
        $rootScope.loadingAccount = false;
        var currentLocation = path;
        var nextLocation = (currentLocation != "/login" ? currentLocation : "/login");
        var delay = 200;

        $timeout(function () {
            Session.create(data);
            $rootScope.account = Session;
            $rootScope.authenticated = true;
            $location.path(nextLocation).replace();
        }, delay);

    });

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function (event, data) {
        if ($rootScope.loadingAccount && data.status !== 401) {
            $rootScope.requestedUrl = $location.path();
            $location.path($rootScope.requestedUrl);
        } else {
            console.log("login");
            Session.invalidate();
            $rootScope.authenticated = false;
            $rootScope.loadingAccount = false;
            var currentPath = $state.current.url;
            if (currentPath == data.path && !$rootScope.authenticated) {
                $rootScope.$broadcast("event:show-login-form", {});
            }
            $location.path(data.path);
        }
    });

    $rootScope.$on('event:auth-loginNotRequired', function (event, data) {
        console.log("login");
        Session.invalidate();
        $rootScope.authenticated = false;
        $rootScope.loadingAccount = false;
        $location.path(data.path);

    });

    // Call when the 403 response is returned by the server
    $rootScope.$on('event:auth-forbidden', function (rejection) {
        $rootScope.$evalAsync(function () {
            $location.path('/error/403').replace();
        });
    });

    // Call when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function () {
        $location.path('/').replace();
    });

    // Get already authenticated user account
    AuthSharedService.getAccount();


}]);
