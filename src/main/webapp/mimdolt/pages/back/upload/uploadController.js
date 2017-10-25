angular.module('myApp').controller("uploadController", ["$scope", "$http", "$stateParams", "productImages", "fileManagerService", "productService",
    function ($scope, $http, $stateParams, productImages, fileManagerService, productService) {


        //an array of files selected
        $scope.files = [];
        $scope.images = [];
        $scope.imageCount = [];

        //listen for the file selected event
        $scope.$on("fileSelected", function (event, args) {
            $scope.$apply(function () {
                //add the file object to the scope's files collection
                $scope.files.push(args.file);
                if ($scope.imageCount.length + $scope.productImages.length < 5)
                    $scope.imageCount.push(1);
            });
        });

        $scope.init = function () {
            $scope.productImages = productImages;
            console.log($scope.productImages);
            if ($scope.productImages.length < 5) {
                $scope.imageCount = new Array(1);
            }

        };

        $scope.init();

        //the save method
        $scope.save = function () {

            $http({
                method: 'POST',
                url: "/api/admin/product/uploadImage?productId="+$stateParams.productId,
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: function (data) {
                    console.log(data.files);
                    var formData = new FormData();
                    formData.append("productId", $stateParams.productId);
                    for (var i = 0; i < data.files.length; i++) {
                        //add each file to the form data and iteratively name them
                        console.log(data.files[i]);
                        formData.append("file" + i, data.files[i]);
                    }
                    return formData;
                },
                data: {productId: $stateParams.productId, files: $scope.files}
            }).success(function (data) {


            }).error(function (data, status) {
                console.log(status);
            });
        };

        $scope.delete = function (image) {

            productService.removeImage({imageId: image.id}).$promise.then(
                function (response) {
                    angular.forEach($scope.images, function (img) {
                        if (image === img)
                            img.display = false;
                    });
                    checkForUpload();
                },
                function (error) {
                    console.log(error);
                });
        }

        $scope.setDefaultImage = function (image) {

            productService.setDefaultImage({productId: image.productId, imageId: image.id}).$promise.then(
                function (response) {
                    $scope.$evalAsync(function () {

                    });
                },
                function (error) {
                    console.log(error);
                });
        }


        function checkForUpload() {
            if ($scope.imageCount.length < 1) {
                $scope.$evalAsync(function () {
                    $scope.imageCount = new Array(1);
                });
            }

        }

    }]);
