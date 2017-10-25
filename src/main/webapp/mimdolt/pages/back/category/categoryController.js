/**
 * Created by Ghost on 11/11/2016.
 */
angular.module('myApp').controller("categoryController", ["$scope", "categoryService", "ngNotify","$http", function ($scope, categoryService, ngNotify,$http) {
    var vm = this;

    /**
     * @type {{id: string, name: string, parentId: string, description: string}}
     */

    $scope.treeOptions = {
        nodeChildren: "children",
        dirSelectable: true,
        injectClasses: {
            ul: "a1",
            li: "a2",
            liSelected: "a7",
            iExpanded: "a3",
            iCollapsed: "a4",
            iLeaf: "a5",
            label: "a6",
            labelSelected: "a8"
        }
    }

    $scope.displayChild = false;
    vm.parent = {};

    vm.category = {
        id: "",
        name: "",
        parentId: "",
        description: "",
        enable: true
    };
    $scope.categories = [{
        name: "",
        description: "",
        parentName: "",
        enable: ""
    }];
    vm.categoryCombo = [];
    $scope.data = [];
    vm.pages = [];
    vm.selected = 0;
    vm.categoryStatus = [
        {name: "نمایش", value: true},
        {name: "عدم نمایش", value: false}
    ];


    /**
     *   public methods
     */
    vm.add = add;
    vm.selectCategory = selectCategory;
    vm.cancel = cancel;
    vm.update = update;
    vm.getPage = getPage;
    vm.chooseCategory = chooseCategory;

    init();


    $scope.files = [];

    //listen for the file selected event
    $scope.$on("fileSelected", function (event, args) {
        $scope.$apply(function () {
            $scope.files.push(args.file);
        });
    });

    $scope.saveCategory = function () {
        console.log(vm.category);
        var file = $scope.files;
        var uploadUrl = "/api/admin/category";
        var fd = new FormData();
        fd.append('file', file);
        fd.append('category', angular.toJson(vm.category, true));
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).success(function () {
            console.log('success');
        }).error(function () {
            console.log('error');
        });
    }

    $scope.save = function () {
        vm.category.parentId = vm.parent.id;
        $http({
            method: 'POST',
            url: "/api/admin/category",
            headers: {
                'Content-Type': undefined
            },
            transformRequest: function (data) {
                console.log($scope.files);
                var formData = new FormData();
                formData.append('category', angular.toJson(vm.category, true));
                formData.append('file', $scope.files[0]);
                return formData;
            },
            data: {category: angular.toJson(vm.category, true), "file": $scope.files}
        }).success(function (data) {


        }).error(function (data, status) {
            console.log(status);
        });
    };

    /**
     *  init function
     */
    function init() {
        categoryService.query().$promise.then(
            function (response) {
                vm.categoryCombo = response;
                $scope.categories = response;
            },
            function (error) {
            });
    }

    function getPage(page) {
        vm.selected = page;
        categoryService.findRange({page: page}).$promise.then(
            function (response) {
                console.log(response.data);
                $scope.categories = response.data;
                vm.pages = new Array(response.count);

            },
            function (error) {
            });
    }


    /**
     *
     * @param category
     */
    function add() {
        vm.category.parentId = vm.parent.id;
        $scope.$broadcast('show-errors-check-validity');
        if ($scope.categoryForm.$valid) {
            categoryService.create(vm.category).$promise
                .then(
                function (response) {
                    init();
                    notify();
                    clearForm();
                },
                function (error) {
                    //console.log("The request failed: " + category);
                });
        }
    }

    $scope.showSelected = function (sel) {
        console.log(sel);
        vm.category = sel;
        angular.forEach(vm.categories, function (category) {
            if (category.id == sel.parentId) {
                vm.parent = category;
                return 0;
            }

        })
    };

    function clearForm() {
        $scope.categoryForm.$setPristine();
        cancel();
    }

    function selectCategory(category) {
        console.log(category);
        vm.category = category;

    }

    function cancel() {
        $scope.$broadcast('show-errors-reset');
        vm.category = {id: "", name: "", parentId: "", description: ""};
    }

    function update(category) {
        categoryService.save(category).$promise
            .then(
            function (response) {
                getPage(0);
                notify();
            },
            function (error) {
                console.log("The request failed: " + error);
            });
    }

    function chooseCategory(categoryId) {
        console.log(categoryId);
        if (vm.category.id != 0 || vm.category != undefined) {
            if (categoryId === vm.category.id) {
                $scope.categoryForm.parent.$setValidity("invalidParent", false);
            } else
                $scope.categoryForm.parent.$setValidity("invalidParent", true);
        }
    }

    self.displayChild = function () {
        $scope.displayChild = !$scope.displayChild;
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
