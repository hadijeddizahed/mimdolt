/**
 * Created by Hadi Jeddi Zahed on 11/11/2016.
 *
 */
angular.module('myApp').controller("productController", ["$scope", "productService", "categoryService", "ngNotify", "$http",
    "$state", "manufacturers", "categories", "$stateParams", "product", "products", "options",
    function ($scope, productService, categoryService, ngNotify, $http,
              $state, manufacturers, categories, $stateParams, product, products, options) {
        var vm = this;

        /**
         * @type {{id: string, name: string, parentId: string, description: string}}
         */

        vm.product = {
            manufacturer:{},
            attributes: [],
            categories:[],
            relationshipProducts :[],
            enable: true
        };

        vm.categories = [];
        vm.categoryCombo = [];
        vm.manufacturers = [];
        vm.products = [];
        vm.relatedProduct = {};
        vm.relatedProducts = [];
        vm.options = [];
        vm.selectedOptions = [];
        $scope.data = [];
        vm.pages = [];
        vm.selected = 0;
        vm.categoryStatus = [
            {name: "نمایش", value: true},
            {name: "عدم نمایش", value: false}
        ];
        vm.viewTypes = [{"title":"صفحه محصول","value":0},{"title":"پانل جستجو","value":1},{"title":"هر دو","value":2}];


        /**
         *   public methods
         */
        vm.add = add;
        vm.selectCategory = selectCategory;
        vm.cancel = cancel;
        vm.update = update;
        vm.addToArray = addToArray;
        vm.setManufacturer = setManufacturer;
        vm.addToCategories = addToCategories;
        vm.addToRelatedProducts = addToRelatedProducts;
        vm.removeCategory = removeCategory;
        vm.removeRelatedProduct = removeRelatedProduct;
        vm.addToOptions = addToOptions;
        vm.removeOption = removeOption;
        vm.displayOptionPanel = displayOptionPanel;
        vm.addNewOption = addNewOption;


        init();

        /**
         *  init function
         */
        function init() {
            vm.categoryCombo = categories;
            vm.manufacturers = manufacturers;
            vm.products = products;
            vm.options = options;
            console.log(vm.options);
            if ($stateParams.productId) {
                vm.product = product;
                angular.forEach(vm.product.attributes,function(attribute){
                    for(var option in vm.options){
                        if(vm.options[option].name == attribute.productOptionValue[0].optionTitle)
                            vm.selectedOptions.splice(vm.selectedOptions.length, 0, vm.options[option].values);
                    }
                })
            }
        }

        /**
         *
         * @param category
         */
        function add() {
            $scope.$broadcast('show-errors-check-validity');
            if ($scope.productForm.$valid) {
                productService.save(vm.product).$promise
                    .then(
                    function (response) {
                        console.log(response);
                        $state.go('admin.product.uploadImage', {productId: response.id});
                    },
                    function (error) {
                        console.log("The request failed: " + error);
                    });
            }
        }

        function clearForm() {
            $scope.categoryForm.$setPristine();
            cancel();
        }

        function selectCategory(category) {
            vm.category = category;

        }

        function cancel() {
            $scope.$broadcast('show-errors-reset');
            vm.category = {id: "", name: "", parentId: "", description: ""};
        }

        function update() {
            productService.save(vm.product).$promise
                .then(
                function (response) {
                    $state.go('admin.product.uploadImage', {productId: response.id});
                },
                function (error) {
                    console.log("The request failed: " + error);
                });
        }


        function addToArray(id) {
            vm.product.categories.splice(0, 0, {id: id, name: ""});
            console.log(vm.product);
        }

        function setManufacturer(manufacturer) {
            vm.product.manufacturer.id = manufacturer.description.id;
        }

        function addToCategories(category) {
            if (category !== undefined) {
                var related = {"id": category.description.id, "title": category.description.name};
                if (vm.categories.indexOf(category.description.id) < 0) {
                    vm.categories.splice(vm.categories.length, 0, related);
                    vm.product.categories.splice(vm.categories.length, 0, related);
                }

            }
        }

        function addToRelatedProducts(relatedProduct) {
            if (relatedProduct !== undefined) {
                var related = {"id": relatedProduct.description.id, "title": relatedProduct.description.title};
                if (vm.relatedProducts.indexOf(relatedProduct.description.id) < 0) {
                    vm.relatedProducts.splice(vm.relatedProducts.length, 0, relatedProduct.description.id);
                    vm.product.relationshipProducts.splice(vm.product.relationshipProducts.length,0,{id: relatedProduct.description.id, title: relatedProduct.description.title});
                }

            }

        }

        function removeCategory(category) {
            var index = vm.product.categories.indexOf(category);
            var index2 = vm.categories.indexOf(category);
            if (index2 != undefined) {
                vm.categories.splice(index2, 1);
            }
            if (index != undefined) {
                vm.product.categories.splice(index, 1);
            }
        }


        function removeRelatedProduct(product) {
            console.log(product);
            var index = vm.product.relationshipProducts.indexOf(product);
            console.log(index);
            var index2 = vm.relatedProducts.indexOf(product.id);
            if (index2 != undefined) {
                vm.relatedProducts.splice(index2, 1);
            }
            if (index != undefined) {
                vm.product.relationshipProducts.splice(index, 1);
            }
        }

        function addToOptions(option) {
            var optionIndex = [];
            var optionValue = {id:null,optionTitle:option.description.name,optionId: option.description.id, optionValueId: "", quantity: "", price: "", weight: "",viewType:""};
            optionIndex.push(optionValue);
            var productOptionValue = {"productOptionValue": optionIndex};
            if (vm.selectedOptions.indexOf(option.description.values) < 0) {
                vm.selectedOptions.splice(vm.selectedOptions.length, 0, option.description.values);
                console.log(vm.selectedOptions[0]);
                vm.product.attributes.splice(vm.product.attributes.length, 0, productOptionValue);
            }
        }

        function removeOption(option) {
            var index = vm.selectedOptions.indexOf(option);
            if (index != undefined) {
                vm.selectedOptions.splice(index, 1);
                vm.product.attributes.splice(index, 1);
            }
        }

        function displayOptionPanel(last, index, optionPanelIndex) {
            if (optionPanelIndex == undefined)
                return last;
            return index == optionPanelIndex;
        }

        function addNewOption(parentIndex, index) {
            console.log(parentIndex);
            var opId = vm.product.attributes[parentIndex].productOptionValue[0].optionId;
            console.log(opId);
            var optionValue = {optionId: opId, optionValueId: "", quantity: "", price: "", weight: ""};
            vm.product.attributes[parentIndex].productOptionValue.splice(index + 1, 0, optionValue);

        }

        function notify() {
            ngNotify.set('عملیات با موفقیت انجام شد', {
                target: '#notifyTarget',
                duration: 3000,
                position: 'top',
                type: 'success',
                sticky: false
            });
        }

    }]);


angular.module('myApp').controller("productsController", ["$scope", "productService", "categoryService", "ngNotify", "$http", "$state", "products",
    function ($scope, productService, categoryService, ngNotify, $http, $state, products) {

        var vm = this;
        /**
         *
         * private variables
         *
         */

        vm.selected = 0;
        /**
         *
         * public method
         *
         */

        vm.getPage = getPage;
        vm.changeStatus = changeStatus;
        vm.init = init;


        init();

        function init() {
            console.log(products);
            vm.products = products.data;
            vm.pages = new Array(products.count);
        }

        function getPage(page) {
            vm.selected = page;
            productService.getPage({page: page}).$promise.then(
                function (response) {
                    vm.products = response.data;
                    vm.pages = new Array(response.count);

                },
                function (error) {
                });
        }

        function changeStatus(productId) {
            productService.changeStatus({productId: productId}).$promise.then(
                function (response) {
                    var page = 0;
                    $state.params.page == undefined ? page = 0 : page = $state.params.page;
                    $scope.$evalAsync(function () {
                        getPage(page);
                    });
                },
                function (error) {
                });
        }

        $scope.trixInitialize = function (e, editor) {
            var document = editor.getDocument()
            document.toString(); // is a JavaScript string
            console.log(document.toString());
        }


    }]);
